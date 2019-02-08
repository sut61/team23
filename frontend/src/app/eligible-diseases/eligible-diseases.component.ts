import { Component, OnInit } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import {MatTableDataSource} from '@angular/material';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

import { GoldcardService } from '../goldcard.service';

export interface PeriodicElement  {
ideligibleDiseases: string;
disease: string;
documentWork: string;
officer: string;
}
@Component({
selector: 'app-eligible-diseases',
templateUrl: './eligible-diseases.component.html',
styleUrls: ['./eligible-diseases.component.css']
})
export class EligibleDiseasesComponent implements OnInit {


diseases: Array<any>;
documents: Array<any>;
eligibleDiseases: Array<any>

ed: any = {
DiseaseSelect: '',
DocumentSelect: '',
UsernameSelect:'',
DeleteSelect:'',
PasswordSelect:'',
CodeSelect:''


};
constructor(private service: GoldcardService ,
  private httpClient: HttpClient,
  private route: ActivatedRoute,
   private router: Router  ) { }

  ngOnInit() {
 this.service.getDisease().subscribe(data => {
         this.diseases = data;

    });

 this.service.getDocument().subscribe(data => {
         this.documents = data;

    });

 this.service.getEligibleDiseases().subscribe(data => {
         this.eligibleDiseases = data;

    });
  }

CheckdataUser(body){
 if (this.ed.DiseaseSelect === '' || this.ed.DocumentSelect === '' || this.ed.UsernameSelect === '') {
      alert('กรุณากรอกข้อมูลให้ครบถ้วน');
    } else {

this.httpClient.post("http://localhost:8080/checkdoc/"+this.ed.DocumentSelect,this.ed).subscribe(
      data => {
        console.log("Document  success");
 this.httpClient.post("http://localhost:8080/checkuser",body).subscribe(
      data => {
        console.log("User  success");

  this.httpClient.post("http://localhost:8080/EligibleDiseases/add/"+this.ed.DiseaseSelect+','+this.ed.DocumentSelect+','+this.ed.UsernameSelect+','+this.ed.CodeSelect,this.ed).subscribe(
      data => {
        console.log("add  success");
         alert("เพิ่มแล้วเรียบร้อย");


        this.router.navigate(['/reload/EligibleDiseases']);

      },
      error => {
        console.log("Error", error);

      }
         );

      },


      error => {
        console.log("Error", error);
        alert("username หรือ password ผิด");
      }
         );


      },
      error => {
        console.log("Error", error);
        alert("Document Number ผิด");
      }
         );






}
    }

Delete(){
      this.httpClient.post("http://localhost:8080/delete/"+ this.ed.DeleteSelect, this.ed)
      .subscribe(
          data => {
              console.log('Delete Request is successful', data);
        this.router.navigate(['/reload/EligibleDiseases']);

          },
          error => {
              console.log('Error', error);
          }
      );
    }



}

