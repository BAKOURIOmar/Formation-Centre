import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { User } from 'src/app/shared/interfaces/user.interface';
import { FormateurService } from 'src/app/shared/services/formateur.service';

@Component({
  selector: 'app-new-formateur',
  templateUrl: './new-formateur.component.html',
  styleUrls: ['./new-formateur.component.css']
})
export class NewFormateurComponent {

  private fb = inject(FormBuilder);
  private formateurService = inject(FormateurService);
  private dialogRef= inject(MatDialogRef);
  public data = inject(MAT_DIALOG_DATA);


  public formateurForm!: FormGroup;

  estatForm: string = "";
  formateurs: User[]=[];


  ngOnInit(): void {

    this.getFormations();

    this.estatForm = "Ajouter";
    this.formateurForm = this.fb.group( {
      name: ['', Validators.required],
      email: ['', Validators.required],
      motcles: ['', Validators.required],
      remarque: ['', Validators.required],
      password: ['', Validators.required],
    })

    if (this.data != null ){
      this.updateForm(this.data);
      this.estatForm = "Actualiser";
    }
  }

  onSave(){
    let data = {
      name: this.formateurForm.get('name')?.value,
      email: this.formateurForm.get('email')?.value,
      motcles: this.formateurForm.get('motcles')?.value,
      remarque: this.formateurForm.get('remarque')?.value,
      password: this.formateurForm.get('password')?.value,
      type: 'INTERNE',
    }



    if (this.data != null){
      //update the product
      this.formateurService.updateFormateur(data,this.data.id)
                .subscribe( (data: any) =>{
                  this.dialogRef.close(1);
                }, (error: any) => {
                  this.dialogRef.close(2);
                })
    } else {
      //call the service to save a product
      this.formateurService.addFormateur(data)
              .subscribe( (data: any) =>{
                this.dialogRef.close(1);
              }, (error: any) => {
                this.dialogRef.close(2);
              })
    }

  }

  onCancel(){
    this.dialogRef.close(3);
  }

  getFormations(){
    this.formateurService.getUsersByRole('ROLE_FORMATEUR')
        .subscribe( (data: any) =>{
          this.formateurs = data;
        }, (error: any) =>{
          console.log("error pou consulter les formateurs");
        })
  }


  updateForm(data: any){

    this.formateurForm = this.fb.group( {
      name: [data.name, Validators.required],
      email: [data.email, Validators.required],
      motcles: [data.motcles, Validators.required],
      remarque: [data.remarque, Validators.required],
      password: ['', Validators.required],
    })
  }
}
