import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatPaginator } from '@angular/material/paginator';
import { MatDialog, MatDialogConfig, MatTableDataSource } from "@angular/material";
import { EditMidicineComponent } from '../edit-midicine/edit-midicine.component';
import { MidicineService } from '../midicine.service';
import { GoldcardService } from '../goldcard.service';
import { FormBuilder, Validators } from '@angular/forms';
import { AlertService } from '../alert.service';
import { AddMidicine } from './addmidicine';
export interface Drug {
  drugId: number;
  typesOfDrugs: {
    typesOfDrugsName: string;
  }
  drugRegistration: {
    drugRegistrationName: string;
  }
  typesOfDosageForms: {
    typesOfDosageFormsName: string;
  }
  disease: {
    diseaseName: string;
  }
  drugName: string;
}

@Component({
  selector: 'app-add-midicine',
  templateUrl: './add-midicine.component.html',
  styleUrls: ['./add-midicine.component.css']
})
export class AddMidicineComponent implements OnInit {
  displayedColumns: string[] = ['drugId', 'drugName', 'typesOfDrugs', 'drugRegistration', 'typesOfDosageForms', 'disease', "actions"];
  

  filter: '';
  drugMatTable: Array<any>;//คืออะไร
  dataSource = new MatTableDataSource<Drug>(this.drugMatTable);


  @ViewChild(MatPaginator) paginator: MatPaginator;

  applyFilter(filterValue: string) {
    this.newMethod(filterValue);
  }
  private newMethod(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }


  Drug: Array<any>;
  TypesOfDrugs: Array<any>;
  DrugRegistration: Array<any>;
  TypesOfDosageForms: Array<any>;
  Diseases: Array<any>;
  view: any = {
    drug: null,
    typesOfDrugs: null,
    drugRegistration: null,
    typesOfDosageForms: null,
    diseases: null
  }

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
      this.onSave(this.myForm.value);
      this.isValidFormSubmitted = true;
    }

    this.isValidFormSubmitted = true;
  }

  /** --------------------------------------------------------------------------------------------- */


  constructor(private midicineService: MidicineService, private httpClient: HttpClient, private dialog: MatDialog, private goldcardService: GoldcardService, private formbuilder: FormBuilder, private alertService: AlertService, ) { }

  ngOnInit() {
    this.goldcardService.getDrug().subscribe(drug => {
      this.Drug = drug;
      console.log(this.Drug);
      this.dataSource.data = drug;
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


  onSave(addMidicine: AddMidicine) {
    console.log(addMidicine.drugs_no);
    console.log(addMidicine.typesOfDrugs_no);
    console.log(addMidicine.drugRegistration_no);
    console.log(addMidicine.typesOfDosageForms_no);
    console.log(addMidicine.diseases_no);



    this.httpClient.post('http://localhost:8080/Drug/' + addMidicine.drugs_no + '/' + addMidicine.typesOfDrugs_no +
      '/' + addMidicine.drugRegistration_no + '/' + addMidicine.typesOfDosageForms_no + '/' + addMidicine.diseases_no, addMidicine)
      .subscribe
      (
        data => {
          this.alertService.success("บันทึกข้อมูลยา สำเร็จ");
          console.log('Post Request is seccessful', data);
        },
        error => {
          console.log('Rrror', error);
          this.alertService.error('ไม่สามารถบันทึกได้ เนื่องจากชื่อยานี้มีอยู่ในระบบอยู่แล้ว โปรดกรุณาตรวจสอบข้อมูลอีกครั้ง');
        }
      );
  }
  onDelete(drugId) {
    this.httpClient.delete('http://localhost:8080/Drug/' + drugId)
      .subscribe
      (
        data => {
          this.alertService.success('ลบเรียบร้อย');
          console.log('Delete Request is seccessful', data);
       
        },
        error => {
          console.log('Rrror', error);
          this.alertService.error('ไม่สามารถลบได้ โปรดกรุณาตรวจสอบข้อมูลอีกครั้ง');
        }
      );
  }
  onEdit(drugId) {
    this.midicineService.getMidicine(drugId);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(EditMidicineComponent, dialogConfig);
  }

}
