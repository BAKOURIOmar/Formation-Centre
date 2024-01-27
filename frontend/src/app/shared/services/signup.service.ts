
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Individu } from '../interfaces/individu.interface';

@Injectable({
  providedIn: 'root'
})
export class SignupService {
  private apiUrl = 'http://localhost:8080/indiv';

  constructor(private httpClient: HttpClient) { }

  signupIndividu(signupData: any): Observable<any> {
    const url = `${this.apiUrl}/addindividu`;
    return this.httpClient.post<Individu>(url,signupData);
  }
}

