import { HttpClient } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";

import Swal from "sweetalert2";
import { UserService } from "../../shared/services/user.service";
import { UserAuthService } from "src/app/shared/services/user-auth.service";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  hide = true;
  errorLogin: boolean = false;
  errorPermiso: boolean = false;
  errorDetalle: boolean = false;
  public myForm: FormGroup = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', Validators.required]
    });

  constructor(private http: HttpClient, private fb: FormBuilder ,private userService: UserService, private userAuthService: UserAuthService,private router :Router  ) { }

  ngOnInit(): void {

  }



  // Función para manejar el envío del formulario
  onSubmit() {
    const { username, password } = this.myForm.value;
    let authRequest ={
      username: username,
      password: password
    }
 //console.log(email,'    ' ,password )
    this.userService.login(authRequest)
      .subscribe({
        next: (response :any) =>{
          this.userAuthService.setRoles(response.role);
          this.userAuthService.setToken(response.message);
          this.userAuthService.setUserId(response.userId)
          const role = response.role;
          console.log(response);

          this.router.navigateByUrl('/home')} ,
        error: (message) => {
          Swal.fire("Le nom d'utilisateur ou le mot de passe sont incorrects. Veuillez réessayer", message, 'error' )
          this.myForm.markAllAsTouched();
        }
      })

  }
}
