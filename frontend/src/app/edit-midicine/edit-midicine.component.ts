import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';

import { HttpClient } from '@angular/common/http'
import { MidicineService } from '../midicine.service';
import { GoldcardService } from '../goldcard.service';
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

  constructor(private midicineService:MidicineService,
              private httpClient: HttpClient,
              private goldcardService:GoldcardService,
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
     onUpdate(){
         console.log(this.drugId);
                this.httpClient.put('http://localhost:8080/Drug/'+ this.drugId +'/'+ this.view.drug + '/'+ this.view.typesOfDrugs +'/'+ this.view.drugRegistration +'/' + this.view.typesOfDosageForms + '/' + this.view.diseases,this.view)
                .subscribe
                (
                  data =>{
                  alert('อัพเดตเรียบร้อย');
                  console.log('Post Request is seccessful',data);
                },
                error=>{
                  console.log('Rrror',error);
                  alert('ไม่สามารถบันทึกได้ โปรดกรุณาใส่ข้อมูลให้ครบถ้วน');
                }
                );
                this.dialogRef.close();
              }
                
              onCancel(){
               
                this.dialogRef.close();
              }
            }

