import { Component, OnInit, ViewChild,Inject } from '@angular/core';
import { GoldcardService } from '../goldcard.service';
import { HttpClient} from '@angular/common/http';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { AlertService } from '../alert.service';
import { AuthService } from '../auth/auth.service';
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import {FormBuilder, FormGroup} from '@angular/forms';
import { DataSource } from '@angular/cdk/collections';
import {MatPaginator, MatTableDataSource} from '@angular/material';
import {DatePipe} from '@angular/common';
function wait(ms){
   var start = new Date().getTime();
   var end = start;
   while(end < start + ms) {
     end = new Date().getTime();
  }
}

export interface CardElement {
AcceptId: number;
codeAccept: string;
comment: string;
officer: string;
rightRegistration: string;
status: string;
Expensess: string;
}
@Component({
selector: 'app-card',
templateUrl: './card.component.html',
styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {
selectedIndex: number;
transform: number;
displayedColumns: string[] = ['AcceptId', 'rightRegistration', 'codeAccept', 'officer', 'comment','status'];
filter : '';

rightregistrations: Array<any>;
PassAcceptToUser: Array<any>;
Cardshow : Array<any>;
Expensess : Array<any>;
card : Array<any>;

input: any = {
AcceptId: '',
Cardcode: '',
Comment: '',
date: '',
Expenses: '',
status: '',
DeleteSelect: '',
};
output: any = {
Delete : '',
};


select: any = {
provinceName: '',
typeName: '',
affiliationName: '',
};


lock: FormGroup;


dataSourceAccepted = new MatTableDataSource<CardElement>(this.Cardshow);

CurrentDate = new Date();
pipe = new DatePipe('en-US');
constructor(private formbuilder: FormBuilder,
            private alertService : AlertService,
            private authService: AuthService,
            fb: FormBuilder,
            private goldcardService: GoldcardService,
            private httpClient: HttpClient,
            private router: Router){

    this.lock = fb.group({
       hideRequiredMarker: false,
       floatLabel: 'never',
    }); }
@ViewChild(MatPaginator) paginator: MatPaginator;
  ngOnInit() {


  this.goldcardService.getCard().subscribe(data =>{
                  this.card = data;
                  console.log(this.card);
            });
this.goldcardService.getRightRegistration().subscribe(data =>{
                  this.rightregistrations = data;
                  console.log(this.rightregistrations);
            });


   this.goldcardService.getPassAcceptToUser().subscribe(data => {
      this.PassAcceptToUser = data;
      this.dataSourceAccepted.data = data;
console.log(this.PassAcceptToUser);
    });
this.dataSourceAccepted.paginator = this.paginator;

this.goldcardService.getExpenses().subscribe(data => {
      this.Expensess = data;

console.log(this.Expensess);
    });
  }
Add(){
      if (this.input.Cardcode === '' || this.input.Comment === '' || this.input.Expenses === '' || this.input.AcceptId === '') {
      alert('กรุณากรอกข้อมูลให้ครบถ้วน');
    } else {
      this.httpClient.post("http://localhost:8080/Card/add/"+this.input.Cardcode+","+this.pipe.transform(this.CurrentDate,'dd:MM:yyyy')+","+this.input.Comment+
      ","+this.input.AcceptId+","+ this.input.Expenses, this.input)
      .subscribe(
          data => {
              console.log('Put data Request is successful', data);
//alert("บันทึกแล้วเรียบร้อย");

this.alertService.success('บันทึกแล้วเรียบร้อย')
        this.router.navigate(['/reload/Card']);

          },
          error => {
              alert("AcceptId ไม่ตรงกับฐานข้อมูล");
              console.log('Error', error);
          }
      );
    }
}
reload(){
wait(3000);
 this.router.navigate(['/reload/Card']);
}
Delete(){
if (this.input.DeleteSelect === '') {
      alert('กรุณากรอกข้อมูลให้ครบถ้วน');
    } else {
 this.output.Delete ="ลบแล้วเรียบร้อย"
      this.httpClient.post("http://localhost:8080/deleteCard/"+ this.input.DeleteSelect, this.input)
      .subscribe(
          data => {
              console.log('Delete Request is successful', data);

            console.log(this.output.Delete);

             alert("แจ้งหายเรียบร้อย");



                this.reload();

          },
          error => {
              console.log('Error', error);
          }
      );
    }}




isValidFormSubmitted = null;

          cardForm = this.formbuilder.group({
          Comment_check : ['', [Validators.required, Validators.pattern('.{5,25}')]],
          Cardcode_check: ['', [Validators.required, Validators.pattern('card.+')]],
          AcceptId_check : ['', [Validators.required, Validators.pattern('\\d+')]],
          });


Check(){

if (this.input.Cardcode === '' || this.input.Comment === '' || this.input.Expenses === '' || this.input.AcceptId === '') {
      alert('กรุณากรอกข้อมูลให้ครบถ้วน');
    } else {
          this.isValidFormSubmitted = false;
          if (this.cardForm.invalid) {

                        if(this.cardForm.controls['Cardcode_check'].hasError('pattern')){
                                          alert("กรุณา กรอก Card code ขึ้นต้นด้วยคำว่า card ให้ถูกต้อง");

                        }
                        else if(this.cardForm.controls['Comment_check'].hasError('pattern')){

                                          alert("กรุณา กรอก comment ความยาว 5 - 25 ตัวอักษร ให้ถูกต้อง");
                        }else if(this.cardForm.controls['AcceptId_check'].hasError('pattern')){
                                          alert("กรุณา กรอก AcceptId เป็นตัวเลข ให้ถูกต้อง");

                        }
                        return;
          }
           else if (this.cardForm.valid){



                       this.Add();

                        this.isValidFormSubmitted = true;
           }

            this.isValidFormSubmitted = true;
   }


}


















  applyFilter(filterValue: string) {
    this.newMethod(filterValue);
  }


  private newMethod(filterValue: string) {
    this.dataSourceAccepted.filter = filterValue.trim().toLowerCase();
  }


}
