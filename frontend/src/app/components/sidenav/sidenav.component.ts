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
  this.router.navigate(['/accueille']);
}

  mobileQuery: MediaQueryList;

  menuNav = [
    {name: "Planification", route: "planification", icon: "assets/img/planification.png"},
    {name: "Formations", route: "formations", icon: "assets/img/formation.png"},
    {name: "Formateurs", route: "formateurs", icon: "assets/img/formateur.png"},
    {name: "Assistants", route: "assistants", icon: "assets/img/assistant.png"},
    {name: "Entreprises", route: "entreprises", icon: "assets/img/entreprise.png"},
    {name: "Formateurs Externes", route: "formateurs externes", icon: "assets/img/formateur.png" },
  ]

  constructor(media: MediaMatcher, private userAuthService: UserAuthService, private router: Router) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
  }
  public isAddmin(){
    return this.userAuthService.isAdmin();
  }
  public isAssistant(){
    return this.userAuthService.isAssistant();
  }
  public isFormateur(){
    return this.userAuthService.isFormat();
  }
}
