import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SendPlanification } from '../interfaces/sendPlanification.interface';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PlanificationService {
  private apiurl = "http://localhost:8080/plan";

  constructor(private httpClient: HttpClient) { }

  getallPlanifications():Observable<SendPlanification[]>{
    const url = `${this.apiurl}/getPlanifications`;
    return this.httpClient.get<SendPlanification[]>(url);
  }

  addPlanification(planification :SendPlanification):Observable<SendPlanification>{
    const url = `${this.apiurl}/addplanification`;
    return this.httpClient.post<SendPlanification>(url,planification);
  }

  updatePlanification(planification :SendPlanification,idPlanification:number):Observable<SendPlanification>{
    const url = `${this.apiurl}/updatepan/${idPlanification}`;
    return this.httpClient.put<SendPlanification>(url,planification);
  }

}
