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
import { AuthGuard } from './shared/guards/auth.guard';
import { GestionEntrepriseComponent } from './views/home/gestion-entreprise/gestion-entreprise.component';

import { DetailsComponent } from './views/details/details.component';
import { SignupComponent } from './components/signup/signup.component';
import { AboutUsComponent } from './views/about-us/about-us.component';
import { GestionFormateursExterneComponent } from './views/home/gestion-formateurs-externe/gestion-formateurs-externe.component';
const routes: Routes = [
  { path: 'home', component: HomeComponent ,canActivate:[AuthGuard],data:{roles:['ROLE_ADMIN','ROLE_FORMATEUR','ROLE_ASSISTANT']},children:[
    {path: 'planification', component: PlanificationComponent ,canActivate:[AuthGuard],data:{roles:['ROLE_ADMIN','ROLE_FORMATEUR','ROLE_ASSISTANT']}},
    {path: 'formateurs', component: GestionFormatuersComponent,canActivate:[AuthGuard],data:{roles:['ROLE_ADMIN','ROLE_FORMATEUR','ROLE_ASSISTANT']}},
    {path: 'formations', component: GestionFormationsComponent,canActivate:[AuthGuard],data:{roles:['ROLE_ADMIN','ROLE_FORMATEUR','ROLE_ASSISTANT']}},
    {path: 'assistants', component: GestionAssistantsComponent,canActivate:[AuthGuard],data:{roles:['ROLE_ADMIN','ROLE_FORMATEUR','ROLE_ASSISTANT']}},
    {path: 'entreprises', component: GestionEntrepriseComponent,canActivate:[AuthGuard],data:{roles:['ROLE_ADMIN','ROLE_FORMATEUR','ROLE_ASSISTANT']}},
    { path: 'formateurs externes', component: GestionFormateursExterneComponent, canActivate: [AuthGuard], data: { roles: ['ROLE_ADMIN', 'ROLE_FORMATEUR', 'ROLE_ASSISTANT'] } },
    { path: '', pathMatch: 'full', redirectTo: 'planification' },
    
  ]},
  { path: 'accueille', component: AccueilleComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'login', component: LoginComponent},
  { path: 'detail/:id', component: DetailsComponent },
  {path: 'aboutUs',component:AboutUsComponent},
  {path:'inscrire/:id',component:SignupComponent},
  { path: '**', redirectTo: 'accueille'},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
