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
import { AlertService } from '../alert.service';
import {MatBottomSheet, MatBottomSheetRef} from '@angular/material';

import { Th } from './th';

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
      code: '',
      treatDate: '',
    };
  select: any = {
      drugName: '',
      goldcardName: '',
      diseaseName: '',
    };
    pipe = new DatePipe('en-US');

    constructor(private formbuilder:FormBuilder,private bottomSheet: MatBottomSheet,private alertService:AlertService,private goldcardService: GoldcardService, private httpClient: HttpClient,private route: ActivatedRoute,
   private router: Router ){

  }
  isValidFormSubmitted = null;
  codePattern = "\\d{7,9}TH";
    myForm = this.formbuilder.group({
    code_no : ['', [Validators.required, Validators.pattern(this.codePattern)]],
    goldcardName_no: ['', [Validators.required, Validators.pattern('')]],
    diseaseName_no: ['', [Validators.required, Validators.pattern('')]],
    drugName_no : ['', [Validators.required, Validators.pattern('')]],
    treatDate_no : ['', [Validators.required, Validators.pattern('')]],
    });

    onFormSubmit(){
        this.isValidFormSubmitted = false;
        if (this.myForm.invalid) {
                  this.alertService.error('กรุณา กรอกข้อมูล ให้ครบถ้วน');
                  if(this.myForm.controls['code_no'].hasError('pattern')){
                                  this.alertService.error('กรุณากรอกตัวเลข 7-9 ตัว + TH');
                  }


                  return;
        }
        else if (this.myForm.valid){
                this.treatment(this.myForm.value);
                this.isValidFormSubmitted = true;
        }

         this.isValidFormSubmitted = true;
    }
  treatment(th : Th){
       // http://localhost:8080/Treatmenthistory/{goldcardName}/{diseaseName}/{drugName}/{code}/{treatDate}
      this.httpClient.post('http://localhost:8080/Treatmenthistory/'+th.code_no+'/'+th.goldcardName_no+'/'+th.diseaseName_no+'/'+th.drugName_no+'/'+this.pipe.transform(th.treatDate_no,'dd:MM:yyyy'),th)
      .subscribe(
          data => {
                        console.log('บันทึกประวัติเรียบร้อย', data);
                        this.bottomSheet.open(Thpass);
                        this.router.navigate(['/reload/TreatmentHistory']);
                    },
          error => {
                        console.log('Error', error);
                        this.alertService.error('ในระบบมี code นี้อยู่แล้วกรุณาเปลี่ยน code ');
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
@Component({
  selector: 'thpass',
  templateUrl: 'thpass.html',
})
export class Thpass {
  constructor(private bottomSheetRef: MatBottomSheetRef<Thpass>) {}

  openLink(event: MouseEvent): void {
    this.bottomSheetRef.dismiss();
    event.preventDefault();
  }
}
