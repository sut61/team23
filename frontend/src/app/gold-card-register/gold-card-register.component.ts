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
import { Gc } from './gc';

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

  myControl = new FormControl();
  ids : number;
  temp : number;


  constructor(private formbuilder: FormBuilder,private alertService:AlertService,private authService: AuthService,fb: FormBuilder,private goldcardService: GoldcardService, private httpClient: HttpClient,private router: Router){


  }

  isValidFormSubmitted = null;
  telPattern = "[0]\\d{9}";
  personalPattern = "\\d{13}";
    myForm = this.formbuilder.group({
    username_no : ['', [Validators.required, Validators.pattern('\\w{3,20}')]],
    password_no: ['', [Validators.required, Validators.pattern('')]],
    firstname_no: ['', [Validators.required, Validators.pattern('')]],
    surname_no : ['', [Validators.required, Validators.pattern('')]],
    birthday_no : ['', [Validators.required, Validators.pattern('')]],
    hospital_no : ['', [Validators.required, Validators.pattern('')]],
    province_no: ['', [Validators.required, Validators.pattern('')]],
    rightstype_no : ['', [Validators.required, Validators.pattern('')]],
      personal_no : ['', [Validators.required, Validators.pattern(this.personalPattern)]],
      tel_no: ['', [Validators.required, Validators.pattern(this.telPattern)]],
    });

    onFormSubmit(){
        this.isValidFormSubmitted = false;
        if (this.myForm.invalid) {
                  this.alertService.error('กรุณา กรอกข้อมูล ให้ครบ');
                  if(this.myForm.controls['username_no'].hasError('pattern')){
                                  this.alertService.error('กรุณา กรอก username (3-20 ตัวอักษร) ให้ถูกต้อง');
                  }
                  else if(this.myForm.controls['personal_no'].hasError('pattern')){
                                  this.alertService.error('กรุณา กรอกบัตรประจำตัวประชาชน เลข 13 หลัก ให้ถูกต้อง');
                  }
                  else if(this.myForm.controls['tel_no'].hasError('pattern')){
                                   this.alertService.error('กรุณา กรอกหมายเลขโทรศัพท์ 0+เลข9หลัก ให้ถูกต้อง');
                  }

                  return;
        }
        else if (this.myForm.valid){
                this.regis(this.myForm.value);
                this.isValidFormSubmitted = true;
        }

         this.isValidFormSubmitted = true;
    }

  private loggedIn = new BehaviorSubject<boolean>(false); // {1}

    get isLoggedIn() {
      return this.loggedIn.asObservable(); // {2}
    }

  regis(gc : Gc){
          // http://localhost:8080/Rightregistration/{username}/{password}/{firstname}/{surname}/{tel}/{personal}/{dateregis}/{birthdate}/{provincename}/{rightstypename}/{hospitalname}
              this.httpClient.post('http://localhost:8080/Rightregistration/'+ gc.username_no+'/' + gc.password_no+'/'+gc.firstname_no+'/'+gc.surname_no+'/'+gc.tel_no+'/'+gc.personal_no+'/'+this.pipe.transform(this.CurrentDate,'dd:MM:yyyy')+'/'+this.pipe.transform(gc.birthday_no,'dd:MM:yyyy')+'/'+gc.province_no+'/'+gc.rightstype_no+'/'+gc.hospital_no,gc)
                .subscribe(
                    data => {
                        console.log('PUT Request is successful', data);
                        this.accept(gc);
                    },
                    error => {
                        console.log('Error', error);
                        this.alertService.error('Error Username หรือ บัตรประจำตัวประชาชน มีผู้ใช้งานแล้ว');
                    }
                );

  }


  accept(gc : Gc){
             //     AcceptToUser/{acceptdate}/{codeaccept}/{username}/{comment}/{statusname}/{officername}
                         this.httpClient.post('http://localhost:8080/AcceptToUser/'+this.pipe.transform(this.CurrentDate,'dd:MM:yyyy')+'/'+gc.username_no+'/null/Fail/null',this.input)
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
