import {Component, OnInit, ViewChild,Inject} from '@angular/core';
import { GoldcardService } from '../goldcard.service';
import { HttpClient} from '@angular/common/http';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { AlertService } from '../alert.service';
import { AuthService } from '../auth/auth.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import { DataSource } from '@angular/cdk/collections';
import {MatPaginator, MatTableDataSource} from '@angular/material';

export interface HospitalElement {
  hospitalName: string;
  branceFive: number;
  hospitalAddress: string;
  provinceName: string;
  hospitalPhone: string;

  }
  
@Component({
  selector: 'app-hospital-register',
  templateUrl: './hospital-register.component.html',
  styleUrls: ['./hospital-register.component.css']
})
export class HospitalRegisterComponent implements OnInit {

  displayedColumns: string[] = ['branceFive', 'hospitalName', 'hospitalAddress', 'provinceName', 'hospitalPhone'];
  filter : '';

  hospitalshow : Array<any>;

  dataSourceHospital = new MatTableDataSource<HospitalElement>(this.hospitalshow);
  @ViewChild(MatPaginator) paginator: MatPaginator;

  
  


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

  constructor(private alertService : AlertService,private authService: AuthService,fb: FormBuilder,private goldcardService: GoldcardService, private httpClient: HttpClient,private router: Router){
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
        this.alertService.success('เพิ่มเรียบร้อย');
      },
      error => {
          console.log('Error', error);
          this.alertService.error('Error มีปัญหา');    
        }
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
    this.goldcardService.getHospital().subscribe(data => {
      this.hospitalshow = data;
      this.dataSourceHospital.data = data;
    });
    this.dataSourceHospital.paginator = this.paginator;
  }
  

  

  applyFilter(filterValue: string) {
    this.newMethod(filterValue);
  }


  private newMethod(filterValue: string) {
    this.dataSourceHospital.filter = filterValue.trim().toLowerCase();
  }


}
