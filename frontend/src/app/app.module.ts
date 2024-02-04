import { Component } from '@angular/core';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './views/home/home.component';
import { LoginComponent } from './views/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AgrupaMaterialModule } from './material/agrupa-material.module';
import { SidenavComponent } from './components/sidenav/sidenav.component';
import { AccueilleComponent } from './views/accueille/accueille.component';
import { RegisterComponent } from './views/register/register.component';
import { HomeRoutingModule } from './views/home/home-routing.module';
import { FullCalendarModule } from '@fullcalendar/angular';
import { GestionFormatuersComponent } from './views/home/gestion-formatuers/gestion-formatuers.component';
import { GestionAssistantsComponent } from './views/home/gestion-assistants/gestion-assistants.component';
import { GestionFormationsComponent } from './views/home/gestion-formations/gestion-formations.component';
import { PlanificationComponent } from './views/home/planification/planification.component';
import { CardformationComponent } from './components/cardformation/cardformation.component';
import { AuthGuard } from './shared/guards/auth.guard';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from './shared/auth.interceptor';
import { UserAuthService } from './shared/services/user-auth.service';
import { NewFormateurComponent } from './components/new-formateur/new-formateur.component';
import { ConfirmationComponent } from './components/confirmation/confirmation.component';
import { NewFormationComponent } from './components/new-formation/new-formation.component';
import { NewAssistantComponent } from './components/new-assistant/new-assistant.component';
import { GestionEntrepriseComponent } from './views/home/gestion-entreprise/gestion-entreprise.component';
import { NewEntrepriseComponent } from './components/new-entreprise/new-entreprise.component';
import { SignupComponent } from './components/signup/signup.component';

import { DetailsComponent } from './views/details/details.component';
import { MatIconModule } from '@angular/material/icon';
import { CommonModule, DatePipe } from '@angular/common';
import { AboutUsComponent } from './views/about-us/about-us.component';
import { GestionFormateursExterneComponent } from './views/home/gestion-formateurs-externe/gestion-formateurs-externe.component';

import { FeedbackComponent } from './views/feedback/feedback.component';
import { StarRatingComponent } from './components/star-rating/star-rating.component';
import { NewPlanificationComponent } from './components/new-planification/new-planification.component';
@NgModule({

  declarations: [
    AppComponent,
    HeaderComponent,
    HomeComponent,
    LoginComponent,
    CardformationComponent,
    SidenavComponent,
    AccueilleComponent,
    RegisterComponent,
    SignupComponent,
    GestionFormatuersComponent,
    GestionAssistantsComponent,
    GestionFormationsComponent,
    PlanificationComponent,
    NewFormateurComponent,
    ConfirmationComponent,
    NewFormationComponent,
    NewAssistantComponent,
    GestionEntrepriseComponent,
    NewEntrepriseComponent,
    DetailsComponent,
    AboutUsComponent,
    GestionFormateursExterneComponent,
    FeedbackComponent,
    StarRatingComponent,
    NewPlanificationComponent,

  ],
  imports: [
    BrowserModule,
    CommonModule,
    AppRoutingModule,
    MatToolbarModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    AgrupaMaterialModule,
    HomeRoutingModule,
    FullCalendarModule,
    MatIconModule,

  ],
  providers: [DatePipe ,
    AuthGuard,

    {
    provide:HTTP_INTERCEPTORS,
    useClass:AuthInterceptor,
    multi:true
  },UserAuthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
