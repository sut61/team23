import {Component, OnInit, ViewChild,Inject} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CollectionViewer, DataSource} from "@angular/cdk/collections";
import {MatPaginator, MatTableDataSource} from '@angular/material';
import { GoldcardService } from '../goldcard.service';
import { AlertService } from '../alert.service';
import {DatePipe} from '@angular/common';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import {MatBottomSheet, MatBottomSheetRef} from '@angular/material';
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {FormBuilder, FormGroup} from '@angular/forms';
import { Acc } from './acc';

export interface Accs{
      codeaccept:  string,
      dateregis:  string,
      username: string,
      name:  string,
      tel:  string,
      personalcard:  string,
      hospitalname: string,
      provincename:  string,
      rightstypename:  string,
      status: string,
      select:string,
}

export interface Acc {
  comment_no: string;
  documentCode_no: string;
}


@Component({
  selector: 'app-accept-to-user',
  templateUrl: './accept-to-user.component.html',
  styleUrls: ['./accept-to-user.component.css']
})
export class AcceptToUserComponent implements OnInit {
  labelPosition : 'fail';
  displayedColumns: string[] = ['codeaccept','dateregis','username','name','tel','personalcard','hospitalname','provincename','rightstypename','status','select'];
  filter : '';
  accs : Array<any>;
  hospitals : Array<any>;
  statuss : Array<any>;
  dataSource = new MatTableDataSource<Accs>(this.accs);
  @ViewChild(MatPaginator) paginator: MatPaginator;
  pipe = new DatePipe('en-US');
  CurrentDate = new Date();
  select: any = {
          hospitalname: '',
          personaldoctors: '',
  };
  table: any = {
    id: '',
    username: '',
    firstname: '',
    surname: '',
    personalcard: '',
    birthday: '',
    dateregis: '',
    comment: '',
    dateaccept: '',
  };
  documentCode: '';
  comment: '';
  hospitalName : string;
  constructor(private formbuilder: FormBuilder,private alertService:AlertService,private goldcardService: GoldcardService,private httpClient: HttpClient
  ,private route: ActivatedRoute, private router: Router,private bottomSheet: MatBottomSheet) {
   }

  ngOnInit() {
      this.goldcardService.getHospital().subscribe(data =>{
            this.hospitals = data;
            console.log(this.hospitals);
      });
      this.goldcardService.getStatus().subscribe(data =>{
                  this.statuss = data;
                  console.log(this.statuss);
            });
      this.goldcardService.getAcceptToUser().subscribe(data =>{
                  this.accs = data;
                  this.dataSource.data = data;
                  console.log(this.accs);
      });
  }

  isValidFormSubmitted = null;

          accForm = this.formbuilder.group({
          comment_no : ['', [Validators.required, Validators.pattern('.{3,30}')]],
          documentCode_no: ['', [Validators.required, Validators.pattern('[ECN]\\d{1,8}')]],
          acc_no : ['', [Validators.required, Validators.pattern('')]],
          });

  onFormSubmit(){
          this.isValidFormSubmitted = false;
          if (this.accForm.invalid) {
                        this.alertService.error('กรุณา กรอกข้อมูล ให้ครบ');
                        if(this.accForm.controls['documentCode_no'].hasError('pattern')){
                                         this.alertService.error('กรุณา กรอกเอกสาร ขึ้นต้น E/C/N + เลขสูงสุด8หลัก ให้ถูกต้อง');
                        }
                        else if(this.accForm.controls['comment_no'].hasError('pattern')){
                                        this.alertService.error('กรุณา กรอก comment (3-30 ตัวอักษร) ให้ถูกต้อง');
                        }
                        return;
          }
           else if (this.accForm.valid){

                        if(this.accForm.controls['acc_no'].value == "pass")
                            this.Accept(this.accForm.value,this.accForm.controls['comment_no'].value);
                        else if(this.accForm.controls['acc_no'].value == "fail")
                            this.NotAccept(this.accForm.value,this.accForm.controls['comment_no'].value);

                        this.isValidFormSubmitted = true;
           }

            this.isValidFormSubmitted = true;
   }

  applyFilter(filterValue: string) {
      this.filterdataSource(filterValue);
    }

    private filterdataSource(filterValue: string) {
      this.dataSource.filter = filterValue.trim().toLowerCase();

    }

    showuser(row){
          if(row.comment == 'null' )
                this.table.comment = "";
          else{
                this.table.comment = row.comment;
          }
          this.table.documentCode = row.documentCode;
          this.table.dateaccept = row.dateAccept;
          this.table.id = row.rightRegistration.regId;
          this.table.username = row.rightRegistration.username;
          this.table.firstname = row.rightRegistration.firstname;
          this.table.surname = row.rightRegistration.surname;
          this.table.personalcard = row.rightRegistration.personalcard;
          this.table.birthday = row.rightRegistration.birthday;
          this.table.dateregis = row.rightRegistration.dateregis;
          console.log('Select'+this.table.username);
    }

    Accept(acc : Acc,com : string){
  //      UpdateAccept/{id}/{acceptdate}/{codeAccept}/{comment}/{username}/{statusname}/{officername}/{documentCode}
              this.httpClient.put('http://localhost:8080/UpdateAccept/'+this.table.id+'/'+this.pipe.transform(this.CurrentDate,'dd:MM:yyyy')+'/P'+this.table.id+'/'+this.table.username+'/'+com+'/Pass/'+localStorage.getItem('currentUser')+'/'+acc.documentCode_no,acc)
                    .subscribe(
                        data => {
                            console.log('PUT Request is successful', data);
                            this.alertService.success('ยืนยัน เรียบร้อย');
                            this.bottomSheet.open(Sheet);
                            this.router.navigate(['/reload/AcceptToUser']);
                        },
                        error => {
                            console.log('Error', error);
                            this.alertService.error('Error กรุณาเลือก User ที่จะยืนยัน หรือ หมายเลขเอกสารถูกอ้างอิงแล้ว');

                        }
                    );
    }
    NotAccept(acc : Acc,com : string){
      //      UpdateAccept/{id}/{acceptdate}/{codeAccept}/{comment}/{username}/{statusname}/{officername}/{documentCode}
                  this.httpClient.put('http://localhost:8080/UpdateAccept/'+this.table.id+'/'+this.pipe.transform(this.CurrentDate,'dd:MM:yyyy')+'/F'+this.table.id+'/'+this.table.username+'/'+com+'/Fail/'+localStorage.getItem('currentUser')+'/'+acc.documentCode_no,acc)
                        .subscribe(
                            data => {
                                console.log('PUT Request is successful', data);
                                this.bottomSheet.open(Sheetq);
                                this.router.navigate(['/reload/AcceptToUser']);
                            },
                            error => {
                                console.log('Error', error);
                                this.alertService.error('Error กรุณาเลือก User ที่จะยืนยัน หรือ หมายเลขเอกสารถูกอ้างอิงแล้ว');


                            }
                        );

    }



}

@Component({
  selector: 'sheet',
  templateUrl: 'sheet.html',
})
export class Sheet {
  constructor(private bottomSheetRef: MatBottomSheetRef<Sheet>) {}

  openLink(event: MouseEvent): void {
    this.bottomSheetRef.dismiss();
    event.preventDefault();
  }

}

@Component({
  selector: 'Sheetq',
  templateUrl: 'Sheetq.html',
})
export class Sheetq {
  constructor(private bottomSheetRef: MatBottomSheetRef<Sheet>) {}

  openLink(event: MouseEvent): void {
    this.bottomSheetRef.dismiss();
    event.preventDefault();
  }

}
