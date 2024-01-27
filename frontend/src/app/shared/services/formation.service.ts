import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders} from '@angular/common/http';
import { Observable, catchError, map } from 'rxjs';
import { UserAuthService } from './user-auth.service';
import { Token } from '@angular/compiler';
import { FormationModel } from 'src/app/shared/Models/FormationModel.model';
@Injectable({
  providedIn: 'root'
})
export class FormationService {
 Formationdata:FormationModel[]=[];
  private apiurl = "http://localhost:8080/form";

  constructor(private httpClient: HttpClient) {}
//recuperer tous le sformation
getFormations(): Observable<FormationModel[]> {
    const url = `${this.apiurl}/getformation`;
  console.log("formation recuperer ");
    return this.httpClient.get<FormationModel[]>(url);
  }
//recupererformation par id
getFormationByid(formationId:Number):Observable<FormationModel>{
  console.log("les donnes avant l appel");
  const url=`${this.apiurl}/getformationbyid/${formationId}`;
  console.log("les donnes appele");
   return this.httpClient.get<FormationModel>(url);

}

}
