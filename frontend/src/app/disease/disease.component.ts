import { Component, OnInit,ViewChild,Inject } from '@angular/core';
import {DatePipe} from '@angular/common';
import { GoldcardService } from '../goldcard.service';
import {ErrorStateMatcher} from '@angular/material/core';
import {map, startWith} from 'rxjs/operators';
import {Observable} from 'rxjs';
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import { HttpClient} from '@angular/common/http';
import {FormBuilder, FormGroup} from '@angular/forms';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import {MatPaginator, MatTableDataSource} from '@angular/material';
import { DataSource } from '@angular/cdk/collections';
import { AlertService } from '../alert.service';
import {MatBottomSheet, MatBottomSheetRef} from '@angular/material';
import { Dis } from './dis';

export interface DiseaseElement {
diseaseName: string;
typeDiseaseName: string;
populationRate: string;
remedy: string;
}
@Component({
  selector: 'app-disease',
  templateUrl: './disease.component.html',
  styleUrls: ['./disease.component.css']
})
export class DiseaseComponent implements OnInit {
  displayedColumns: string[] = ['diseaseName', 'typeDiseaseName', 'populationRate','symptom', 'remedy'];
  filter : '';

  disease : Array<any>;
  dataSourceDisease = new MatTableDataSource<DiseaseElement>(this.disease);
  @ViewChild(MatPaginator) paginator: MatPaginator;

  peopledisease : Array<any>;
  typedisease : Array<any>;
  input: any = {
      diseaseName: '',
      symptom: '',
      cause: '',
      remedy: '',
    };
  select: any = {
      typeDiseaseName: '',
      populationRate: '',
    };
    pipe = new DatePipe('en-US');

    constructor(private formbuilder:FormBuilder,private alertService:AlertService, private goldcardService: GoldcardService , private bottomSheet: MatBottomSheet, private httpClient: HttpClient,private route: ActivatedRoute,
   private router: Router ){

  }
  isValidFormSubmitted = null;
  NamePattern = "(โรค).+";
  Symptom = ".{5,40}";
    myForm = this.formbuilder.group({
    diseaseName_no : ['', [Validators.required, Validators.pattern(this.NamePattern)]],
    symptom_no: ['', [Validators.required, Validators.pattern(this.Symptom)]],
    cause_no: ['', [Validators.required, Validators.pattern('')]],
    remedy_no : ['', [Validators.required, Validators.pattern('')]],
    typeDisease_no : ['', [Validators.required, Validators.pattern('')]],
    populationRate_no : ['', [Validators.required, Validators.pattern('')]],
    });

    onFormSubmit(){
        this.isValidFormSubmitted = false;
        if (this.myForm.invalid) {
                  this.alertService.error('กรุณา กรอกข้อมูล ให้ครบถ้วน');
                  if(this.myForm.controls['diseaseName_no'].hasError('pattern')){
                                  this.alertService.error('กรุณากรอก โรค นำหน้า');
                  }else if(this.myForm.controls['symptom_no'].hasError('pattern')){
                                  this.alertService.error('กรุณากรอกข้อมูล (5-40) ตัว');
                  }


                  return;
        }
        else if (this.myForm.valid){
                this.diseases(this.myForm.value);
                this.isValidFormSubmitted = true;
        }

         this.isValidFormSubmitted = true;
    }


  diseases(dis : Dis){
       // http://localhost:8080//Disease/{diseaseName}/{typeDiseaseName}/{populationRate}/{symptom}/{cause}/{remedy}
      this.httpClient.post('http://localhost:8080/Disease/'+dis.diseaseName_no+'/'+dis.typeDisease_no+'/'+dis.populationRate_no+'/'+ dis.symptom_no+'/'+dis.cause_no+'/'+ dis.remedy_no,dis)
      .subscribe(
          data => {
                        console.log('บันทึกโรคเรียบร้อย', data);
                        this.bottomSheet.open(Success);
                        this.router.navigate(['/reload/Disease']);

                    },
          error => {
                        console.log('Error', error);
                        this.alertService.error('ชื่อโรคนี้มีในระบบแล้ว');
                  }
                );
  }

  ngOnInit() {
    this.goldcardService.getDisease().subscribe(data =>{
            this.disease = data;
            console.log(this.disease);
            this.dataSourceDisease.data = data;

      });
    this.goldcardService.getTypeDisease().subscribe(data =>{
            this.typedisease = data;
            console.log(this.typedisease);
      });
    this.goldcardService.getPeopleDisease().subscribe(data =>{
            this.peopledisease = data;
            console.log(this.peopledisease);
      });
        this.dataSourceDisease.paginator = this.paginator;

  }

  applyFilter(filterValue: string) {
    this.newMethod(filterValue);
  }


  private newMethod(filterValue: string) {
    this.dataSourceDisease.filter = filterValue.trim().toLowerCase();
  }
}


@Component({
  selector: 'success',
  templateUrl: 'success.html',
})
export class Success {
  constructor(private bottomSheetRef: MatBottomSheetRef<Success>) {}

  openLink(event: MouseEvent): void {
    this.bottomSheetRef.dismiss();
    event.preventDefault();
  }

}
