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
import { AuthService } from '../auth/auth.service';
import { AlertService } from '../alert.service';
import {MatPaginator, MatTableDataSource} from '@angular/material';


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

  constructor(private alertService:AlertService,private authService: AuthService,fb: FormBuilder,private goldcardService: GoldcardService, private httpClient: HttpClient,private router: Router){
       this.lock = fb.group({
          hideRequiredMarker: false,
          floatLabel: 'never',
       });
  }

save(){
this.alertService.clear();
  // http://localhost:8080/Publicz/{publiczHead}/{PublicizeDetail}/{date_reg}/{officerName}/{hospialName}/{TypeOfPublicz}/{email}/{call}
        this.httpClient.post('http://localhost:8080/Publicz/'+this.input.publiczHead+'/'+this.input.PublicizeDetail+'/'+this.pipe.transform(this.CurrentDate,'dd:MM:yyyy')+'/'+this.select.officerName+'/'+this.select.hospitalname+'/'+this.select.typeofpub+'/'+this.input.email+'/'+this.input.call
        ,this.input)
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
