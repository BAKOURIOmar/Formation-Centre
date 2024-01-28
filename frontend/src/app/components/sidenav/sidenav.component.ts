import { MediaMatcher } from '@angular/cdk/layout';
import { Component } from '@angular/core';
import { UserAuthService } from '../../shared/services/user-auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css']
})
export class SidenavComponent {
logOut() {
  this.userAuthService.clear();
  this.router.navigate(['/login']);
}

  mobileQuery: MediaQueryList;

  menuNav = [
    {name: "Planification", route: "planification", icon: "home"},
    {name: "Formations", route: "formations", icon: "category"},
    {name: "Formateurs", route: "formateurs", icon: "production_quantity_limits"},
    {name: "Assistants", route: "assistants", icon: "production_quantity_limits"},
    {name: "Entreprises", route: "entreprises", icon: "production_quantity_limits"}
  ]

  constructor(media: MediaMatcher, private userAuthService: UserAuthService, private router: Router) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
  }
  public isAddmin(){
    return this.userAuthService.isAdmin();
  }
}
