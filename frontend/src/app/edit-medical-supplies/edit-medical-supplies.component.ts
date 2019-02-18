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


isValidFormSubmitted = null;
codeNumber = "(C).+";
modelNumber = ".{2,5}";
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
    }else if (this.view.codeNumber == null) {
      this.alertService.error('กรุณากรอกรหัสเวชภัณฑ์');
    }
    else if (this.view.modelNumber == null) {
      this.alertService.error('กรุณากรอกหมายเลขรุ่น');
    }
    else if (this.view.medicalSupplies == null) {
      this.alertService.error('กรุณากรอกชื่อเวชภัณฑ์');
    }
    else if (this.view.brandName == null) {
      this.alertService.error('กรุณากรอกชื่อแบรนด์');
    }
    else if (this.view.properties == null) {
      this.alertService.error('กรุณากรอกคุณสมบัติ');
    }
    else if (this.view.medicalInstrument == null) {
      this.alertService.error('กรุณาเลือกอุปกรณ์การแพทย์');
    }
    else if (this.view.useability == null) {
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



  constructor(public medicalsuppliesServices:MedicalsuppliesService, 
    private httpClient:HttpClient ,
    private dialog: MatDialog,
    private formbuilder: FormBuilder,
    private router: Router ,
    private alertService: AlertService,
              public dialogRef: MatDialogRef<EditMedicalSuppliesComponent>) { }

  ngOnInit() {
    this.medicalsuppliesServices.getMedicalSupplies().subscribe(medicalSupplies => {
      this.MedicalSupplies = medicalSupplies;
      console.log(this.MedicalSupplies);
    });
    this.medicalsuppliesServices.getMedicalInstrument().subscribe(medicalInstrument => {
      this.MedicalInstrument = medicalInstrument;
      console.log(this.MedicalInstrument);
    });
    this.medicalsuppliesServices.getUseability().subscribe(useability => {
      this.Useability = useability;
      console.log(this.Useability);
    });

  }
onUpdate(editMidical:EditMedical){
    this.medicalsuppliesServices.getIdMedicalSupplies();
    console.log(this.medicalsuppliesServices.getIdMedicalSupplies());
    console.log(editMidical.codeNumber_no);
    console.log(editMidical.modelNumber_no);
    console.log(editMidical.medicalSupplies_no);
    console.log(editMidical.brandName_no);
    console.log(editMidical.properties_no);
    console.log(editMidical.medicalInstrument_no);
    console.log(editMidical.useability_no);
    
    this.httpClient.put('http://localhost:8080/MedicalSupplies/' +this.medicalsuppliesServices.getIdMedicalSupplies() +'/'+ editMidical.codeNumber_no +  
    '/'+ editMidical.modelNumber_no + 
    '/'+ editMidical.medicalSupplies_no +
    '/'+ editMidical.brandName_no + 
    '/'+ editMidical.properties_no + 
    '/'+ editMidical.medicalInstrument_no +
    '/'+ editMidical.useability_no,editMidical)
    .subscribe
    (
      data =>{
      alert('บันทึกเรียบร้อย');
      console.log('Put Request is seccessful',data);
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
