import { HttpClient } from "@angular/common/http";
import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";

import Swal from "sweetalert2";
import { UserService } from "../../shared/services/user.service";

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

  constructor(private http: HttpClient, private fb: FormBuilder ,private userService: UserService,private router :Router  ) { }

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
        next: (response ) =>{
          console.log(response)

          this.router.navigateByUrl('/home')} ,
        error: (message) => {
          console.log('tamo aquiii  ')
          console.log(message)
          console.log(message.error.text)
          //Swal.fire('Error', message, 'error' )
          this.myForm.markAllAsTouched();
        }
      })

  }
}
