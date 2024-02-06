import { Component, OnInit, inject } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { SignupComponent } from 'src/app/components/signup/signup.component';
import { Formation } from 'src/app/shared/interfaces/formation.interface';
import {FormationService} from'src/app/shared/services/formation.service';


@Component({
  selector: 'app-details',
  templateUrl:'./details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent {
// formationdata:FormationModel[]=[];
 formation: Formation | undefined;
idformation:number|undefined;
public dialog = inject(MatDialog);
image:any;
constructor(private route: ActivatedRoute,private formationservice:FormationService,private router: Router){}
ngOnInit(){
  this.route.paramMap.subscribe(params => {
   this.idformation = Number(params.get('id'));
  });
  if (this.idformation !== undefined) {
    this.getformationdetails(this.idformation);
  }
}

getformationdetails(formationId: number){
   this.formationservice.getFormationByid(formationId).subscribe(
    (formationData: Formation) => {
      console.log("les donnes recus")
       this.formation = formationData;
       console.log("formation",this.formation);
       this.processFormationResponse(this.formation);
    },
    (error) => {
      console.error('Error fetching formation details:', error);
  //     // Handle error, e.g., display an error message to the user
    }
   )

}

processFormationResponse(formationData: Formation):void {
  if (formationData && formationData.picture) {
    console.log("les donnes ")
 formationData.picture = 'data:image/jpeg;base64,'+formationData.picture;
  console.log(formationData.picture);
  }
  this.formation = formationData;
  console.log("formation",this.formation.picture);
   }

//s 'inscrire a la formation
inscrireFormation(formationId:number){
  const dialogRef = this.dialog.open(SignupComponent, {
    width: '500px',
    data: {
      formationId: formationId,
    }
  });

}

//retourner a la page d acceuille
retourner(){
  this.router.navigate(['/accueille']);
}
}

