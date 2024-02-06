import { Component, OnInit, inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Group } from 'src/app/shared/interfaces/group.interface';
import { GroupService } from 'src/app/shared/services/group.service';

@Component({
  selector: 'app-groupes',
  templateUrl: './groupes.component.html',
  styleUrls: ['./groupes.component.css']
})
export class GroupesComponent implements OnInit{

  private groupService = inject(GroupService);
  public data = inject(MAT_DIALOG_DATA);
  groupes: Group[]=[];

  ngOnInit(): void {
    this.getGroupes(this.data.formationId);
  }
  getGroupes(idFormation : any){
    this.groupService.getGroupesByIdformation(idFormation)
        .subscribe( (data: Group[]) =>{
          this.groupes = data;
        }, (error: any) =>{
          console.log("error pou consulter les Groupes");
        })
  }
  GivemeFeedBack(groupId:number){
    this.groupService.GivemeFeedBack(groupId)
        .subscribe( () =>{

        }, (error: any) =>{
          console.log("error pour le feed back");
        })
  }
}
