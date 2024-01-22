import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {FormationService} from 'src/app/shared/services/formation.service';

@Component({
  selector: 'app-cardformation',
  templateUrl: './cardformation.component.html',
  styleUrls: ['./cardformation.component.css']
})
export class CardformationComponent implements OnInit {

 constructor(private formationservice:FormationService){}

 ngOnInit(): void {
//    this.showformation();
 }

// public showformation() {
//   console.log("je peux ")
//   this.formationservice.afficherFormation().subscribe(
//     (res: FormationModule[]) => {
//       console.log ("afficheer")
//       this.Formationdata = res;
//     },
//     (err: HttpErrorResponse) => {
//       console.log("ne peux pas")
//       console.log(err);
//     }
//   );
// }
}
