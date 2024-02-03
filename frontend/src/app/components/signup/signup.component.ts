/*import { Component } from '@angular/core';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

}*/
import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { SignupService } from '../../shared/services/signup.service';
import { DatePipe } from '@angular/common';
import { MatSnackBar, MatSnackBarRef, SimpleSnackBar } from '@angular/material/snack-bar';

type LocalDate = Date | null; // Changer le type de LocalDate à Date

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  hide = true;
  errorSignup: boolean = false;
  idFormation!: number;
  private snackBar = inject(MatSnackBar);

  public myForm: FormGroup = this.fb.group({
    nom: ['', Validators.required],
    prenom: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    tel: ['', Validators.required],
    ville: ['', Validators.required],
    dateNaissance: ['', Validators.required]
  });

  constructor(private fb: FormBuilder, private signupService: SignupService,private route: ActivatedRoute, private router: Router,private datePipe: DatePipe) {

  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.idFormation = +params['id']; // 'id' es el nombre del parámetro en la ruta
    });
   }



  onSubmit() {
    const { nom, prenom, email, tel, ville, dateNaissance } = this.myForm.value;
    console.log("Date avant conversion", dateNaissance);

    // Convertissez la date avant de l'envoyer au serveur
    const dateDeNaissance = this.datePipe.transform(dateNaissance,'yyyy-MM-dd');
    console.log("dateNaissanceFormatted", dateDeNaissance);
    const signupRequest ={nom, prenom, email, tel, ville,dateDeNaissance}


      console.log(signupRequest);
      this.signupService.signupIndividu(signupRequest,this.idFormation).subscribe({
        next: (response) => {
          console.log("added");
          console.log(response);
          this.openSnackBar('Inscription reussite, verrifier votre email','OK')
          this.router.navigate(['/accueille']);
        },
        error: (message) => {

          this.myForm.markAllAsTouched();
        }
      });


  }

  openSnackBar(message: string, action: string) : MatSnackBarRef<SimpleSnackBar>{
    return this.snackBar.open(message, action, {
      duration: 2000
    })

  }


}
