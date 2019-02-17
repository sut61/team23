import { Component, OnInit,ViewChild,Inject  } from '@angular/core';
import {DatePipe} from '@angular/common';
import { GoldcardService } from '../goldcard.service';
import {ErrorStateMatcher} from '@angular/material/core';
import {map, startWith} from 'rxjs/operators';
import {Observable} from 'rxjs';
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import { HttpClient} from '@angular/common/http';
import {FormBuilder, FormGroup} from '@angular/forms';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { AuthService } from '../auth/auth.service';
import { AlertService } from '../alert.service';
import {MatPaginator, MatTableDataSource} from '@angular/material';
import {MatBottomSheet, MatBottomSheetRef} from '@angular/material';
import {Pub} from './pub';


export class MyErrorStateMatcher implements ErrorStateMatcher {
isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-publicz',
  templateUrl: './publicz.component.html',
  styleUrls: ['./publicz.component.css']
})
export class PubliczComponent implements OnInit {
  hospitals : Array<any>;
  officers : Array<any>;
  typeofpub : Array<any>;
  select: any = {
          officername: '',
          hospitalname: '',
          typeofpub: '',
  };
  input: any = {
          publiczHead: '',
          PublicizeDetail: '',
          email: '',
          call: '',
  }
  startDate = new Date(1990, 0, 1);
  CurrentDate = new Date();
  pipe = new DatePipe('en-US');
  matcher = new MyErrorStateMatcher();
  lock: FormGroup;
  myControl = new FormControl();

  constructor(private formbuilder:FormBuilder,private alertService:AlertService, private goldcardService: GoldcardService , private bottomSheet: MatBottomSheet, private httpClient: HttpClient,private route: ActivatedRoute,
   private router: Router ){}

  isValidFormSubmitted = null;
  Header = ".{5,20}";
  Detail = "^\\S.+";
  email  = "\\w{2,10}@\\w{2,10}\\.(com)||\\w{2,10}@\\w{2,10}\\.(\w{2,3}\\.\\w{2,3})||\\w{2,10}@\\w{2,10}\\.(COM)";
  call   = "[0][896]\\d{8}"
  pubForm  = this.formbuilder.group({
    publiczHead_no : ['', [Validators.required, Validators.pattern(this.Header)]],
    PublicizeDetail_no: ['', [Validators.required, Validators.pattern(this.Detail)]],
    officerName_no: ['', [Validators.required, Validators.pattern('')]],
    hospitalName_no : ['', [Validators.required, Validators.pattern('')]],
    typePubName_no : ['', [Validators.required, Validators.pattern('')]],
    email_no : ['', [Validators.required, Validators.pattern(this.email)]],
    call_no : ['', [Validators.required, Validators.pattern(this.call)]],
  });
  onFormSubmit(){
        this.isValidFormSubmitted = false;
        if (this.pubForm.invalid) {
                  this.alertService.error('กรุณา กรอกข้อมูล ให้ครบถ้วน');
                  if(this.pubForm.controls['publiczHead_no'].hasError('pattern')){
                                  this.alertService.error('กรุณากรอกหัวเรื่องให้มีความยาว 5 ถึง 20 ตัวอักษร');
                  }else if(this.pubForm.controls['PublicizeDetail_no'].hasError('pattern')){
                                  this.alertService.error('รายละเอียดห้ามมีช่องว่างหน้าตัวอักษร');
                  }else if(this.pubForm.controls['email_no'].hasError('pattern')){
                                  this.alertService.error('กรุณากรอกอีเมลติดต่อให้ถูกต้อง');
                  }else if(this.pubForm.controls['call_no'].hasError('pattern')){
                                  this.alertService.error('กรุณากรอกเบอร์โทรติดต่อให้ถูกต้อง');
                  }
                  return;
        }
        else if (this.pubForm.valid){
                this.save(this.pubForm.value);
                this.isValidFormSubmitted = true;
        }
         this.isValidFormSubmitted = true;
  }

  save(pub : Pub){
    // http://localhost:8080/Publicz/{publiczHead}/{PublicizeDetail}/{date_reg}/{officerName}/{hospialName}/{TypeOfPublicz}/{email}/{call}
          this.httpClient.post('http://localhost:8080/Publicz/'+pub.publiczHead_no+'/'+pub.PublicizeDetail_no+'/'+this.pipe.transform(this.CurrentDate,'dd:MM:yyyy')+'/'+pub.officerName_no+'/'+pub.hospitalName_no+'/'+pub.typePubName_no+'/'+pub.email_no+'/'+pub.call_no
          ,pub)
              .subscribe(
                data => {
                  console.log('PUT Request is successful', data);
                  this.alertService.success('บันทึกสำเร็จ');

                },
                error => {
                  console.log('Error', error);
                  this.alertService.error('ไม่บันทึกสำเร็จ');
                }
              );
  }

  ngOnInit() {
    this.goldcardService.getOfficer().subscribe(data =>{
      this.officers = data;
      console.log(this.officers);
    });
    this.goldcardService.getHospital().subscribe(data =>{
      this.hospitals = data;
      console.log(this.hospitals);
    });
    this.goldcardService.getTypeOfPublicz().subscribe(data =>{
      this.typeofpub = data;
      console.log(this.typeofpub);
    });

  }

}
