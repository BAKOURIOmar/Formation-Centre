import { Component, ViewChild, inject } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar, MatSnackBarRef, SimpleSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { CalendarOptions } from '@fullcalendar/core';
import dayGridPlugin from '@fullcalendar/daygrid';
import interactionPlugin from '@fullcalendar/interaction';
import { ConfirmationComponent } from 'src/app/components/confirmation/confirmation.component';
import { NewFormateurComponent } from 'src/app/components/new-formateur/new-formateur.component';
import { PageResponse } from 'src/app/shared/interfaces/pageResponse.interface';
import { User } from 'src/app/shared/interfaces/user.interface';
import { FormateurService } from 'src/app/shared/services/formateur.service';
@Component({
  selector: 'app-gestion-formatuers',
  templateUrl: './gestion-formatuers.component.html',
  styleUrls: ['./gestion-formatuers.component.css']
})
export class GestionFormatuersComponent {

  private formateurService = inject(FormateurService);
  private snackBar = inject(MatSnackBar);
  public dialog = inject(MatDialog);

  ngOnInit(): void {
    this.getFormateurs();
  }

  displayedColumns: string[] = ['id', 'name', 'motcles', 'email','remarque','type','rating','actions'];
  dataSource = new MatTableDataSource<User>();

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  getFormateurs(): void {

   console.log("hshshshsh")
    this.formateurService.getUsersByRole('ROLE_FORMATEUR')
      .subscribe( (data:PageResponse<User>) => {

        this.processCategoriesResponse(data.content);

      }, (error: any) => {
        console.log("error: ", error);
      })
  }

  processCategoriesResponse(resp: any){

      this.dataSource.data = resp.map( (e: any) => {
        if(e.feedbacks && e.feedbacks.length>0) {
          e.rating = e.feedbacks.reduce((a: any, b: any) => a + b.rating, 0) / e.feedbacks.length;
        }
        else{
          e.rating = "0"
        }
        return e;
      });
      this.dataSource.paginator = this.paginator;


  }

  openFormateurDialog(){
    const dialogRef = this.dialog.open(NewFormateurComponent , {
      width: '450px'
    });

    dialogRef.afterClosed().subscribe((result:any) => {

      if( result == 1){
        this.openSnackBar("Formateur Ajouter", "Succès");
        this.getFormateurs();
      } else if (result == 2) {
        this.openSnackBar("un erreur se produit a l'heur d'ajoter le formateur ", "Error");
      }
    });
  }

  edit(id:number, name: string, motcles: string, email: string, remarque: string){
    const dialogRef = this.dialog.open(NewFormateurComponent , {
      width: '450px',
      data: {id: id, name: name, motcles: motcles, email: email , remarque: remarque}
    });

    dialogRef.afterClosed().subscribe((result:any) => {

      if( result == 1){
        this.openSnackBar("Formateur Actualiser", "Exitosa");
        this.getFormateurs();
      } else if (result == 2) {
        this.openSnackBar("un erreur a produit l'heur de la modification de formateur", "Error");
      }
    });
  }

  delete(id: any){
    const dialogRef = this.dialog.open(ConfirmationComponent , {
      width: '450px',
      data: {id: id, module: "formateur"}
    });

    dialogRef.afterClosed().subscribe((result:any) => {

      if( result == 1){
        this.openSnackBar("Formateur supprimer", "OK");
        this.getFormateurs();
      } else if (result == 2) {
        this.openSnackBar("un erreur se produit a l'heure de supprimer la Formateur", "Error");
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

