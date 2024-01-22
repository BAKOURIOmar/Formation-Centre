import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {FormationService} from 'src/app/shared/services/formation.service';

import {FormationModel} from 'src/app/shared/Models/FormationModel.model';

@Component({
  selector: 'app-cardformation',
  templateUrl:'./cardformation.component.html',
  styleUrls: ['./cardformation.component.css']
})
export class CardformationComponent implements OnInit {

  FormationData:FormationModel[]=[];
  showForm = false; // Ajout de la propriété pour contrôler l'affichage du formulaire
  nom: string = ""; // Ajout de la propriété pour stocker le nom du participant
  email: string = "";

 constructor(private formationservice:FormationService){}

 ngOnInit(): void {
//    this.showformation();
 }


public showformation() {
  console.log("avant l appel ")
this.formationservice.recupererformation().subscribe(
  (res: FormationModel[])=>{
    this.FormationData= res;
  },
  (err)=>{
    console.log(err);
  }
)
}
 onSubmit(){
  console.log("called");
  console.log("Nom:", this.nom);
  console.log("Email:", this.email);
 }


}
