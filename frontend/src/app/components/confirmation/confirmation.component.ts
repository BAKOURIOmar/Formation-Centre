import { Component, inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { EntrepriseService } from 'src/app/shared/services/entreprise.service';
import { FormateurService } from 'src/app/shared/services/formateur.service';
import { FormationService } from 'src/app/shared/services/formation.service';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.css']
})
export class ConfirmationComponent {
  private formationService= inject(FormationService);
  private formateurService= inject(FormateurService);
  private entrepriseService= inject(EntrepriseService);
  private dialogRef= inject(MatDialogRef);
  public data = inject(MAT_DIALOG_DATA);


  onNoClick(){
    this.dialogRef.close(3)
  }

  delete(){
    if (this.data != null){

      if (this.data.module == "formation") {

        this.formationService.deleteFormation(this.data.id).
              subscribe( (data:any) =>{
                this.dialogRef.close(1);
              }, (error: any) => {
                this.dialogRef.close(2);
              })
      } else if ( this.data.module == "formateur" )  {
            this.formateurService.deleteFormateur(this.data.id).
              subscribe( (data:any) =>{
                this.dialogRef.close(1);
              }, (error: any) => {
                this.dialogRef.close(2);
              })
      } else if ( this.data.module == "assistant" )  {
        this.formateurService.deleteFormateur(this.data.id).
          subscribe( (data:any) =>{
            this.dialogRef.close(1);
          }, (error: any) => {
            this.dialogRef.close(2);
          })
      } else if ( this.data.module == "entreprise" )  {
        this.entrepriseService.deleteEntreprise(this.data.id).
          subscribe( (data:any) =>{
            this.dialogRef.close(1);
          }, (error: any) => {
            this.dialogRef.close(2);
          })
  }

    } else {
      this.dialogRef.close(2);
    }
  }

}
