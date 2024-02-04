import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Formation } from '../interfaces/formation.interface';

@Injectable({
  providedIn: 'root'
})
export class GroupService {
  private apiurl = "http://localhost:8080/group";

  constructor(private httpClient: HttpClient) { }

  getGroupesByIdformation(formationId : number): Observable<Formation[]>{
    const url = `${this.apiurl}/getAllGroupes?formationId=${formationId}`;
      return this.httpClient.get<Formation[]>(url);
  }


}
