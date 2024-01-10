import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { UserAuthService } from "./user-auth.service";
import { Observable } from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private API_BASE_URL = "http://localhost:8080/auth";

  constructor(
    private httpClient: HttpClient,
    private userAuthService: UserAuthService
  ) { }

  public login(loginData: any):Observable<string> {
    return this.httpClient.post<string>(this.API_BASE_URL + "/generateToken", loginData);
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

  public roleMatch(allowedRoles: string[]): boolean {
    const userRoles: string = this.userAuthService.getRoles();
    if (userRoles !== '') {
      return allowedRoles.some(role => userRoles.includes(role));
    }
    return false;
  }

}
