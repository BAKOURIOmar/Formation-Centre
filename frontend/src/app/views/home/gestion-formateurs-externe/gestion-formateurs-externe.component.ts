import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { FormateurService } from 'src/app/shared/services/formateur.service';
import { User } from 'src/app/shared/interfaces/user.interface';
import { PageResponse } from 'src/app/shared/interfaces/pageResponse.interface';


@Component({
  selector: 'app-gestion-formateurs-externe',
  templateUrl: './gestion-formateurs-externe.component.html',
  styleUrls: ['./gestion-formateurs-externe.component.css']
})
export class GestionFormateursExterneComponent implements OnInit {
  displayedColumns: string[] = ['id', 'name', 'motcles', 'email', 'type', 'actions'];
  dataSource = new MatTableDataSource<User>();

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private formateurService: FormateurService) { }

  ngOnInit(): void {
    this.getFormateursExternes();
  }

  getFormateursExternes(): void {
    this.formateurService.getUsersByRole('ROLE_FORMATEUR').subscribe(
      (data: PageResponse<User>) => {
        const formateursExternes = data.content.filter(user => user.type === 'EXTERNE');
        this.dataSource.data = formateursExternes;
        this.dataSource.paginator = this.paginator;
      },
      (error: any) => {
        console.log("error: ", error);
      }
    );
  }

  edit(id: number): void {
    // Recherche du formateur à mettre à jour dans la liste des formateurs chargée dans la dataSource
    const formateurToUpdate = this.dataSource.data.find(formateur => formateur.id === id);

    if (formateurToUpdate) {
      // Modification du type du formateur à 'INTERNE'
      formateurToUpdate.type = 'INTERNE';

      // Appel du service pour mettre à jour le formateur sur le serveur
      this.formateurService.updateFormateur(formateurToUpdate, id).subscribe(
        (response: any) => {
          // Réaction à la réponse réussie du serveur
          console.log('Formateur mis à jour avec succès : ', response);
          // Mettre à jour la liste des formateurs dans le dataSource
          const updatedData = this.dataSource.data.map(item => {
            if (item.id === formateurToUpdate.id) {
              return formateurToUpdate;
            }
            return item;
          });
          this.dataSource.data = updatedData; // Mettre à jour la source de données
          console.log("Formateur mis à jour avec succès");
        },
        (error: any) => {
          // Gestion des erreurs lors de la mise à jour du formateur
          console.error('Erreur lors de la mise à jour du formateur : ', error);
          console.error("Erreur lors de la mise à jour du formateur");
        }
      );
    } else {
      // Affichage d'une erreur si le formateur n'est pas trouvé pour l'ID fourni
      console.error('Formateur non trouvé pour l\'ID fourni.');
    }
  }

  delete(id: number): void {
    this.formateurService.deleteFormateur(id).subscribe(
      (response: any) => {
        console.log('Formateur supprimé avec succès : ', response);
        this.getFormateursExternes();
      },
      (error: any) => {
        console.error('Erreur lors de la suppression du formateur : ', error);
      }
    );
  }
}




