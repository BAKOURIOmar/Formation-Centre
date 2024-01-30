import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { UserAuthService } from "./user-auth.service";
import { Observable } from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private API_BASE_URL = "http://localhost:8080/auth";

  requestHeader = new HttpHeaders(
    { "NO-Auth": "True" }
  );

  constructor(
    private httpClient: HttpClient,
    private userAuthService: UserAuthService
  ) { }

  public login(loginData: any):Observable<string> {
    return this.httpClient.post<string>(this.API_BASE_URL + "/generateToken", loginData,{headers:this.requestHeader});
  }



  public forFormateur() {
    return this.httpClient.get(this.API_BASE_URL + '/format/formatProfile', { responseType: "text" });
  }

  public forAdmin() {
    return this.httpClient.get(this.API_BASE_URL + '/admin/adminProfile', { responseType: "text" });
  }

  public forAssistant() {
    return this.httpClient.get(this.API_BASE_URL + '/assistant/assistantProfile', { responseType: "text" });
  }

  // Méthode pour enregistrer un formateur externe
  public registerExternalFormateur(formateur: any): Observable<any> {
    return this.httpClient.post<any>(`${this.API_BASE_URL}/registerFormateurExterne`, formateur);
  }

  public roleMatch(allowedRoles: string[]): boolean {
    const userRoles: string = this.userAuthService.getRoles();

    if (userRoles !== '') {
      // Divise la chaîne des rôles en un tableau
      const userRolesArray = userRoles.split(',');

      // Vérifie si au moins un des rôles autorisés est présent dans le tableau
      return allowedRoles.some(role => userRolesArray.includes(role.trim()));
    }

    return false;
  }

}
