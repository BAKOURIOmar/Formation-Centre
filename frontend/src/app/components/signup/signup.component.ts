import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SignupService } from '../../shared/services/signup.service';
import { DatePipe } from '@angular/common';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  myForm: FormGroup;

  constructor(private fb: FormBuilder, private signupService: SignupService, private router: Router, private datePipe: DatePipe, public dialogRef: MatDialogRef<SignupComponent>) {
    this.myForm = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      tel: ['', Validators.required],
      ville: ['', Validators.required],
      dateNaissance: ['', Validators.required]

    })
  }

  ngOnInit(): void {
    this.myForm = this.fb.group({
      nom: ['', Validators.required],
      prenom: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      tel: ['', Validators.required],
      ville: ['', Validators.required],
      dateNaissance: ['', Validators.required]

    })
  }

  onSubmit(): void {
    if (this.myForm.valid) {
      const { nom, prenom, email, tel, ville, dateNaissance } = this.myForm.value;
      console.log("Date avant conversion", dateNaissance);

      // Convertissez la date avant de l'envoyer au serveur
      const dateDeNaissance = this.datePipe.transform(dateNaissance, 'yyyy-MM-dd');
      console.log("dateNaissanceFormatted", dateDeNaissance);
      const signupRequest = { nom, prenom, email, tel, ville, dateDeNaissance }

      console.log(signupRequest);

      this.signupService.signupIndividu(signupRequest).subscribe({
        next: (response) => {
          console.log("added");
          console.log(response);

          // Redirection vers la page de connexion après l'inscription
          this.router.navigateByUrl('/login');
          this.router.navigate(['/accueille']);

          // Fermer le dialog après l'inscription réussie
          this.dialogRef.close();
        },
        error: (message) => {
          // Marquer tous les champs du formulaire comme touchés en cas d'erreur
          this.myForm.markAllAsTouched();

          // Gérer les erreurs
          console.error('Erreur lors de l\'inscription :', message);
          // Afficher un message d'erreur à l'utilisateur
        }
      });
    } else {
      // Le formulaire est invalide, gérer l'affichage d'un message d'erreur
      console.log('Formulaire invalide, veuillez le remplir correctement.');
    }
  }

  onCancel() {
    // Implémentez les actions à effectuer lors de l'annulation du formulaire
    this.dialogRef.close();
    console.log("Formulaire annulé");
  }

}

