import { Component, ViewChild, inject } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar, MatSnackBarRef, SimpleSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { CalendarOptions } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import { ConfirmationComponent } from 'src/app/components/confirmation/confirmation.component';
import { NewAssistantComponent } from 'src/app/components/new-assistant/new-assistant.component';
import { NewFormateurComponent } from 'src/app/components/new-formateur/new-formateur.component';
import { User } from 'src/app/shared/interfaces/user.interface';
import { FormateurService } from 'src/app/shared/services/formateur.service';

@Component({
  selector: 'app-gestion-assistants',
  templateUrl: './gestion-assistants.component.html',
  styleUrls: ['./gestion-assistants.component.css']
})
export class GestionAssistantsComponent {

  private formateurService = inject(FormateurService);
  private snackBar = inject(MatSnackBar);
  public dialog = inject(MatDialog);

  ngOnInit(): void {
    this.getAssistants();
  }

  displayedColumns: string[] = ['id', 'name', 'email','ville','actions'];
  dataSource = new MatTableDataSource<User>();

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  getAssistants(): void {
   console.log("hshshshsh")
    this.formateurService.getUsersByRole('ROLE_ASSISTANT')
      .subscribe( (data:any) => {

        this.processAssistantResponse(data);

      }, (error: any) => {
        console.log("error: ", error);
      })
  }

  processAssistantResponse(resp: any){

      this.dataSource.data = resp;
      this.dataSource.paginator = this.paginator;


  }

  openFormateurDialog(){
    const dialogRef = this.dialog.open(NewAssistantComponent , {
      width: '450px'
    });

    dialogRef.afterClosed().subscribe((result:any) => {

      if( result == 1){
        this.openSnackBar("Assistant Ajouter", "Exitosa");
        this.getAssistants();
      } else if (result == 2) {
        this.openSnackBar("un erreur se produit a l'heur d'ajoter le Assistant ", "Error");
      }
    });
  }

  edit(id:number, name: string,  email: string, ville: string){
    const dialogRef = this.dialog.open(NewAssistantComponent , {
      width: '450px',
      data: {id: id, name: name,  email: email , ville: ville}
    });

    dialogRef.afterClosed().subscribe((result:any) => {

      if( result == 1){
        this.openSnackBar("Assistant Actualiser", "Exitosa");
        this.getAssistants();
      } else if (result == 2) {
        this.openSnackBar("un erreur a produit l'heur de la modification de Assistant", "Error");
      }
    });
  }

  delete(id: any){
    const dialogRef = this.dialog.open(ConfirmationComponent , {
      width: '450px',
      data: {id: id, module: "assistant"}
    });

    dialogRef.afterClosed().subscribe((result:any) => {

      if( result == 1){
        this.openSnackBar("Assistant supprimer", "OK");
        this.getAssistants();
      } else if (result == 2) {
        this.openSnackBar("un erreur se produit a l'heure de supprimer la Assistant", "Error");
      }
    });
  }

  buscar( termino: string){

    // if( termino.length === 0){
    //   return this.getCategories();
    // }

    // this.categoryService.getCategorieById(termino)
    //         .subscribe( (resp: any) => {
    //           this.processCategoriesResponse(resp);
    //         })
  }

  openSnackBar(message: string, action: string) : MatSnackBarRef<SimpleSnackBar>{
    return this.snackBar.open(message, action, {
      duration: 2000
    })

  }

}
