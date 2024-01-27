import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {FormationService} from 'src/app/shared/services/formation.service';
import { FormationModel } from 'src/app/shared/Models/FormationModel.model';
import { Router } from '@angular/router';



@Component({
  selector: 'app-cardformation',
  templateUrl:'./cardformation.component.html',
  styleUrls: ['./cardformation.component.css']
})
export class CardformationComponent implements OnInit {

  FormationData:FormationModel[]=[];
  showForm = false; // Ajout de la propriété pour contrôler l'affichage du formulaire nom: string = ""; // Ajout de la propriété pour stocker le nom du participant email: string = "";

 constructor(private formationservice:FormationService, private router: Router){}

 ngOnInit(): void {
this.showformation();
 }

 public showformation() {
  this.formationservice.getFormations().subscribe(
    (data: FormationModel[]) => {
      this.FormationData = data;
      console.log("retourne")
    },
    (error) => {
      console.log("ne pas afficher");
      console.error('Error fetching formations:', error);
    }
  );
}
exploreDetails(formationId: number){
    this.router.navigate(['/detail', formationId]);
    console.log("id", formationId);
    console.log("rederiger");

}

}

