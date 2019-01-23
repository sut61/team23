import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()

export class GoldcardService {
public API = '//localhost:8080';
  constructor(private http: HttpClient) { }

  getRightRegistration(): Observable<any> {
      return this.http.get('//localhost:8080/Members');
  }
  getRightsType(): Observable<any> {
        return this.http.get('//localhost:8080/RightsType');
    }
    getHospital(): Observable<any> {
         return this.http.get('//localhost:8080/listhospital');
      }
      getProvince(): Observable<any> {
            return this.http.get('//localhost:8080/Province');
        }

  getDisease(): Observable<any> {
     return this.http.get(this.API + '/Disease');
  }
  getDocument(): Observable<any> {
     return this.http.get(this.API + '/Documentary');
  }
  getEligibleDiseases(): Observable<any> {
     return this.http.get(this.API + '/EligibleDiseases');
  }


}
