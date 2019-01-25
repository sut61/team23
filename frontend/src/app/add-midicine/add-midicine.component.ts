import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { DataSource } from '@angular/cdk/collections';
import { MatPaginator } from '@angular/material/paginator';
import { Observable } from 'rxjs/internal/Observable';
import { MatDialog, MatDialogConfig } from "@angular/material";
import { EditMidicineComponent } from '../edit-midicine/edit-midicine.component';
import { MidicineService } from '../midicine.service';
import { GoldcardService } from '../goldcard.service';
export interface Drug{
  drugId:number;
  typesOfDrugs:{
    typesOfDrugsName:string;
  }
  drugRegistration:{
    drugRegistrationName:string;
  }
  typesOfDosageForms:{
    typesOfDosageFormsName:string;
  }
  disease:{
    diseaseName:string;
  }
  drugName:string;
  }
  export class MatTableDataSource extends DataSource<any>{
  
    constructor(private midicineService:MidicineService){
      super();
    }
    connect(): Observable<Drug[]>{
      return this.midicineService.getShow();
    }
    disconnect(){}
  }
@Component({
  selector: 'app-add-midicine',
  templateUrl: './add-midicine.component.html',
  styleUrls: ['./add-midicine.component.css']
})
export class AddMidicineComponent implements OnInit {
  displayedColumns: string[] = ['drugId', 'drugName', 'typesOfDrugs','drugRegistration','typesOfDosageForms','disease',"actions"];
  dataSource = new MatTableDataSource(this.midicineService);
  drug: Array<any>;//คืออะไร
  @ViewChild(MatPaginator) paginator: MatPaginator;

  

  // applyFilter(filterValue: string) {
  //   this.newMethod(filterValue);
  // }
  // private newMethod(filterValue: string) {
  //   this.dataSource.filter = filterValue.trim().toLowerCase();
  // }

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
  /** --------------------------------------------------------------------------------------------- */
  
  
  constructor(private midicineService: MidicineService , private httpClient: HttpClient ,private dialog: MatDialog,private goldcardService:GoldcardService) { }

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
  onSave(){
    console.log(this.view.drug);
    console.log(this.view.typesOfDrugs);
    console.log(this.view.drugRegistration);
    console.log(this.view.typesOfDosageForms);
    console.log(this.view.diseases);
    this.httpClient.post('http://localhost:8080/Drug/'+ this.view.drug + '/'+ this.view.typesOfDrugs +
    '/'+ this.view.drugRegistration +'/' + this.view.typesOfDosageForms + '/' + this.view.diseases,this.view)
    .subscribe
    (
      data =>{
      alert('บันทึกเรียบร้อย');
      console.log('Post Request is seccessful',data);
    },
    error=>{
      console.log('Rrror',error);
      alert('ไม่สามารถบันทึกได้ โปรดกรุณาใส่ข้อมูลให้ครบถ้วน');
    }
    );
  }
  onDelete(drugId){
    this.httpClient.delete('http://localhost:8080/Drug/'+ drugId )
    .subscribe
    (
      data =>{
      alert('ลบเรียบร้อย');
      console.log('Delete Request is seccessful',data);
      window.location.reload();
    },
    error=>{
      console.log('Rrror',error);
      alert('ไม่สามารถลบได้ โปรดกรุณาตรวจสอบข้อมูลอีกครั้ง');
    }
    );
  }
  onEdit(drugId){
    this.midicineService.getMidicine(drugId);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(EditMidicineComponent,dialogConfig);
  }

}
