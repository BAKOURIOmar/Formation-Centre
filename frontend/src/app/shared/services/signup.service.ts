/*import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  constructor() { }
}*/
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Individu } from '../interfaces/individu.interface';

// import { Individu } from '../interfaces/individu.interface';
@Injectable({
  providedIn: 'root'
})
export class SignupService {
  private apiUrl = 'http://localhost:8080/indiv';

  constructor(private httpClient: HttpClient) { }


  signupIndividu(signupData: any, idFormation : number): Observable<any> {
    const url = `${this.apiUrl}/inscription/${idFormation}`;
    return this.httpClient.post<Individu>(url,signupData);
}

}

