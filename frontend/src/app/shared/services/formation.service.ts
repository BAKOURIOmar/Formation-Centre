import { Injectable } from '@angular/core';
import { HttpClient,HttpErrorResponse,HttpHeaders} from '@angular/common/http';
import { Observable, catchError } from 'rxjs';
import { UserAuthService } from './user-auth.service';
import { Token } from '@angular/compiler';
import { FormationModel } from '../Models/FormationModel.model';

@Injectable({
  providedIn: 'root'
})
export class FormationService {

  private  apiurl= 'http://localhost:8080/form/getformation';

  constructor(private httpClient:HttpClient){}

  public recupererformation(): Observable<FormationModel[]> {
    const url = `${this.apiurl}`;
    console.log("Before request");
    return this.httpClient.get<FormationModel[]>(url, { responseType: 'json' }).pipe(
      catchError((error: HttpErrorResponse) => {
      console.error("Error during request:", error);
      console.error("Server response:", error.status, error.statusText);
      throw error;
      })
    );
  }




}
