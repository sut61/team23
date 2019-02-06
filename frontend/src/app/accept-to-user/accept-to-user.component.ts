import {Component, OnInit, ViewChild,Inject} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CollectionViewer, DataSource} from "@angular/cdk/collections";
import {MatPaginator, MatTableDataSource} from '@angular/material';
import { GoldcardService } from '../goldcard.service';
import { AlertService } from '../alert.service';
import {DatePipe} from '@angular/common';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import {MatBottomSheet, MatBottomSheetRef} from '@angular/material';

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
}

@Component({
  selector: 'app-accept-to-user',
  templateUrl: './accept-to-user.component.html',
  styleUrls: ['./accept-to-user.component.css']
})
export class AcceptToUserComponent implements OnInit {

  displayedColumns: string[] = ['codeaccept','dateregis','username','name','tel','personalcard','hospitalname','provincename','rightstypename','status'];
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
  comment: '';
  hospitalName : string;
  constructor(private alertService:AlertService,private goldcardService: GoldcardService,private httpClient: HttpClient
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

  applyFilter(filterValue: string) {
      this.filterdataSource(filterValue);
    }

    private filterdataSource(filterValue: string) {
      this.dataSource.filter = filterValue.trim().toLowerCase();

    }

    showuser(row){
          if(row.comment == 'null')
                this.table.comment = "";
          else{this.table.comment = row.comment;}
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

    Accept(){
            this.alertService.clear();
  //      UpdateAccept/{id}/{acceptdate}/{codeAccept}/{comment}/{username}/{statusname}/{officername}
              this.httpClient.put('http://localhost:8080/UpdateAccept/'+this.table.id+'/'+this.pipe.transform(this.CurrentDate,'dd:MM:yyyy')+'/P'+this.table.id+'/'+this.table.username+'/'+this.comment+'/Pass/'+localStorage.getItem('currentUser'),this.accs)
                    .subscribe(
                        data => {
                            console.log('PUT Request is successful', data);
                            this.alertService.success('ยืนยัน เรียบร้อย');
                            this.bottomSheet.open(Sheet);
                            this.router.navigate(['/reload/AcceptToUser']);
                        },
                        error => {
                            console.log('Error', error);
                            this.alertService.error('Error กรุณาเลือก User ที่จะยืนยัน');

                        }
                    );
    }
    NotAccept(){
            this.alertService.clear();
      //      UpdateAccept/{id}/{acceptdate}/{codeAccept}/{comment}/{username}/{statusname}/{officername}
                  this.httpClient.put('http://localhost:8080/UpdateAccept/'+this.table.id+'/'+this.pipe.transform(this.CurrentDate,'dd:MM:yyyy')+'/F'+this.table.id+'/'+this.table.username+'/'+this.comment+'/Fail/'+localStorage.getItem('currentUser'),this.accs)
                        .subscribe(
                            data => {
                                console.log('PUT Request is successful', data);
                                this.bottomSheet.open(Sheetq);
                                this.router.navigate(['/reload/AcceptToUser']);
                            },
                            error => {
                                console.log('Error', error);
                                this.alertService.error('Error กรุณาเลือก User ที่จะยืนยัน');
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
