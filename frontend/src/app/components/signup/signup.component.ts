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

  constructor(private fb: FormBuilder, private signupService: SignupService, private router: Router) { }

  ngOnInit(): void { }

  // Ajoutez la méthode getter pour la date de naissance
  get dateNaissance(): LocalDate {
    const dateControl = this.myForm.get('dateNaissance');
    const dateValue = dateControl ? dateControl.value : null;
    return dateValue ? new Date(dateValue) : null;
  }

  onSubmit() {
    const { nom, prenom, email, tel, ville, dateNaissance } = this.myForm.value;
    console.log("Date avant conversion", dateNaissance);

    // Convertissez la date avant de l'envoyer au serveur
    const dateNaissanceFormatted: LocalDate = this.dateNaissance;
    console.log("dateNaissanceFormatted", dateNaissanceFormatted);

    if (dateNaissanceFormatted !== null) {
      console.log("dateNaissanceFormatted", dateNaissanceFormatted);
      const dateNaissanceValue: string = dateNaissanceFormatted.toISOString(); // Formatez la date en ISO 8601
      console.log("dateNaissanceValue", dateNaissanceValue);

      let signupRequest = {
        nom: nom,
        prenom: prenom,
        email: email,
        tel: tel,
        ville: ville,
        dateNaissance: dateNaissanceValue,
      };

      console.log(signupRequest);
      this.signupService.signup(signupRequest).subscribe({
        next: (response) => {
          console.log("added");
          console.log(response);
          this.router.navigateByUrl('/login');
        },
        error: (message) => {
          console.log("error");
          console.log(message);
          console.log(message.error.text);
          this.errorSignup = true;
          this.myForm.markAllAsTouched();
        }
      });
    }
    console.log("Date après conversion", dateNaissance);
  }

}
