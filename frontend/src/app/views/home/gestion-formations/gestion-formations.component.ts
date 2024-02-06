import { Component, ViewChild, inject } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar, MatSnackBarRef, SimpleSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { ConfirmationComponent } from 'src/app/components/confirmation/confirmation.component';
import { GroupesComponent } from 'src/app/components/groupes/groupes.component';
import { NewFormateurComponent } from 'src/app/components/new-formateur/new-formateur.component';
import { NewFormationComponent } from 'src/app/components/new-formation/new-formation.component';
import { Formation } from 'src/app/shared/interfaces/formation.interface';
import { PageResponse } from 'src/app/shared/interfaces/pageResponse.interface';
import { FormationService } from 'src/app/shared/services/formation.service';

@Component({
  selector: 'app-gestion-formations',
  templateUrl: './gestion-formations.component.html',
  styleUrls: ['./gestion-formations.component.css']
})
export class GestionFormationsComponent {


  private formationService = inject(FormationService);
  private snackBar = inject(MatSnackBar);
  public dialog = inject(MatDialog);

  ngOnInit(): void {
    this.getFormations();
  }

  displayedColumns: string[] = ['id', 'name', 'nombreh','seuil', 'cout', 'programme','ville', 'categorie','date','picture', 'actions'];
  dataSource = new MatTableDataSource<Formation>();

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  getFormations() {
    this.formationService.getFormations()
      .subscribe((data: PageResponse<Formation>) => {
        console.log("respuesta de productos: ", data);
        this.processFormationResponse(data.content);
      }, (error: any) => {
        console.log("error en productos: ", error);
      })
  }

  processFormationResponse(resp: any) {
    const dateFormation: Formation[] = [];

       resp.forEach((element: Formation) => {
         //element.category = element.category.name;
         element.picture = 'data:image/jpeg;base64,'+element.picture;
         dateFormation.push(element);
       });

       //set the datasource
    this.dataSource.data = resp;
    this.dataSource.paginator = this.paginator;
  }


openProductDialog(){
  const dialogRef = this.dialog.open( NewFormationComponent, {
    width: '450px'
  });

  dialogRef.afterClosed().subscribe((result:any) => {

    if( result == 1){
      this.openSnackBar("Formaion Ajouter", "Exitosa");
      this.getFormations();
    } else if (result == 2) {
      this.openSnackBar("un Erruer se produit a l'heure de ajouter une formation", "Error");
    }
  });
}

openSnackBar(message: string, action: string) : MatSnackBarRef < SimpleSnackBar > {
  return this.snackBar.open(message, action, {
    duration: 2000
  })

}

  edit(id:number, name: string, nombreh: number, seuil:number , cout: number, programme: string, ville: string, categorie: string, date:string){
    const dialogRef = this.dialog.open(NewFormationComponent , {
      width: '450px',
      data: {id: id, name: name, nombreh: nombreh ,seuil :seuil , cout: cout, programme: programme, ville: ville, categorie: categorie , date: date}
    });

    dialogRef.afterClosed().subscribe((result:any) => {

      if( result == 1){
        this.openSnackBar("Producto editado", "Exitosa");
        this.getFormations();
      } else if (result == 2) {
        this.openSnackBar("Se produjo un error al editar producto", "Error");
      }
    });


  }

  delete(id: any){
    const dialogRef = this.dialog.open(ConfirmationComponent , {
      width: '450px',
      data: {id: id, module: "formation"}
    });

    dialogRef.afterClosed().subscribe((result:any) => {

      if( result == 1){
        this.openSnackBar("Formation supprimer", "Exitosa");
        this.getFormations();
      } else if (result == 2) {
        this.openSnackBar("un erreur se produit a l'heure de supprimer la formation", "Error");
      }
    });
  }



  giveMeFeedBack(formationId : number) {
    const dialogRef = this.dialog.open( GroupesComponent, {
      width: '450px',
      height: '400px',
      data:{formationId :formationId}
    })
  }


  //recuperer le nom de recherche
  buscar(name: any) {
    if (name.length === 0) {
      this.getFormations(); // Si la recherche est vide, récupérez toutes les formations
    } else {
      this.formationService.getformationfiltre(name)
        .subscribe((data: PageResponse<Formation>) => {
          console.log("Réponse des formations filtrées par nom: ", data);
          this.processFormationResponse(data.content);
        }, (error: any) => {
          console.log("Erreur lors de la recherche des formations par nom: ", error);
        });
    }
  }
}


