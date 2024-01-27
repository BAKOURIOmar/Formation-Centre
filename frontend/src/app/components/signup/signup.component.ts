
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SignupService } from '../../shared/services/signup.service';
import { DatePipe } from '@angular/common';

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
    const dateNaissanceFormatted = this.datePipe.transform(dateNaissance,'yyyy/MM/dd');
    console.log("dateNaissanceFormatted", dateNaissanceFormatted);
    const signupRequest ={nom, prenom, email, tel, ville,dateNaissanceFormatted}

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

    // console.log("Date après conversion", dateNaissance);
  }

}
