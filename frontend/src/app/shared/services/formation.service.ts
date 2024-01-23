import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserAuthService } from './user-auth.service';
import { Token } from '@angular/compiler';
import { FormationModel } from '../models/FormationModel.model';
@Injectable({
  providedIn: 'root'
})
export class FormationService {

  private apiurl = "http://localhost:8080/form/";



  constructor(private httpClient: HttpClient) { }

getFormations(){
  const url = `${this.apiurl}/getformation`;
    return this.httpClient.get(url);
}


}
