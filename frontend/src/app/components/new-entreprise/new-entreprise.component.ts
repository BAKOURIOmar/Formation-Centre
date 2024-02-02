import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Entreprise } from 'src/app/shared/interfaces/entreprise.interface';
import { PageResponse } from 'src/app/shared/interfaces/pageResponse.interface';
import { User } from 'src/app/shared/interfaces/user.interface';
import { EntrepriseService } from 'src/app/shared/services/entreprise.service';

@Component({
  selector: 'app-new-entreprise',
  templateUrl: './new-entreprise.component.html',
  styleUrls: ['./new-entreprise.component.css']
})
export class NewEntrepriseComponent {
  private fb = inject(FormBuilder);
  private entrepriseService = inject(EntrepriseService);
  private dialogRef= inject(MatDialogRef);
  public data = inject(MAT_DIALOG_DATA);


  public entrepriseForm!: FormGroup;

  estatForm: string = "";
  entreprises: Entreprise[]=[];


  ngOnInit(): void {

    this.getEntreprises();

    this.estatForm = "Ajouter";
    this.entrepriseForm = this.fb.group( {
      name: ['', Validators.required],
      adresse: ['', Validators.required],
      tel: ['', Validators.required],
      url: ['', Validators.required],
      email: ['', Validators.required],
    })

    if (this.data != null ){
      this.updateForm(this.data);
      this.estatForm = "Actualiser";
    }
  }

  onSave(){
    let data = {
      name: this.entrepriseForm.get('name')?.value,
      adresse: this.entrepriseForm.get('adresse')?.value,
      tel: this.entrepriseForm.get('tel')?.value,
      url: this.entrepriseForm.get('url')?.value,
      email: this.entrepriseForm.get('email')?.value,
    }



    if (this.data != null){
      //update the product
      this.entrepriseService.updateEntreprise(data,this.data.id)
                .subscribe( (data: any) =>{
                  this.dialogRef.close(1);
                }, (error: any) => {
                  this.dialogRef.close(2);
                })
    } else {
      //call the service to save a product
      this.entrepriseService.addEntreprise(data)
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

  getEntreprises(){
    this.entrepriseService.getEntreprises()
        .subscribe( (data: PageResponse<Entreprise>) =>{
          this.entreprises = data.content;
        }, (error: any) =>{
          console.log("error pou consulter les formateurs");
        })
  }


  updateForm(data: any){

    this.entrepriseForm = this.fb.group( {
      name: [data.name, Validators.required],
      adresse: [data.email, Validators.required],
      tel: [data.tel, Validators.required],
      url: [data.url, Validators.required],
      email: [data.email, Validators.required],
    })
  }
}
