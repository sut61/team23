import { Component, OnInit ,ViewChild} from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatDialog, MatDialogConfig, MatTableDataSource } from "@angular/material";
import { HttpClient } from '@angular/common/http';
import { AlertService } from '../alert.service';
import { MedicalsuppliesService } from '../medicalsupplies.service';
import { EditMedicalSuppliesComponent } from '../edit-medical-supplies/edit-medical-supplies.component';
import { Validators, FormBuilder } from '@angular/forms';
import { Medical } from './medical';
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

isValidFormSubmitted = null;
   codeNumber = "(C).+";
   modelNumber = ".{1,5}";
   medicalSupplies=".{2,30}";
   brandName=".{2,10}";
   properties=".{2,30}";
    myForm = this.formbuilder.group({
    codeNumber_no : ['', [Validators.required, Validators.pattern(this.codeNumber)]],
    modelNumber_no: ['', [Validators.required, Validators.pattern(this.modelNumber)]],
    medicalSupplies_no: ['', [Validators.required, Validators.pattern(this.medicalSupplies)]],
    brandName_no : ['', [Validators.required, Validators.pattern(this.brandName)]],
    properties_no : ['', [Validators.required, Validators.pattern(this.properties)]],
    medicalInstrument_no: ['', [Validators.required, Validators.pattern('')]],
    useability_no: ['', [Validators.required, Validators.pattern('')]],

    
    });

    onFormSubmit(){
      this.isValidFormSubmitted = false;
      if (this.myForm.invalid) {
                this.alertService.error('กรุณา กรอกข้อมูล ให้ครบถ้วน');
                if(this.myForm.controls['codeNumber_no'].hasError('pattern')){
                                this.alertService.error('กรุณากรอก C นำหน้า');
                }else if(this.myForm.controls['modelNumber_no'].hasError('pattern')){
                                this.alertService.error('กรุณากรอกข้อมูล (1-5) ตัว');
                }
                else if(this.myForm.controls['medicalSupplies_no'].hasError('pattern')){
                  this.alertService.error('กรุณากรอกข้อมูล (2-30) ตัว');
                }
                else if(this.myForm.controls['brandName_no'].hasError('pattern')){
                  this.alertService.error('กรุณากรอกข้อมูล (2-30) ตัว');
                }
                else if(this.myForm.controls['properties_no'].hasError('pattern')){
                  this.alertService.error('กรุณากรอกข้อมูล (2-30) ตัว');
                }
                else if(this.myForm.controls['medicalInstrument_no'].hasError('pattern')){
                  this.alertService.error('กรุณาเลือกอุปกรณ์การแพทย์');
                }
                else if(this.myForm.controls['useability_no'].hasError('pattern')){
                  this.alertService.error('กรุณาเลือกการใช้งาน');
                }




                return;
      }
      else if (this.myForm.valid){
              this.onSave(this.myForm.value);
              this.isValidFormSubmitted = true;
      }

       this.isValidFormSubmitted = true;
  }


 

constructor(private formbuilder:FormBuilder,private medicalsuppliesService:MedicalsuppliesService, private httpClient:HttpClient ,private dialog: MatDialog,private alertService:AlertService ) { }

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




  
  onSave(medical:Medical){
    console.log(medical.codeNumber_no);
    console.log(medical.modelNumber_no );
    console.log(medical.medicalSupplies_no);
    console.log(medical.brandName_no);
    console.log(medical.properties_no);
    console.log(medical.medicalInstrument_no);
    console.log(medical.useability_no);

    this.httpClient.post('http://localhost:8080/MedicalSupplies/'+ medical.codeNumber_no +  
    '/'+ medical.modelNumber_no + 
    '/'+ medical.medicalSupplies_no +
    '/'+ medical.brandName_no + 
    '/'+ medical.properties_no + 
    '/'+ medical.medicalInstrument_no +
    '/'+ medical.useability_no,medical)
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
