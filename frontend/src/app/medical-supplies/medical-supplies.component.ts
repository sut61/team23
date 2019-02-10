import { Component, OnInit ,ViewChild} from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatDialog, MatDialogConfig, MatTableDataSource } from "@angular/material";
import { HttpClient } from '@angular/common/http';
import { AlertService } from '../alert.service';
import { MedicalsuppliesService } from '../medicalsupplies.service';
import { EditMedicalSuppliesComponent } from '../edit-medical-supplies/edit-medical-supplies.component';
export interface MedicalSupplies{
medicalsuppliesId:number;
codeNumber:String;
medicalsuppliesName:String;
brandName:String;
modelNumber:String;
properties:String;

  medicalInstrument:{
    medicalInstrumentName:String;
  }
  useability:{
    useabilityName:String;
  }
}

@Component({
  selector: 'app-medical-supplies',
  templateUrl: './medical-supplies.component.html',
  styleUrls: ['./medical-supplies.component.css']
})
export class MedicalSuppliesComponent implements OnInit {
  displayedColumns: string[] = ['medicalsuppliesId','codeNumber','modelNumber', 'medicalsuppliesName','brandName','properties','medicalInstrument','useability',"actions"];

  filter : '';
 
  medical: Array<any>;//คืออะไร
  dataSource = new MatTableDataSource<MedicalSupplies>(this.medical);
  

  @ViewChild(MatPaginator) paginator: MatPaginator;
  
  applyFilter(filterValue: string) {
    this.newMethod(filterValue);
  }
  private newMethod(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
    
  }
  


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

 



constructor(private medicalsuppliesService:MedicalsuppliesService, private httpClient:HttpClient ,private dialog: MatDialog,private alertService:AlertService ) { }

  ngOnInit() {
    this.medicalsuppliesService.getMedicalSupplies().subscribe(medicalSupplies => {
      this.MedicalSupplies = medicalSupplies;
      console.log(this.MedicalSupplies);
      this.dataSource.data=medicalSupplies;   
      
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
  onSave(){
    if (this.view.codeNumber== null) {
      this.alertService.error('กรุณากรอกรหัสเวชภัณฑ์');
    }
  
    else if (this.view.modelNumber == null) {
      this.alertService.error('กรุณากรอกหมายเลขรุ่น');
    }
    else if( this.view.medicalSupplies == null){
      this.alertService.error('กรุณากรอกชื่อเวชภัณฑ์');
    }
    else if ( this.view.brandName == null) {
      this.alertService.error('กรุณากรอกชื่อแบรนด์');
    }
    else if(this.view.medicalInstrument == null) {
      this.alertService.error('กรุณาเลือกอุปกรณ์การแพทย์');
    }
    else if(this.view.useability == null) {
      this.alertService.error('กรุณาเลือกการใช้งาน');
    }
    else{

      this.onSaveFunction()
      this.alertService.success('บันทึกเรียบร้อย');
    }
  }
  
  onSaveFunction(){
    console.log(this.view.codeNumber);
    console.log(this.view.modelNumber);
    console.log(this.view.medicalSupplies);
    console.log(this.view.brandName);
    console.log(this.view.properties);
    console.log(this.view.medicalInstrument);
    console.log(this.view.useability);

    this.httpClient.post('http://localhost:8080/MedicalSupplies/'+ this.view.codeNumber +  
    '/'+ this.view.modelNumber + 
    '/'+ this.view.medicalSupplies +
    '/'+ this.view.brandName + 
    '/'+ this.view.properties + 
    '/'+ this.view.medicalInstrument +
    '/'+ this.view.useability,this.view)
    .subscribe
    (
      data =>{
       
      console.log('Post Request is seccessful',data);
     
      // window.location.reload();
    },
    error=>{
      console.log('Rrror',error);
      this.alertService.error('ไม่สามารถบันทึกได้ เนื่องเกิดจากมีข้อมูลอยู่แล้วหรือใส่ข้อมูลไม่ครบ โปรดกรุณาตรวสอบข้อมูลอีกครั้ง');
    }
    );
  }
  onDelete(medicalsuppliesId){
    this.httpClient.delete('http://localhost:8080/MedicalSupplies/'+ medicalsuppliesId )
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
  onEdit(editMedicalsupplies){
    this.medicalsuppliesService.getEditMedicalSupplies(editMedicalsupplies);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(EditMedicalSuppliesComponent,dialogConfig);
  }

}
