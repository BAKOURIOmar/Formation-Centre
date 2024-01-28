/*import { Component } from '@angular/core';

@Component({
  selector: 'app-accueille',
  templateUrl: './accueille.component.html',
 // styleUrls: ['./accueille.component.css']
})
export class AccueilleComponent {


  logOut() {
throw new Error('Method not implemented.');
}

}*/
import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'app-accueille',
  templateUrl: './accueille.component.html',
})
export class AccueilleComponent {

  constructor(public dialog: MatDialog) { }

  openLoginForm(): void {
    const dialogRef = this.dialog.open(LoginComponent, {
      width: '400px'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('Le dialogue est fermé');
    });
  }
}

