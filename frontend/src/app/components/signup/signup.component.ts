/*import { Component } from '@angular/core';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

}*/
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SignupService } from '../../shared/services/signup.service';
import { parseISO } from 'date-fns';
import { DatePipe } from '@angular/common';

type LocalDate = Date | null; // Changer le type de LocalDate à Date

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  hide = true;
  errorSignup: boolean = false;

  public myForm: FormGroup = this.fb.group({
    nom: ['', Validators.required],
    prenom: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    tel: ['', Validators.required],
    ville: ['', Validators.required],
    dateNaissance: ['', Validators.required]
  });

  constructor(private fb: FormBuilder, private signupService: SignupService, private router: Router,private datePipe: DatePipe) { }

  ngOnInit(): void { }



  onSubmit() {
    const { nom, prenom, email, tel, ville, dateNaissance } = this.myForm.value;
    console.log("Date avant conversion", dateNaissance);

    // Convertissez la date avant de l'envoyer au serveur
    const dateDeNaissance = this.datePipe.transform(dateNaissance,'yyyy-MM-dd');
    console.log("dateNaissanceFormatted", dateDeNaissance);
    const signupRequest ={nom, prenom, email, tel, ville,dateDeNaissance}


      console.log(signupRequest);
      this.signupService.signupIndividu(signupRequest).subscribe({
        next: (response) => {
          console.log("added");
          console.log(response);
          //this.router.navigateByUrl('/login');
        },
        error: (message) => {

          this.myForm.markAllAsTouched();
        }
      });


  }

}
