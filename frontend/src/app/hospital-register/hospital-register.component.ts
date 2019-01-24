import { Component, OnInit } from '@angular/core';
import { GoldcardService } from '../goldcard.service';
import { HttpClient} from '@angular/common/http';
import {MatTableDataSource} from '@angular/material';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { AlertService } from '../alert.service';
import { AuthService } from '../auth/auth.service';
import {FormBuilder, FormGroup} from '@angular/forms';



@Component({
  selector: 'app-hospital-register',
  templateUrl: './hospital-register.component.html',
  styleUrls: ['./hospital-register.component.css']
})
export class HospitalRegisterComponent implements OnInit {


  affiliations: Array<any>;
  typehospitals: Array<any>;
  provinces: Array<any>;

  input: any = {
    branceNine: '',
    branceFive: '',
    hospitalName: '',
    hospitalAddress: '',
    hospitalPostcode: '',
    hospitalPhone: '',
  };

  select: any = {
    provinceName: '',
    typeName: '',
    affiliationName: '',
  };


  lock: FormGroup;

  constructor(private alertService:AlertService,private authService: AuthService,fb: FormBuilder,private goldcardService: GoldcardService, private httpClient: HttpClient,private router: Router){
    this.lock = fb.group({
       hideRequiredMarker: false,
       floatLabel: 'never',
    });
}
hospitalregis(){
    // http://localhost:8080/addhospital/{branceNine}/{branceFive}/{hospitalName}/{affiliationName}/{typeName}/{hospitalAddress}/{provinceName}/{hospitalPostcode}/{hospitalPhone}
    this.httpClient.post('http://localhost:8080/addhospital/'+this.input.branceNine+'/'+this.input.branceFive+'/'+this.input.hospitalName+'/'+this.select.affiliationName+'/'+this.select.typeName+'/'+this.input.hospitalAddress+'/'+this.select.provinceName+'/'+this.input.hospitalPostcode+'/'+this.input.hospitalPhone,this.input)
    .subscribe(
      data => {
        console.log("add  success", data);
        alert("ลงทะเบียนเรียบร้อย");
        window.location.reload();

      },
      error => {
          console.log('Error', error);
          alert("ไม่สามารถลงทะเบีบยโรงพยาบาลได้");      }
   );
  }
  ngOnInit() {
    this.goldcardService.getAffiliation().subscribe(data => {
      this.affiliations = data;
    });
    this.goldcardService.getProvince().subscribe(data => {
      this.provinces = data;
    });
    this.goldcardService.getTypeHospital().subscribe(data => {
      this.typehospitals = data;
    });
  }
}
