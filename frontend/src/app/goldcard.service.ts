import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()

export class GoldcardService {

  constructor(private http: HttpClient) { }

  getRightRegistration(): Observable<any> {
      return this.http.get('//localhost:8080/Rightregistration');
  }
  getRightsType(): Observable<any> {
        return this.http.get('//localhost:8080/RightsType');
    }
    getHostpital(): Observable<any> {
          return this.http.get('//localhost:8080/Hostpital');
      }
      getProvince(): Observable<any> {
            return this.http.get('//localhost:8080/Province');
        }
}
