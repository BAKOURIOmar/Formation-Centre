import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { PageResponse } from 'src/app/shared/interfaces/pageResponse.interface';
import { User } from 'src/app/shared/interfaces/user.interface';
import { FormateurService } from 'src/app/shared/services/formateur.service';

@Component({
  selector: 'app-new-assistant',
  templateUrl: './new-assistant.component.html',
  styleUrls: ['./new-assistant.component.css']
})
export class NewAssistantComponent {

  private fb = inject(FormBuilder);
  private formateurService = inject(FormateurService);
  private dialogRef= inject(MatDialogRef);
  public data = inject(MAT_DIALOG_DATA);


  public AssistantForm!: FormGroup;

  estatForm: string = "";
  assistants: User[]=[];


  ngOnInit(): void {

    this.getAssistants();

    this.estatForm = "Ajouter";
    this.AssistantForm = this.fb.group( {
      name: ['', Validators.required],
      email: ['', Validators.required],
      ville: ['', Validators.required],
      password: ['', Validators.required],
    })

    if (this.data != null ){
      this.updateForm(this.data);
      this.estatForm = "Actualiser";
    }
  }

  onSave(){
    let data = {
      name: this.AssistantForm.get('name')?.value,
      email: this.AssistantForm.get('email')?.value,
      ville: this.AssistantForm.get('ville')?.value,
      password: this.AssistantForm.get('password')?.value
    }



    if (this.data != null){
      //update the product
      this.formateurService.updateAssistant(data,this.data.id)
                .subscribe( (data: any) =>{
                  this.dialogRef.close(1);
                }, (error: any) => {
                  this.dialogRef.close(2);
                })
    } else {
      //call the service to save a product
      this.formateurService.addAssistant(data)
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

  getAssistants(){
    this.formateurService.getUsersByRole('ROLE_ASSISTANT')
        .subscribe( (data: PageResponse<User>) =>{
          this.assistants = data.content;
        }, (error: any) =>{
          console.log("error pou consulter les formateurs");
        })
  }


  updateForm(data: any){

    this.AssistantForm = this.fb.group( {
      name: [data.name, Validators.required],
      email: [data.email, Validators.required],
      ville: [data.ville, Validators.required],
      password: ['', Validators.required],
    })
  }
}
