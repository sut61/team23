import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

import { HttpClient } from '@angular/common/http'
import { MidicineService } from '../midicine.service';
import { GoldcardService } from '../goldcard.service';
import { Validators, FormBuilder } from '@angular/forms';
import { EditMidicine } from './editMidicine';
import { AlertService } from '../alert.service';
@Component({
  selector: 'app-edit-midicine',
  templateUrl: './edit-midicine.component.html',
  styleUrls: ['./edit-midicine.component.css']
})
export class EditMidicineComponent implements OnInit {
  Drug: Array<any>;
  TypesOfDrugs: Array<any>;
  DrugRegistration: Array<any>;
  TypesOfDosageForms: Array<any>;
  Diseases: Array<any>;
  view: any={
    drug:null,
    typesOfDrugs:null,
    drugRegistration:null,
    typesOfDosageForms:null,
    diseases:null
  }
  drugId=this.midicineService.getIdMidicine();
   
  isValidFormSubmitted = null;
  drugs = "[a-zA-Z]{2,30}";
  typesOfDrugs = ".{2,5}";
  drugRegistration = ".{2,30}";
  typesOfDosageForms = ".{2,10}";
  diseases = ".{2,30}";
  myForm = this.formbuilder.group({
    drugs_no: ['', [Validators.required, Validators.pattern(this.drugs)]],
    typesOfDrugs_no: ['', [Validators.required, Validators.pattern('')]],
    drugRegistration_no: ['', [Validators.required, Validators.pattern('')]],
    typesOfDosageForms_no: ['', [Validators.required, Validators.pattern('')]],
    diseases_no: ['', [Validators.required, Validators.pattern('')]],
  });
  onFormSubmit() {
    this.isValidFormSubmitted = false;
    if (this.myForm.invalid) {
    
      if (this.myForm.controls['drugs_no'].hasError('pattern')) {
        this.alertService.error('ชื่อยาต้องเป็นภาษาอังกฤษเท่านั้น และมีความยาวตัวอักษรอย่างน้อย 2 - 30 ตัว');

      }else if (this.view.drug == null) {
        this.alertService.error('กรุณากรอกชื่อยา');
      }
      else if (this.view.drugRegistration == null) {
        this.alertService.error('กรุณาเลือกประเภทยา');
      }
      else if (this.view.drugRegistration == null) {
        this.alertService.error('กรุณาเลือกทะเบียนยา');
      }
      else if (this.view.typesOfDosageForms == null) {
        this.alertService.error('กรุณาเลือกรูปแบบผลิตภัณฑ์');
      }
      else if (this.view.diseases == null) {
        this.alertService.error('กรุณาเลือกโรค');
      }


      return;
    }
    else if (this.myForm.valid) {
      this.onUpdate(this.myForm.value);
      this.isValidFormSubmitted = true;
      
    }

    this.isValidFormSubmitted = true;
  }
  
  constructor(private midicineService:MidicineService,
              private httpClient: HttpClient,
              private goldcardService:GoldcardService,private route: ActivatedRoute,
              private formbuilder: FormBuilder,
              private alertService: AlertService,
   private router: Router ,
              public dialogRef: MatDialogRef<EditMidicineComponent>) { }
              ngOnInit() {
                this.goldcardService.getDrug().subscribe(drug => {
                  this.Drug = drug;
                  console.log(this.Drug);
                  
                });
                this.midicineService.getTypesOfDrugs().subscribe(typesOfDrugs => {
                  this.TypesOfDrugs = typesOfDrugs;
                  console.log(this.TypesOfDrugs);
                });
                this.midicineService.getDrugRegistration().subscribe(drugRegistration => {
                  this.DrugRegistration = drugRegistration;
                  console.log(this.DrugRegistration);
                });
                this.midicineService.getTypesOfDosageForms().subscribe(typesOfDosageForms => {
                  this.TypesOfDosageForms = typesOfDosageForms;
                  console.log(this.TypesOfDosageForms);
                });
                this.goldcardService.getDisease().subscribe(diseases => {
                  this.Diseases = diseases;
                  console.log(this.Diseases);
                  
                });
            
              }
     onUpdate(editMidicine:EditMidicine){
        console.log(editMidicine.drugs_no);
        console.log(editMidicine.typesOfDrugs_no);
        console.log(editMidicine.drugRegistration_no);
        console.log(editMidicine.typesOfDosageForms_no);
        console.log(editMidicine.diseases_no);
     

         console.log(this.drugId);
                this.httpClient.put('http://localhost:8080/Drug/'+ this.midicineService.getIdMidicine() +
                 '/' + editMidicine.drugs_no +
                 '/' + editMidicine.typesOfDrugs_no +
                 '/' + editMidicine.drugRegistration_no +
                 '/' + editMidicine.typesOfDosageForms_no + 
                 '/' + editMidicine.diseases_no,editMidicine)
                .subscribe
                (
                  data =>{
                  this.alertService.success('อัพเดตข้อมูลยาเสร็จสิ้น');
                  console.log('put Request is seccessful',data);
                  
                },
                error=>{
                  console.log('Rrror',error);
                  this.alertService.error('ไม่สามารถบันทึกได้ โปรดกรุณาใส่ข้อมูลให้ครบถ้วน');
                }
                );
                this.dialogRef.close();
              }
  onCancel(){
    this.dialogRef.close();
  }
}

