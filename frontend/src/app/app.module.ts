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
import { PlanificationsComponent } from './views/home/planifications/planifications.component';
import { CardformationComponent } from './components/cardformation/cardformation.component';
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
    PlanificationsComponent,
    

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatToolbarModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    AgrupaMaterialModule,
    HomeRoutingModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
