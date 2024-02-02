
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginComponent } from '../login/login.component';
import { HttpClient } from '@angular/common/http';
import { FormationService } from 'src/app/shared/services/formation.service';
import { Formation } from 'src/app/shared/interfaces/formation.interface';
import { ConnectableObservable } from 'rxjs';

@Component({
  selector: 'app-accueille',
  templateUrl: './accueille.component.html',
})
export class AccueilleComponent {
  @Output() envoyerFormations : EventEmitter<Formation[]>=new EventEmitter<Formation[]>();


  constructor(public dialog: MatDialog,private http: HttpClient,
    private formationService: FormationService) { }

  openLoginForm(): void {
    const dialogRef = this.dialog.open(LoginComponent, {
      width: '400px'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Le dialogue est fermé');
    });
  }


  getFormationsFiltre(searchTerm: string) {
    console.log("searchTerm", searchTerm);
    this.formationService.getformationfiltre(searchTerm).subscribe((formations: any[]) => {
      formations.forEach((formation) => {
        // Vérifiez d'abord si la propriété 'picture' existe dans la formation
        if (formation.picture) {
          // Convertissez l'image en URL base64
          formation.picture = 'data:image/jpeg;base64,' + formation.picture;
        }
      });
      console.log("les formations passe", formations);
      this.envoyerFormations.emit(formations);
    });
  }

  
}

