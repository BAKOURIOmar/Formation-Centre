import { MediaMatcher } from '@angular/cdk/layout';
import { Component } from '@angular/core';

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
    {name: "Assistants", route: "assistants", icon: "production_quantity_limits"}
  ]

  constructor(media: MediaMatcher) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
  }
}
