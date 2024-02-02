import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FormationService } from 'src/app/shared/services/formation.service';
import { Formation } from 'src/app/shared/interfaces/formation.interface';
import { Router } from '@angular/router';
import { SignupComponent } from '../signup/signup.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-cardformation',
  templateUrl: './cardformation.component.html',
  styleUrls: ['./cardformation.component.css']
})
export class CardformationComponent implements OnInit {

  formations: Formation[] = [];

  constructor(private formationservice: FormationService, private router: Router, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.showformation();
  }

  public showformation() {
    this.formationservice.getFormations().subscribe(
      (formations: any) => {
        this.formations = formations; // Assurez-vous que formations est de type Formation[]
        console.log("data", this.formations);
        this.processFormationResponse(this.formations);
      },
      (error) => {
        console.error('Error fetching formations:', error);
      }
    );
  }

  processFormationResponse(resp: any) {
    const dateFormation: Formation[] = [];
    console.log("avantle fetch")
    resp.forEach((element: Formation) => {
      element.picture = 'data:image/jpeg;base64,' + element.picture;
      console.log("image transferer");
      dateFormation.push(element);
    });
  }

  exploreDetails(formationId: number) {
    this.router.navigate(['/detail', formationId]);
    console.log("id", formationId);
    console.log("rediriger");
  }

  //s'inscrire button
  inscrireFormation(formationId: number) {
    console.log("recuperer");
    console.log('id', formationId);
    this.router.navigate(['/inscrire', formationId]);
  }

  openSignup() {
    // Implémentez les actions à effectuer lorsque le formulaire d'inscription est ouvert
    this.dialog.open(SignupComponent, {
      width: '450px'
    });
    console.log("Ouverture du formulaire d'inscription");
  }
}