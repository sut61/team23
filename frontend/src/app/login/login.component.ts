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

  members: Array<any>;
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

    this.goldcardService.getRightRegistration().subscribe(data =>{
              this.members = data;
              console.log(this.members);
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
       for(var item of this.members){
                if(user.userName === '' &&  user.password ===  ''){
                  status = 'non';
                  break;
                }
                else if(user.userName == item.username &&  user.password ==  item.password){
                  status = 'user';
                  this.router.navigate(['/userview']);
                  break;
                }
                else if(user.userName == "admin" && user.password == "admin"){
                  status = 'admin';
                  this.router.navigate(['/home']);
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
                  this.authService.login(item.username);
                  localStorage.setItem('currentUser',item.username);
                  this.loggedIn.next(true);
              }
              else if(status == 'admin'){
                   this.authService.loginadmin("admin");
                   localStorage.setItem('currentUser',"admin");
                   this.loggedIn.next(true);
              }
              else if((status == 'error')){
                  this.alertService.error('Incorrect username or password.');

                }
    }
}

@Component({
  selector: 'error',
  templateUrl: './error.html'
})
export class ErrorComponent{

}
