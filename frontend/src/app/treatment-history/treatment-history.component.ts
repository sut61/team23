import { Component, OnInit } from '@angular/core';
import {DatePipe} from '@angular/common';
import { GoldcardService } from '../goldcard.service';
import {ErrorStateMatcher} from '@angular/material/core';
import {map, startWith} from 'rxjs/operators';
import {Observable} from 'rxjs';
import {FormControl, FormGroupDirective, NgForm, Validators} from '@angular/forms';
import { HttpClient} from '@angular/common/http';
import {FormBuilder, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-treatment-history',
  templateUrl: './treatment-history.component.html',
  styleUrls: ['./treatment-history.component.css']
})
export class TreatmentHistoryComponent implements OnInit {
  diseases : Array<any>;
  drugs : Array<any>;
  goldcards : Array<any>;
  treatmenthistorys : Array<any>;
  input: any = {
      treatDate: '',
    };
  select: any = {
      drugName: '',
      goldcardName: '',
      diseaseName: '',
    };
    pipe = new DatePipe('en-US');

    constructor(private goldcardService: GoldcardService, private httpClient: HttpClient){

  }
  treatment(){
       // http://localhost:8080/Treatmenthistory/{goldcardName}/{diseaseName}/{drugName}/{treatDate}
      this.httpClient.post('http://localhost:8080/Treatmenthistory/'+this.select.goldcardName+'/'+this.select.diseaseName+'/'+this.select.drugName+'/'+this.pipe.transform(this.input.treatDate,'dd:MM:yyyy'),this.input)
      .subscribe(
          data => {
                        console.log('บันทึกประวัติเรียบร้อย', data);
                        alert('บันทึกประวัติเรียบร้อย');
                    },
          error => {
                        console.log('Error', error);
                        alert('กรอกข้อมูลให้ครบถ้วน');
                    }
                );
  }


  ngOnInit() {
    this.goldcardService.getDisease().subscribe(data =>{
            this.diseases = data;
            console.log(this.diseases);
      });
    this.goldcardService.getDrug().subscribe(data =>{
            this.drugs = data;
            console.log(this.drugs);
      });
    this.goldcardService.getGoldcard().subscribe(data =>{
            this.goldcards = data;
            console.log(this.goldcards);
      });
    this.goldcardService.getTreatmenthistory().subscribe(data =>{
            this.treatmenthistorys = data;
            console.log(this.treatmenthistorys);
});
}
}
