import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatDialog, MatDialogConfig, MatDialogRef } from "@angular/material";
import { Router } from '@angular/router';
import { MedicalsuppliesService } from '../medicalsupplies.service';
import { AlertService } from '../alert.service';
import { FormBuilder, Validators } from '@angular/forms';
import { EditMedical } from './editMidical';

@Component({
  selector: 'app-edit-medical-supplies',
  templateUrl: './edit-medical-supplies.component.html',
  styleUrls: ['./edit-medical-supplies.component.css']
})
export class EditMedicalSuppliesComponent implements OnInit {
MedicalSupplies: Array<any>;
MedicalInstrument:Array<any>
Useability:Array<any>;
view: any={
  codeNumber:null,
  modelNumber:null,
  medicalSupplies:null,
  brandName:null,
  properties:null,
  medicalInstrument:null,
  useability:null
}
medicalsuppliesId=this.medicalsuppliesService.getIdMedicalSupplies();

isValidFormSubmitted = null;
codeNumber = "(C).+";
modelNumber = ".{1,5}";
medicalSupplies = ".{2,30}";
brandName = ".{2,10}";
properties = ".{2,30}";
myForm = this.formbuilder.group({
  codeNumber_no: ['', [Validators.required, Validators.pattern(this.codeNumber)]],
  modelNumber_no: ['', [Validators.required, Validators.pattern(this.modelNumber)]],
  medicalSupplies_no: ['', [Validators.required, Validators.pattern(this.medicalSupplies)]],
  brandName_no: ['', [Validators.required, Validators.pattern(this.brandName)]],
  properties_no: ['', [Validators.required, Validators.pattern(this.properties)]],
  medicalInstrument_no: ['', [Validators.required, Validators.pattern('')]],
  useability_no: ['', [Validators.required, Validators.pattern('')]],


});

onFormSubmit() {
  this.isValidFormSubmitted = false;
  if (this.myForm.invalid) {
    this.alertService.error('กรุณา กรอกข้อมูล ให้ครบถ้วน');
    if (this.myForm.controls['codeNumber_no'].hasError('pattern')) {
      this.alertService.error('กรุณากรอก C นำหน้า');
    }
    else if (this.myForm.controls['modelNumber_no'].hasError('pattern')) {
      this.alertService.error('กรุณากรอกข้อมูล (1-5) ตัว');
    }
    else if (this.myForm.controls['medicalSupplies_no'].hasError('pattern')) {
      this.alertService.error('กรุณากรอกข้อมูล (2-30) ตัว');
    }
    else if (this.myForm.controls['brandName_no'].hasError('pattern')) {
      this.alertService.error('กรุณากรอกข้อมูล (2-30) ตัว');
    }
    else if (this.myForm.controls['properties_no'].hasError('pattern')) {
      this.alertService.error('กรุณากรอกข้อมูล (2-30) ตัว');
    }
    else if (this.myForm.controls['medicalInstrument_no'].hasError('pattern')) {
      this.alertService.error('กรุณาเลือกอุปกรณ์การแพทย์');
    }
    else if (this.myForm.controls['useability_no'].hasError('pattern')) {
      this.alertService.error('กรุณาเลือกการใช้งาน');
    }




    return;
  }
  else if (this.myForm.valid) {
    this.onUpdate(this.myForm.value);
    this.isValidFormSubmitted = true;
    this.alertService.success("บันทึกข้อมูลเวชภัณฑ์ สำเร็จ");
  }

  this.isValidFormSubmitted = true;
}


  constructor(private medicalsuppliesService:MedicalsuppliesService, 
    private httpClient:HttpClient ,
    private dialog: MatDialog,
    private formbuilder: FormBuilder,
    private router: Router ,
    private alertService: AlertService,
              public dialogRef: MatDialogRef<EditMedicalSuppliesComponent>) { }

  ngOnInit() {
    this.medicalsuppliesService.getMedicalSupplies().subscribe(medicalSupplies => {
      this.MedicalSupplies = medicalSupplies;
      console.log(this.MedicalSupplies);
    });
    this.medicalsuppliesService.getMedicalInstrument().subscribe(medicalInstrument => {
      this.MedicalInstrument = medicalInstrument;
      console.log(this.MedicalInstrument);
    });
    this.medicalsuppliesService.getUseability().subscribe(useability => {
      this.Useability = useability;
      console.log(this.Useability);
    });

  }
onUpdate(edit:EditMedical){
    console.log(this.medicalsuppliesId);
    console.log(this.view.codeNumber);
    console.log(this.view.modelNumber);
    console.log(this.view.medicalSupplies);
    console.log(this.view.brandName);
    console.log(this.view.properties);
    console.log(this.view.medicalInstrument);
    console.log(this.view.useability);
    
    this.httpClient.put('http://localhost:8080/MedicalSupplies/' +this.medicalsuppliesId+'/'+ this.view.codeNumber +  
    '/'+ this.view.modelNumber + 
    '/'+ this.view.medicalSupplies +
    '/'+ this.view.brandName + 
    '/'+ this.view.properties + 
    '/'+ this.view.medicalInstrument +
    '/'+ this.view.useability,this.view)
    .subscribe
    (
      data =>{
      alert('บันทึกเรียบร้อย');
      console.log('Post Request is seccessful',data);
      window.location.reload();
    },
    error=>{
      console.log('Rrror',error);
      alert('ไม่สามารถบันทึกได้ โปรดกรุณาใส่ข้อมูลอีกครั้ง');
    }
    );
  }
onCancel(){
this.dialogRef.close();
}
}
