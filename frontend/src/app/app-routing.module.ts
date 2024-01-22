import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AccueilleComponent } from './views/accueille/accueille.component';
import { GestionAssistantsComponent } from './views/home/gestion-assistants/gestion-assistants.component';
import { GestionFormationsComponent } from './views/home/gestion-formations/gestion-formations.component';
import { GestionFormatuersComponent } from './views/home/gestion-formatuers/gestion-formatuers.component';
import { HomeComponent } from './views/home/home.component';
import { LoginComponent } from './views/login/login.component';
import { RegisterComponent } from './views/register/register.component';
import { PlanificationComponent } from './views/home/planification/planification.component';


const routes: Routes = [
  { path: 'home', component: HomeComponent,children:[
    {path: 'planification', component: PlanificationComponent},
    {path: 'formateurs', component: GestionFormatuersComponent},
    {path: 'formations', component: GestionFormationsComponent},
    {path: 'assistants', component: GestionAssistantsComponent},
    { path: '', pathMatch: 'full', redirectTo: 'planification' },
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
