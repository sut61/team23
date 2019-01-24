import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'; //ถ้า get  มีปัญหาเรียกตัวนี้

import { Observable } from 'rxjs/Observable'
import { Drug } from './add-midicine/add-midicine.component';

@Injectable({
  providedIn: 'root'
})
export class MidicineService {

  public API ='//localhost:8080';
  private serviceUrl ='http://localhost:8080/Drug'
  EditMidicine:number;
  constructor(private httpClient:HttpClient) { 
  
  }
  getDrugs():Observable<any>{
    return this.httpClient.get(this.API+'/Drug');
  }
  getTypesOfDrugs():Observable<any>{
    return this.httpClient.get(this.API+'/TypesOfDrugs');
  }

  getDrugRegistration():Observable <any>{
    return this.httpClient.get(this.API+'/DrugRegistration');
  }

  getTypesOfDosageForms():Observable <any>{
    return this.httpClient.get(this.API+'/TypesOfDosageForms');
  }

  getDisease():Observable <any>{
    return this.httpClient.get(this.API+'/Disease');
  }
  getShow(): Observable<Drug[]>{
    return this.httpClient.get<Drug[]>(this.serviceUrl);
  }
  

  getMidicine(editMidicine){
     this.EditMidicine=editMidicine;
   }
  getIdMidicine(){
    return this.EditMidicine;
  }


}
