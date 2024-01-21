import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './views/home/home.component';
import { LoginComponent } from './views/login/login.component';
import { RegisterComponent } from './views/register/register.component';
import { AccueilleComponent } from './views/accueille/accueille.component';
import { PlanificationsComponent } from './views/home/planifications/planifications.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent,children:[
    {path: 'planifications', component: PlanificationsComponent},
    { path: '', pathMatch: 'full', redirectTo: 'planifications' },
  ]},
  { path: 'accueille', component: AccueilleComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'login', component: LoginComponent},
  { path: '**', redirectTo: 'accueille'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
