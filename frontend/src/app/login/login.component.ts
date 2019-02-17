import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthService } from './../auth/auth.service';
import { Observable } from 'rxjs/Observable';
import { GoldcardService } from '../goldcard.service';
import { User } from './user';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { AlertService } from '../alert.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: FormGroup;                    // {1}
  private formSubmitAttempt: boolean; // {2}
  private loggedIn = new BehaviorSubject<boolean>(false); // {1}

  get isLoggedIn() {
    return this.loggedIn.asObservable(); // {2}
  }
  waitaccepts: Array<any>;
  members: Array<any>;
  officers: Array<any>;
  isLoggedIn$: Observable<boolean>;
  constructor(
    private fb: FormBuilder,         // {3}
    private authService: AuthService, // {4}
     private router: Router,private goldcardService: GoldcardService,private alertService: AlertService
  ) {}

  ngOnInit() {
    this.form = this.fb.group({     // {5}
      userName: ['', Validators.required],
      password: ['', Validators.required]
    });

    this.goldcardService.getAccepted().subscribe(data =>{
                      this.members = data;
                      console.log(this.members);
    });
    this.goldcardService.getAcceptToUser().subscribe(data =>{
                          this.waitaccepts = data;
                          console.log(this.waitaccepts);
        });
    this.goldcardService.getOfficer().subscribe(data =>{
                          this.officers = data;
                          console.log(this.officers);
    });

    this.isLoggedIn$ = this.authService.isLoggedIn;

  }

  isFieldInvalid(field: string) { // {6}
    return (
      (!this.form.get(field).valid && this.form.get(field).touched) ||
      (this.form.get(field).untouched && this.formSubmitAttempt)
    );
  }

  onSubmit() {
      if (this.form.valid) {
  //      this.authService.login(this.form.value); // {7}
          this.login2(this.form.value);
      }
      this.formSubmitAttempt = true;             // {8}
    }

 register(){
          this.authService.register();
          this.formSubmitAttempt = true;
  }
  login2(user: User){
       this.alertService.clear();
       for(var item of this.members){
          for(var i of this.officers){
                if(user.userName == i.userName && user.password == i.passWord){
                      status = 'admin';
                      this.router.navigate(['/home']);
                      this.authService.loginadmin(i.userName);
                      localStorage.setItem('currentUser',i.userName);
                      break;
                }
          }
                if(user.userName === '' &&  user.password ===  ''){
                  status = 'non';
                  break;
                }
                else if(user.userName == item.rightRegistration.username &&  user.password ==  item.rightRegistration.password){
                  status = 'user';
                  this.router.navigate(['/userview']);
                  break;
                }
                else{
                  status = 'error';
                }
       }
              if(status == 'non'){
                  alert('กรุณากรอก Username และ password');
          }
              else if(status == 'user'){
                  this.authService.login(item.rightRegistration.username);
                  localStorage.setItem('currentUser',item.rightRegistration.username);
                  this.loggedIn.next(true);
              }
              else if(status == 'admin'){
                   this.authService.loginadmin(i.userName);
                   localStorage.setItem('currentUser',i.userName);
                   this.loggedIn.next(true);
              }
              else if(status == 'error'){
                  this.alertService.error('Incorrect username or password.');
              }
              else if(status = 'wait'){
                  this.alertService.error('รอเจ้าหน้าที่ยืนยันในการมีสิทธิ์บัตรทอง');
              }
    }
}
@Component({
  selector: 'error',
  templateUrl: './error.html'
})
export class ErrorComponent{

}
