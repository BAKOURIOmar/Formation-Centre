import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {FormationService} from'src/app/shared/services/formation.service';


@Component({
  selector: 'app-details',
  templateUrl:'./details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent {
// formationdata:FormationModel[]=[];
// formation: FormationModel | undefined;
idformation:Number|undefined;
constructor(private route: ActivatedRoute,private formationservice:FormationService){}

ngOnInit(){
  this.route.paramMap.subscribe(params => {
   this.idformation = Number(params.get('id'));
  });
  if (this.idformation !== undefined) {
    this.getformationformationdetails(this.idformation);
  }
}
getformationformationdetails(formationId: Number){
  // this.formationservice.getFormationByid(formationId).subscribe(
  //   (formationData: FormationModel) => {
  //     console.log("les donnes recus")
  //     this.formation = formationData;
  //   },
  //   (error) => {
  //     console.error('Error fetching formation details:', error);
  //     // Handle error, e.g., display an error message to the user
  //   }
  // )
}

}
