import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserAuthService } from './user-auth.service';
import { Token } from '@angular/compiler';
import { FormationModule } from 'src/app/Module/formation/formation.module';
@Injectable({
  providedIn: 'root'
})
export class FormationService {

  private  apiurl= 'http://localhost:8080/form/getformation';

  constructor(private httpClient:HttpClient){}


afficherFormation(): Observable<FormationModule[]> {
  console.log("recuperer les test")
    return this.httpClient.get<FormationModule[]>(`${this.apiurl}`);
    console.log("apres recupere les text")
  }

}
