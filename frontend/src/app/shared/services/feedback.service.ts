import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../interfaces/user.interface';
import { PageResponse } from '../interfaces/pageResponse.interface';
import { Feedback } from '../interfaces/Feedback.interface';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {
  private apiurl = "http://localhost:8080/feedback";




  constructor(private httpClient: HttpClient) { }


  addFeedback(feedback:any):Observable<Feedback>{
    const url = `${this.apiurl}/add`;
    return this.httpClient.post<Feedback>(url,feedback);
  }




}
