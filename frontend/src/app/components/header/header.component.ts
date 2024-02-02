import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { RegisterComponent } from 'src/app/views/register/register.component';
import { FormationService } from 'src/app/shared/services/formation.service';
import { Formation } from 'src/app/shared/interfaces/formation.interface';
import { HttpClient } from '@angular/common/http';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  formation:Formation | undefined;

  @Output() envoyerSearchTerm : EventEmitter<string>=new EventEmitter<string>();

searchTerm: string = '';
  constructor(private dialog: MatDialog,
              private http: HttpClient,
              private formationService: FormationService
              ) { }

  ngOnInit(): void {
    this.formationService.getFormations().subscribe(formations => {
    });
  }

  openRegisterDialog() {
    this.dialog.open(RegisterComponent, {
      width: '450px'
    });
  }

  filterFormations(event: Event): void {
    const target = event.target as HTMLInputElement;
    const searchTerm = target.value;
    console.log("searched",searchTerm);
    this.envoyerSearchTerm.emit(searchTerm);
  }




}
