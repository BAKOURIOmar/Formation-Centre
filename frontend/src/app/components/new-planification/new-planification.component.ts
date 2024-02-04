import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Entreprise } from 'src/app/shared/interfaces/entreprise.interface';
import { Formation } from 'src/app/shared/interfaces/formation.interface';
import { Group } from 'src/app/shared/interfaces/group.interface';
import { PageResponse } from 'src/app/shared/interfaces/pageResponse.interface';
import { User } from 'src/app/shared/interfaces/user.interface';
import { EntrepriseService } from 'src/app/shared/services/entreprise.service';
import { FormateurService } from 'src/app/shared/services/formateur.service';
import { FormationService } from 'src/app/shared/services/formation.service';
import { GroupService } from 'src/app/shared/services/group.service';
import { PlanificationService } from 'src/app/shared/services/planification.service';

@Component({
  selector: 'app-new-planification',
  templateUrl: './new-planification.component.html',
  styleUrls: ['./new-planification.component.css']
})
export class NewPlanificationComponent {
  private fb = inject(FormBuilder);
  private planificationService = inject(PlanificationService);
  private formationService = inject(FormationService);
  private formateurService = inject(FormateurService);
  private entrepriseService = inject(EntrepriseService);
  private groupService = inject(GroupService);
  private dialogRef= inject(MatDialogRef);
  public data = inject(MAT_DIALOG_DATA);


  public planificationForm!: FormGroup;

  estatForm: string = "";
  defaultValue: string = '1';
  palnifications: any[]=[];
  formations: Formation[]=[];
  formateurs: User[]=[];
  entreprises: Entreprise[]=[];
  groupes: Group[]=[];
  selected :number = 1 ;

  constructor(){
  }
  ngOnInit(): void {
    this.getFormations();
   // this.getPlanifications();
    this.getFormateurs();
    this.getEntreprises();
    console.log('-------'+this.data.start);
    console.log('---------'+this.data.end);

    this.estatForm = "Ajouter";
    this.planificationForm = this.fb.group( {
      datedebut: [this.data.start, Validators.required],
      datefin: [this.data.end, Validators.required],
      title: ['', Validators.required],
      formationId: ['', Validators.required],
      formateurId: ['', Validators.required],
      groupOrEnterprise: [1, Validators.required],
      entrepriseId: [''],
      groupeId: ['', Validators.required],
    })


    if (this.data.mode === 'update' ){
      this.updateForm(this.data);
      this.estatForm = "Actualiser";
    }
  }
  getFormations(){
    this.formationService.getFormations()
        .subscribe( (data: PageResponse<Formation>) =>{
          this.formations = data.content;
        }, (error: any) =>{
          console.log("error pou consulter les formation");
        })
  }

  getFormateurs(){
    this.formateurService.getUsersByRole('ROLE_FORMATEUR')
        .subscribe( (data: any) =>{
          this.formateurs = data.content;
        }, (error: any) =>{
          console.log("error pou consulter les formateurs");
        })
  }
  getEntreprises(){
    this.entrepriseService.getEntreprises()
        .subscribe( (data: PageResponse<Entreprise>) =>{
          this.entreprises = data.content;
        }, (error: any) =>{
          console.log("error pou consulter les Entreprises");
        })
  }
  getGroupes(idFormation : any){
    this.groupService.getGroupesByIdformation(idFormation)
        .subscribe( (data: Group[]) =>{
          this.groupes = data;
        }, (error: any) =>{
          console.log("error pou consulter les Groupes");
        })
  }
  changeValue(){
    console.log(this.planificationForm.get('groupOrEnterprise')?.value);
    this.selected = this.planificationForm.get('groupOrEnterprise')?.value ;
    if(this.selected===1){
      this.planificationForm.get('groupeId')?.setValidators(Validators.required);
      this.planificationForm.get('entrepriseId')?.setValidators([]);
    }else{
      this.planificationForm.get('entrepriseId')?.setValidators(Validators.required);
      this.planificationForm.get('groupeId')?.setValidators([]);
    }

  this.planificationForm.get('entrepriseId')?.updateValueAndValidity();
  this.planificationForm.get('groupeId')?.updateValueAndValidity();

  }

  onSave(){
    let data = {
      datedebut: this.planificationForm.get('datedebut')?.value,
      datefin: this.planificationForm.get('datefin')?.value,
      title: this.planificationForm.get('title')?.value,
      formationId: this.planificationForm.get('formationId')?.value,
      formateurId: this.planificationForm.get('formateurId')?.value,
      entrepriseId: this.planificationForm.get('entrepriseId')?.value,
      groupeId: this.planificationForm.get('groupeId')?.value,
    }



    if (this.data.mode === 'update'){
      //update the product

      this.planificationService.updatePlanification(data,this.data.eventData.id)
                .subscribe( (data: any) =>{
                  this.dialogRef.close(1);
                }, (error: any) => {
                  this.dialogRef.close(2);
                })
    } else {
      //call the service to save a product
      this.planificationService.addPlanification(data)
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

  // getPlanifications(){
  //   this.planificationService.getUsersByRole('ROLE_FORMATEUR')
  //       .subscribe( (data: any) =>{
  //         this.formateurs = data;
  //       }, (error: any) =>{
  //         console.log("error pou consulter les formateurs");
  //       })
  // }


  updateForm(data: any){
    console.log("formation id "+data.eventData.formationId);
    if(data.eventData.formationId){
      this.getGroupes(data.eventData.formationId);
    }
    this.planificationForm = this.fb.group( {
      datedebut: [data.eventData.datedebut, Validators.required],
      datefin: [data.eventData.datefin, Validators.required],
      title: [data.eventData.title, Validators.required],
      formationId: [data.eventData.formationId, Validators.required],
      formateurId: [data.eventData.formateurId, Validators.required],
      groupOrEnterprise: [data.eventData.groupeId!==null?1:2, Validators.required],
      entrepriseId: [data.eventData.entrepriseId],
      groupeId: [data.eventData.groupeId, Validators.required],
    })
  }
}
