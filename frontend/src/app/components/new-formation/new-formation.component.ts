import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Formation } from 'src/app/shared/interfaces/formation.interface';
import { FormationService } from 'src/app/shared/services/formation.service';

@Component({
  selector: 'app-new-formation',
  templateUrl: './new-formation.component.html',
  styleUrls: ['./new-formation.component.css']
})
export class NewFormationComponent {

  private fb = inject(FormBuilder);
  private formationService = inject(FormationService);
  private dialogRef= inject(MatDialogRef);
  public data = inject(MAT_DIALOG_DATA);


  public formationForm!: FormGroup;

  estatForm: string = "";
  formations: Formation[]=[];
  selectedFile: any;
  nameImg: string ="";

  ngOnInit(): void {

    this.getFormations();

    this.estatForm = "Ajouter";
    this.formationForm = this.fb.group( {
      name: ['', Validators.required],
      nombreh: ['', Validators.required],
      cout: ['', Validators.required],
      programme: ['', Validators.required],
      ville: ['', Validators.required],
      categorie: ['', Validators.required],
      picture: ['', Validators.required]
    })

    if (this.data != null ){
      this.updateForm(this.data);
      this.estatForm = "Actualiser";
    }
  }

  onSave(){
    let data = {
      name: this.formationForm.get('name')?.value,
      nombreh: this.formationForm.get('nombreh')?.value,
      cout: this.formationForm.get('cout')?.value,
      programme: this.formationForm.get('programme')?.value,
      ville: this.formationForm.get('ville')?.value,
      categorie: this.formationForm.get('categorie')?.value,
    }

    // const uploadImageData = new FormData();
    // uploadImageData.append('picture', this.selectedFile);
    // uploadImageData.append('form', data);

    if (this.data != null){
      //update the product
      this.formationService.updateFormation(this.data.id,this.selectedFile,data)
                .subscribe( (data: any) =>{
                  this.dialogRef.close(1);
                }, (error: any) => {
                  this.dialogRef.close(2);
                })
    } else {
      //call the service to save a product
      this.formationService.addFormation(data,this.selectedFile)
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
    this.formationService.getFormations()
        .subscribe( (data: any) =>{
          this.formations = data;
        }, (error: any) =>{
          console.log("error pou consulter les formation");
        })
  }

  onFileChanged(event: any){

    this.selectedFile = event.target.files[0];
    console.log(this.selectedFile);

    this.nameImg = event.target.files[0].name;


  }

  updateForm(data: any){

    this.formationForm = this.fb.group( {
      name: [data.name, Validators.required],
      nombreh: [data.nombreh, Validators.required],
      cout: [data.cout, Validators.required],
      programme: [data.programme, Validators.required],
      ville: [data.ville, Validators.required],
      categorie: [data.categorie, Validators.required],
      picture: ['', Validators.required]
    })
  }
}
