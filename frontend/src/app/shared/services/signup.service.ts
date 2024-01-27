/*import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  constructor() { }
}*/
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { parseISO, format } from 'date-fns';

@Injectable({
  providedIn: 'root'
})
export class SignupService {
  private apiUrl = 'http://localhost:8080/indiv/addindividu'; // Remplacez cela par votre URL d'API d'inscription

  constructor(private http: HttpClient) { }

  signup(signupData: any): Observable<any> {

    // Convertissez la date avant de l'envoyer au serveur
    const dateNaissanceValue: string | null = signupData.dateNaissance;

    if (dateNaissanceValue !== null) {
      const dateNaissanceFormatted: string = format(parseISO(dateNaissanceValue), 'yyyy-MM-dd');

      // Ajoutez cette ligne de log
      console.log("dateNaissanceFormatted", dateNaissanceFormatted);

      // Mettez à jour la propriété dateNaissance dans signupData avec la version formatée
      signupData.dateNaissance = dateNaissanceFormatted;
    }

    console.log("Données avant envoi", signupData);
    const request = this.http.post<any>(`${this.apiUrl}`, signupData);
    request.subscribe(() => {
    console.log("Requête envoyée");
    });
    return request;
    
  }
}

