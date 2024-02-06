import { Component, ViewChild, inject } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar, MatSnackBarRef, SimpleSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { ConfirmationComponent } from 'src/app/components/confirmation/confirmation.component';
import { NewEntrepriseComponent } from 'src/app/components/new-entreprise/new-entreprise.component';
import { Entreprise } from 'src/app/shared/interfaces/entreprise.interface';
import { PageResponse } from 'src/app/shared/interfaces/pageResponse.interface';
import { EntrepriseService } from 'src/app/shared/services/entreprise.service';

@Component({
  selector: 'app-gestion-entreprise',
  templateUrl: './gestion-entreprise.component.html',
  styleUrls: ['./gestion-entreprise.component.css']
})
export class GestionEntrepriseComponent {
  private entrepriseService = inject(EntrepriseService);
  private snackBar = inject(MatSnackBar);
  public dialog = inject(MatDialog);
  dataSource = new MatTableDataSource<Entreprise>(); // Utilisez le type Entreprise ici


  ngOnInit(): void {
    this.getAssistants();
  }

  displayedColumns: string[] = ['id', 'name', 'adresse', 'tel', 'url', 'email', 'actions'];

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  getAssistants(): void {
    console.log("hshshshsh")
    this.entrepriseService.getEntreprises()
      .subscribe((data: PageResponse<Entreprise>) => {
        this.processEntrepriseResponse(data.content);
      }, (error: any) => {
        console.log("error: ", error);
      })
  }

  processEntrepriseResponse(resp: Entreprise[]): void {
    this.dataSource.data = resp;
    this.dataSource.paginator = this.paginator;
  }

  openEntrepriseDialog(): void {
    const dialogRef = this.dialog.open(NewEntrepriseComponent, {
      width: '450px'
    });

    dialogRef.afterClosed().subscribe((result: any) => {
      if (result == 1) {
        this.openSnackBar("Entreprise Ajouter", "Exitosa");
        this.getAssistants();
      } else if (result == 2) {
        this.openSnackBar("un erreur se produit a l'heur d'ajoter le Entreprise ", "Error");
      }
    });
  }

  // Méthodes d'édition et de suppression
  edit(id: number, name: string, adresse: string, tel: number, url: string, email: string) {
    const dialogRef = this.dialog.open(NewEntrepriseComponent, {
      width: '450px',
      data: { id: id, name: name, adresse: adresse, tel: tel, url: url, email: email }
    });

    dialogRef.afterClosed().subscribe((result: any) => {

      if (result == 1) {
        this.openSnackBar("Entreprise Actualiser", "Exitosa");
        this.getAssistants();
      } else if (result == 2) {
        this.openSnackBar("un erreur a produit l'heur de la modification de Entreprise", "Error");
      }
    });
  }

  delete(id: any) {
    const dialogRef = this.dialog.open(ConfirmationComponent, {
      width: '450px',
      data: { id: id, module: "entreprise" }
    });

    dialogRef.afterClosed().subscribe((result: any) => {

      if (result == 1) {
        this.openSnackBar("Entreprise supprimer", "OK");
        this.getAssistants();
      } else if (result == 2) {
        this.openSnackBar("un erreur se produit a l'heure de supprimer la Entreprise", "Error");
      }
    });
  }

  buscar(termino: string): void {
    if (termino.trim()) {
      this.entrepriseService.getEntrepriseByName(termino)
        .subscribe((data: Entreprise[]) => {
          this.dataSource.data = data;
        }, (error: any) => {
          console.log("error: ", error);
        });
    } else {
      this.getAssistants(); // Si le terme de recherche est vide, rechargez toutes les entreprises
    }
  }

  openSnackBar(message: string, action: string): MatSnackBarRef<SimpleSnackBar> {
    return this.snackBar.open(message, action, {
      duration: 2000
    });
  }
}
