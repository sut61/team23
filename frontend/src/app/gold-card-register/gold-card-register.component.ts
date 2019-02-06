import { Component, OnInit } from '@angular/core';
import {DatePipe} from '@angular/common';
import { GoldcardService } from '../goldcard.service';
import {ErrorStateMatcher} from '@angular/material/core';
import {map, startWith} from 'rxjs/operators';
import {Observable} from 'rxjs';
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import { HttpClient} from '@angular/common/http';
import {FormBuilder, FormGroup} from '@angular/forms';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { AuthService } from '../auth/auth.service';
import { AlertService } from '../alert.service';

export class MyErrorStateMatcher implements ErrorStateMatcher {
isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}


@Component({
  selector: 'app-gold-card-register',
  templateUrl: './gold-card-register.component.html',
  styleUrls: ['./gold-card-register.component.css']
})
export class GoldCardRegisterComponent implements OnInit {


  provinces : Array<any>;
  rightstypes : Array<any>;
  hospitals : Array<any>;
  rightregistrations : Array<any>;
  input: any = {
      username: '',
      password: '',
      firstname:  '',
      surname:  '',
      tel:  '',
      personalcard:  '',
      birthday:  '',
      dateregis:  '',
      ids: '',
    };
    select: any = {
          rightstypename: '',
          hospitalname: '',
          provincename:  '',
    };

  startDate = new Date(1990, 0, 1);
  CurrentDate = new Date();
  pipe = new DatePipe('en-US');
  matcher = new MyErrorStateMatcher();
  lock: FormGroup;
  myControl = new FormControl();
  ids : number;
  temp : number;
  constructor(private alertService:AlertService,private authService: AuthService,fb: FormBuilder,private goldcardService: GoldcardService, private httpClient: HttpClient,private router: Router){
       this.lock = fb.group({
          hideRequiredMarker: false,
          floatLabel: 'never',
       });

  }

  private loggedIn = new BehaviorSubject<boolean>(false); // {1}

    get isLoggedIn() {
      return this.loggedIn.asObservable(); // {2}
    }



  regis(){
        this.alertService.clear();
          // http://localhost:8080/Rightregistration/{username}/{password}/{firstname}/{surname}/{tel}/{personal}/{dateregis}/{birthdate}/{provincename}/{rightstypename}/{hospitalname}
              this.httpClient.post('http://localhost:8080/Rightregistration/'+ this.input.username+ '/' + this.input.password+'/'+this.input.firstname+'/'+this.input.surname+'/'+this.input.tel+'/'+this.input.personalcard+'/'+this.pipe.transform(this.CurrentDate,'dd:MM:yyyy')+'/'+this.pipe.transform(this.input.birthday,'dd:MM:yyyy')+'/'+this.select.provincename+'/'+this.select.rightstypename+'/'+this.select.hospitalname,this.input)
                .subscribe(
                    data => {
                        console.log('PUT Request is successful', data);
                        this.accept();

                    },
                    error => {
                        console.log('Error', error);

                        this.alertService.error('Error Username อาจจะมีผู้ใช้งานแล้ว\nหรือ กรอกข้อมูลผิดพลาด');
                    }
                );

  }


  accept(){
              this.alertService.clear();
             //     AcceptToUser/{acceptdate}/{codeaccept}/{username}/{comment}/{statusname}/{officername}
                         this.httpClient.post('http://localhost:8080/AcceptToUser/'+this.pipe.transform(this.CurrentDate,'dd:MM:yyyy')+'/'+this.input.username+'/null/Fail/null',this.input)
                               .subscribe(
                                   data => {
                                        this.alertService.success('รอการยืนยันสิทธิจากเจ้าหน้าที่์');
                                   },
                                   error => {
                                       console.log('Error', error);
                                       this.alertService.error('Error มีปัญหา');
                                   }
                               );

  }
  login(){
           this.authService.logout();
  }
  passwordFormControl = new FormControl('', [
            Validators.required,
  ]);

  ngOnInit() {
      this.goldcardService.getRightsType().subscribe(data =>{
            this.rightstypes = data;
            console.log(this.rightstypes);
      });
      this.goldcardService.getProvince().subscribe(data =>{
            this.provinces = data;
            console.log(this.provinces);
      });
      this.goldcardService.getHospital().subscribe(data =>{
            this.hospitals = data;
            console.log(this.hospitals);
      });
      this.goldcardService.getRightRegistration().subscribe(data =>{
                  this.rightregistrations = data;
                  console.log(this.rightregistrations);
      });

  }
}
