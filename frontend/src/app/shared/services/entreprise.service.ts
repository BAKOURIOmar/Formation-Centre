import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Entreprise } from '../interfaces/entreprise.interface';
import { PageResponse } from '../interfaces/pageResponse.interface';

@Injectable({
  providedIn: 'root'
})
export class EntrepriseService {
  private apiurl = "http://localhost:8080/entreprise";

  constructor(private httpClient: HttpClient) { }

  getEntreprises():Observable<PageResponse<Entreprise>>{
    const url = `${this.apiurl}/getEntreprise`;
    return this.httpClient.get<PageResponse<Entreprise>>(url);
  }

  addEntreprise(entreprise:any):Observable<Entreprise>{
    const url = `${this.apiurl}/addEntreprise`;
    return this.httpClient.post<Entreprise>(url,entreprise);
  }

  updateEntreprise(entreprise:any, id :number):Observable<Entreprise>{
    const url = `${this.apiurl}/updateEntreprise/${id}`;
    return this.httpClient.put<Entreprise>(url,entreprise);
  }

  deleteEntreprise(id :number):Observable<Boolean>{
    const url = `${this.apiurl}/deleteEntreprise/${id}`;
    return this.httpClient.delete<Boolean>(url);
  }

  getEntrepriseByName(name: string): Observable<Entreprise[]> {
    const url = `${this.apiurl}/getEntrepriseByName?name=${name}`;
    return this.httpClient.get<Entreprise[]>(url);
  }

}
