import { Component, OnInit , ViewChild} from '@angular/core';
import { GoldcardService } from '../goldcard.service';
import { HttpClient} from '@angular/common/http';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { AlertService } from '../alert.service';
import { AuthService } from '../auth/auth.service';
import {FormBuilder, FormGroup} from '@angular/forms';
import { DataSource } from '@angular/cdk/collections';
import {MatPaginator, MatTableDataSource} from '@angular/material';


export interface TrainingElement{
  topicTraining: string;
  objectiveTraining: string;
  importantTopicTraining: string;
  attendess: number;
  expenditure: number;
  


}

@Component({
  selector: 'app-add-training',
  templateUrl: './add-training.component.html',
  styleUrls: ['./add-training.component.css']
})
export class AddTrainingComponent implements OnInit {

  displayedColumns: string[] = ['topicTraining', 'objectiveTraining', 'importantTopicTraining', 'typeTrainingName', 'lecturerName', 'hospitalName', 'attendess', 'expenditure'];
  filter : '';

  traingshow : Array<any>;

  dataSourceTraining = new MatTableDataSource<TrainingElement>(this.traingshow);
  @ViewChild(MatPaginator) paginator: MatPaginator;


  hospitals: Array<any>;
  lecturers: Array<any>;
  typetrainings: Array<any>;

input: any = {
  topicTraining: '',
  objectiveTraining: '',
  importantTopicTraining: '',
  attendess: '',
  expenditure: '',

};


  select: any = {
 typeTrainingName: '',
 lecturerName: '',
 hospitalName: '',
  };

lock: FormGroup;
  constructor(private alertService : AlertService,private authService: AuthService,fb: FormBuilder,private goldcardService: GoldcardService, private httpClient: HttpClient,private router: Router){
    this.lock = fb.group({
       hideRequiredMarker: false,
       floatLabel: 'never',
    });
}

trainingadd(){
  // http://localhost:8080/addtraining/{topicTraining}/{objectiveTraining}/{importantTopicTraining}/{lecturerName}/{typeTrainingName}/{hospitalName}
  this.httpClient.post('http://localhost:8080/addtraining/'+this.input.topicTraining+'/'+this.input.objectiveTraining+'/'+this.input.importantTopicTraining+'/'+this.select.typeTrainingName+'/'+this.select.lecturerName+'/'+this.select.hospitalName+'/'+this.input.attendess+'/'+this.input.expenditure,this.input)
  .subscribe(
    data => {
      console.log("add  success", data);
      this.alertService.success('เพิ่มเรียบร้อย');

    },
    error => {
        console.log('Error', error);
        this.alertService.error('Error มีปัญหา');    
      }
 );
}


  ngOnInit() {
    this.goldcardService.getHospital().subscribe(data => {
      this.hospitals = data;
    })
    this.goldcardService.getLecturer().subscribe(data => {
      this.lecturers = data;
    })
    this.goldcardService.getTypeTraining().subscribe(data => {
      this.typetrainings = data;
    })
    this.goldcardService.getTraining().subscribe(data => {
      this.traingshow = data;
      this.dataSourceTraining.data = data;
    })
    this.dataSourceTraining.paginator = this.paginator;
  }

  applyFilter(filterValue: string) {
    this.newMethod(filterValue);
  }


  private newMethod(filterValue: string) {
    this.dataSourceTraining.filter = filterValue.trim().toLowerCase();
  }

}
