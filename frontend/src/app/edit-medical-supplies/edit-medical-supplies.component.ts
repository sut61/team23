import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatDialog, MatDialogConfig, MatDialogRef } from "@angular/material";
import { Router } from '@angular/router';
import { MedicalsuppliesService } from '../medicalsupplies.service';

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
  constructor(private medicalsuppliesService:MedicalsuppliesService, private httpClient:HttpClient ,private dialog: MatDialog,private router: Router ,
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
onUpdate(){
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
