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

    constructor(private alertService:AlertService, private goldcardService: GoldcardService , private bottomSheet: MatBottomSheet, private httpClient: HttpClient,private route: ActivatedRoute,
   private router: Router ){

  }

  diseases(){
       // http://localhost:8080//Disease/{diseaseName}/{typeDiseaseName}/{populationRate}/{symptom}/{cause}/{remedy}
      this.httpClient.post('http://localhost:8080/Disease/'+this.input.diseaseName+'/'+this.select.typeDiseaseName+'/'+this.select.populationRate+'/'+ this.input.symptom+'/'+this.input.cause+'/'+ this.input.remedy,this.input)
      .subscribe(
          data => {
                        console.log('บันทึกโรคเรียบร้อย', data);
                        this.bottomSheet.open(Success);
                        this.router.navigate(['/reload/Disease']);

                    },
          error => {
                        console.log('Error', error);
                        this.alertService.error('กรุณากรอกข้อมูลให้ครบถ้วน');
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
