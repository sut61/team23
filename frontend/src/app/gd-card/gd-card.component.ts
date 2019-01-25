import { Component, OnInit } from '@angular/core';
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
import { AlertService } from '../alert.service';
import { AuthService } from '../auth/auth.service';
import {MatTableDataSource} from '@angular/material';

@Component({
  selector: 'app-gd-card',
  templateUrl: './gd-card.component.html',
  styleUrls: ['./gd-card.component.css']
})
export class GDCardComponent implements OnInit {

  hospitals : Array<any>;
  members : Array<any>;
  officers : Array<any>;
  select: any = {
          officername: '',
          hospitalname: '',
          membername:  '',
    };
  input: any = {
          detail: ''
  }


  constructor(private goldcardService: GoldcardService, private httpClient: HttpClient,private route: ActivatedRoute,
   private router: Router ) {}

  save(){
  // http://localhost:8080/Goldcard/{detail}/{officerName}/{HospialName}/{MemberName}
              this.httpClient.post('http://localhost:8080/Goldcard/'+this.input.detail+'/'+this.select.officerName+'/'+this.select.hospitalname+'/'+this.select.membername,this.input)
                .subscribe(
                    data => {
                        console.log('PUT Request is successful', data);
                        alert('บันทึกเรียบร้อยแล้ว');
this.router.navigate(['/reload/GDCard']);
                    },
                    error => {
                        console.log('Error', error);
                        alert('กรอกข้อมูลผิดพลาด โปรดตรวจสอบใหม่');
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

  }

}
