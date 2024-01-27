import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../interfaces/user.interface';

@Injectable({
  providedIn: 'root'
})
export class FormateurService {
  private apiurl = "http://localhost:8080/auth";




  constructor(private httpClient: HttpClient) { }

  getUsersByRole(role: string){
    const url = `${this.apiurl}/users?role=${role}`;
    return this.httpClient.get(url);
  }

  addFormateur(formateur:any):Observable<User>{
    const url = `${this.apiurl}/addFormateur`;
    return this.httpClient.post<User>(url,formateur);
  }

  updateFormateur(formateur:any, id :number):Observable<User>{
    const url = `${this.apiurl}/updateFormateur/${id}`;
    return this.httpClient.put<User>(url,formateur);
  }

  deleteFormateur(id: number):Observable<Boolean>{
    const url = `${this.apiurl}/supprimerUser/${id}`;
    return this.httpClient.delete<Boolean>(url);
  }

  addAssistant(assistant:any):Observable<User>{
    const url = `${this.apiurl}/addAssistant`;
    return this.httpClient.post<User>(url,assistant);
  }

  updateAssistant(assistant:any, id :number):Observable<User>{
    const url = `${this.apiurl}/updateAssistant/${id}`;
    return this.httpClient.put<User>(url,assistant);
  }



}