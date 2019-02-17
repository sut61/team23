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
import {GDC} from './gdc'

@Component({
  selector: 'app-gd-card',
  templateUrl: './gd-card.component.html',
  styleUrls: ['./gd-card.component.css']
})
export class GDCardComponent implements OnInit {

  hospitals : Array<any>;
  members : Array<any>;
  officers : Array<any>;
  usernames : Array<any>;
  select: any = {
          officername: '',
          hospitalname: '',
          usermane:  '',
    };
  input: any = {
          detail: ''
  }

  constructor(private formbuilder:FormBuilder,private alertService:AlertService, private goldcardService: GoldcardService , private bottomSheet: MatBottomSheet, private httpClient: HttpClient,private route: ActivatedRoute,
   private router: Router ){}

  isValidFormSubmitted = null;
  Detail = "^\\S.+";
  gdcForm  = this.formbuilder.group({
    officerName_no : ['', [Validators.required, Validators.pattern('')]],
    hospitalName_no: ['', [Validators.required, Validators.pattern('')]],
    username_no: ['', [Validators.required, Validators.pattern('')]],
    detail_no : ['', [Validators.required, Validators.pattern(this.Detail)]],
  });
  onFormSubmit(){
        this.isValidFormSubmitted = false;
        if (this.gdcForm.invalid) {
                  this.alertService.error('กรุณา กรอกข้อมูล ให้ครบถ้วน');
                  if(this.gdcForm.controls['detail_no'].hasError('pattern')){
                                  this.alertService.error('กรุณากรอกรายละเอียดโดยห้ามขึ้นต้นด้วยช่องว่าง');
                  }
                  return;
        }
        else if (this.gdcForm.valid){
                this.save(this.gdcForm.value);
                this.isValidFormSubmitted = true;
        }
         this.isValidFormSubmitted = true;
  }

  save( gdc : GDC){
  // http://localhost:8080/Goldcard/{detail}/{officerName}/{HospialName}/{MemberName}
              this.httpClient.post('http://localhost:8080/Goldcard/'+gdc.detail_no+'/'+gdc.officerName_no+'/'+gdc.hospitalName_no+'/'+gdc.username_no
              ,gdc)
                .subscribe(
                    data => {
                        console.log('PUT Request is successful', data);
                        this.alertService.success('บันทึกเรียบร้อยแล้ว');

                    },
                    error => {
                        console.log('Error', error);
                        this.alertService.success('กรอกข้อมูลผิดพลาด โปรดตรวจสอบใหม่');
                    }
                );

  }

  ngOnInit() {

    this.goldcardService.getHospital().subscribe(data =>{
      this.hospitals = data;
      console.log(this.hospitals);
      });
  this.goldcardService.getMember().subscribe(data =>{
      this.members = data;
      console.log(this.members);
      });
  this.goldcardService.getOfficer().subscribe(data =>{
      this.officers = data;
      console.log(this.officers);
      });

  this.goldcardService.getRightRegistration().subscribe(data =>{
      this.usernames = data;
      console.log(this.usernames);
      });

  }

}
