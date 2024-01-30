/*import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { FormateurService } from 'src/app/shared/services/formateur.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public formateurForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<RegisterComponent>,
    private formateurService: FormateurService
  ) { }

  ngOnInit(): void {
    this.initForm();
  }

  initForm(): void {
    this.formateurForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      motcles: ['', Validators.required],
      type: ['EXTERNE'] // Définir le type EXTERNE
    });
  }

  onSave(): void {
    if (this.formateurForm.valid) {
      const formateurData = this.formateurForm.value;
      this.formateurService.addFormateur(formateurData)
        .subscribe(
          (response) => {
            console.log('Formateur ajouté avec succès : ', response);
            this.dialogRef.close(true); // Fermer le dialogue après l'ajout réussi
          },
          (error) => {
            console.error('Erreur lors de l\'ajout du formateur : ', error);
            // Gérer l'erreur ou afficher un message à l'utilisateur
          }
        );
    } else {
      console.log('Formulaire invalide, veuillez le remplir correctement.');
    }
  }

  onCancel(): void {
    this.dialogRef.close(false); // Fermer le dialogue sans sauvegarder
  }
}*/
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from 'src/app/shared/services/user.service';
import { MatDialogRef } from '@angular/material/dialog'; // Importez MatDialogRef

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  formateurForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private dialogRef: MatDialogRef<RegisterComponent> // Injectez MatDialogRef
  ) { 
    // Initialisez formateurForm dans le constructeur
    this.formateurForm = this.formBuilder.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      motcles: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.formateurForm = this.formBuilder.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      motcles: ['', Validators.required]
    });
  }

  onSave(): void {
    if (this.formateurForm.valid) {
      const formateurData = this.formateurForm.value;
      this.userService.registerExternalFormateur(formateurData).subscribe(
        (response) => {
          // Gérer la réponse de l'API
          console.log('Inscription réussie :', response);
          // Afficher un message de succès à l'utilisateur
          this.dialogRef.close();
        },
        (error) => {
          // Gérer les erreurs
          console.error('Erreur lors de l\'inscription :', error);
          // Afficher un message d'erreur à l'utilisateur
        }
      );
    } else {
      // Le formulaire est invalide, gérer l'affichage d'un message d'erreur
      console.log('Formulaire invalide, veuillez le remplir correctement.');
    }
  }
  onCancel(): void {
    this.dialogRef.close(); // Fermer le dialogue sans sauvegarder
  }

}


