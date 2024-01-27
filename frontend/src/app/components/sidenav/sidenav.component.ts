import { MediaMatcher } from '@angular/cdk/layout';
import { Component } from '@angular/core';
import { UserAuthService } from '../../shared/services/user-auth.service';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css']
})
export class SidenavComponent {
logOut() {
throw new Error('Method not implemented.');
}

  mobileQuery: MediaQueryList;

  menuNav = [
    {name: "planification", route: "planification", icon: "home"},
    {name: "Formations", route: "formations", icon: "category"},
    {name: "Formateurs", route: "formateurs", icon: "production_quantity_limits"},
    {name: "Assistants", route: "assistants", icon: "production_quantity_limits"},
    {name: "Entreprises", route: "entreprises", icon: "production_quantity_limits"}
  ]

  constructor(media: MediaMatcher ,private userAuthService: UserAuthService) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
  }
  public isAddmin(){
    return this.userAuthService.isAdmin();
  }
}
