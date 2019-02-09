import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'; 
import { Observable } from 'rxjs/Observable'
import { MedicalSupplies } from './medical-supplies/medical-supplies.component';
@Injectable({
  providedIn: 'root'
})

export class MedicalsuppliesService {
  public API ='//localhost:8080';
  private serviceUrl ='http://localhost:8080/MedicalSupplies';
  EditMedicalsupplies:number;
  codeNumber:String;
  constructor(private httpClient:HttpClient) { }
  getMedicalSupplies():Observable<any>{
    return this.httpClient.get(this.API +'/MedicalSupplies');
}
getMedicalInstrument():Observable<any>{
  return this.httpClient.get(this.API +'/MedicalInstrument');
}
getUseability():Observable<any>{
  return this.httpClient.get(this.API +'/Useability');
}
getShow(): Observable<MedicalSupplies[]>{
  return this.httpClient.get<MedicalSupplies[]>(this.serviceUrl);
}
getEditMedicalSupplies(editMedicalsupplies){
  this.EditMedicalsupplies=editMedicalsupplies;
}
getIdMedicalSupplies(){
  return this.EditMedicalsupplies;
}

findcodeNumber(codeNumber:String){
  return this.httpClient.get(this.API+'/MedicalSupplies'+ codeNumber);
}

}

