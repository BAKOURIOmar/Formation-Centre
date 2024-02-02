import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders} from '@angular/common/http';

import { Formation } from '../interfaces/formation.interface';
import { PageResponse } from '../interfaces/pageResponse.interface';
@Injectable({
  providedIn: 'root'
})
export class FormationService {
  private apiurl = "http://localhost:8080/form";




  constructor(private httpClient: HttpClient) { }

getFormations(): Observable<PageResponse<Formation>>{
  const url = `${this.apiurl}/getformation`;
    return this.httpClient.get<PageResponse<Formation>>(url);
}
addFormation(formation: any,picture: File):Observable<Formation>{
  const formData = new FormData();
  formData.append('name', formation.name);
  formData.append('nombreh', formation.nombreh);
  formData.append('cout', formation.cout);
  formData.append('programme', formation.programme);
  formData.append('ville', formation.ville);
  formData.append('categorie', formation.categorie);
  formData.append('picture', picture);

  const url = `${this.apiurl}/addformation`;
    return this.httpClient.post<Formation>(url,formData);
}

updateFormation(id: number, picture: File,formationUpdated:any):Observable<Formation>{
  const url = `${this.apiurl}/updateformation/${id}`;

    const formData = new FormData();
    formData.append('picture', picture);
    formData.append('form', new Blob([JSON.stringify(formationUpdated)],{ type: 'application/json' } ));

    return this.httpClient.put<Formation>(url, formData);
  }

  deleteFormation(id: number):Observable<Boolean>{
    const url = `${this.apiurl}/deleteform/${id}`;
    return this.httpClient.delete<Boolean>(url);
  }
  getFormationByid(id:number):Observable<Formation>{
   const url=`${this.apiurl}/getformationbyid/${id}`;
   return this.httpClient.get<Formation>(url);

  }

}

