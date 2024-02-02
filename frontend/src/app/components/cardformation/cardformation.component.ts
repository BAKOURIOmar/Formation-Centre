import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {FormationService} from 'src/app/shared/services/formation.service';
import {Formation} from 'src/app/shared/interfaces/formation.interface';
import { Router } from '@angular/router';
import { PageResponse } from 'src/app/shared/interfaces/pageResponse.interface';



@Component({
  selector: 'app-cardformation',
  templateUrl:'./cardformation.component.html',
  styleUrls: ['./cardformation.component.css']
})
export class CardformationComponent implements OnInit {

  formations:Formation[]=[];
 constructor(private formationservice:FormationService, private router: Router){}

 ngOnInit(): void {
this.showformation();
 }

 public showformation() {


   this.formationservice.getFormations().subscribe(
    (formations:PageResponse<Formation>) => {
      this.formations = formations.content; // Assurez-vous que formations est de type Formation[]
      console.log("data",this.formations);
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
       //element.category = element.category.name;
       element.picture = 'data:image/jpeg;base64,'+element.picture;
       console.log("image transferer");
       dateFormation.push(element);
     });
    }


exploreDetails(formationId: number){
    this.router.navigate(['/detail', formationId]);
    console.log("id", formationId);
    console.log("rederiger");

}

//s inscrire button
inscrireFormation(formationId:number){
  console.log("recuperer");
  console.log('id',formationId);
  this.router.navigate(['/inscrire',formationId]);


}
}

