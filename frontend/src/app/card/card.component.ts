import { Component, OnInit, ViewChild,Inject } from '@angular/core';
import { GoldcardService } from '../goldcard.service';
import { HttpClient} from '@angular/common/http';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { AlertService } from '../alert.service';
import { AuthService } from '../auth/auth.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import { DataSource } from '@angular/cdk/collections';
import {MatPaginator, MatTableDataSource} from '@angular/material';
import {DatePipe} from '@angular/common';

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


input: any = {
AcceptId: '',
Cardcode: '',
Comment: '',
date: '',
Expenses: '',
status: '',
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
constructor(private alertService : AlertService,private authService: AuthService,fb: FormBuilder,private goldcardService: GoldcardService, private httpClient: HttpClient,private router: Router){
    this.lock = fb.group({
       hideRequiredMarker: false,
       floatLabel: 'never',
    }); }
@ViewChild(MatPaginator) paginator: MatPaginator;
  ngOnInit() {


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
      this.httpClient.post("http://localhost:8080/Card/add/"+this.input.Cardcode+","+this.pipe.transform(this.CurrentDate,'dd:MM:yyyy')+","+this.input.Comment+
      ","+this.input.AcceptId+","+ this.input.Expenses, this.input)
      .subscribe(
          data => {
              console.log('Put data Request is successful', data);
alert("บันทึกแล้วเรียบร้อย");
        this.router.navigate(['/reload/Card']);

          },
          error => {
              console.log('Error', error);
          }
      );
    }



  applyFilter(filterValue: string) {
    this.newMethod(filterValue);
  }


  private newMethod(filterValue: string) {
    this.dataSourceAccepted.filter = filterValue.trim().toLowerCase();
  }


}
