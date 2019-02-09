import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GoldCardRegisterComponent } from './gold-card-register/gold-card-register.component';
import { LoginComponent , ErrorComponent} from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AuthGuard } from './auth/auth.guard';
import { AppComponent } from './app.component';
import { UserviewComponent } from './userview/userview.component';
import { EligibleDiseasesComponent } from './eligible-diseases/eligible-diseases.component';
import { TreatmentHistoryComponent } from './treatment-history/treatment-history.component';
import { ReloadpageComponent } from './reloadpage/reloadpage.component';
import { HospitalRegisterComponent } from './hospital-register/hospital-register.component';
import { AddMidicineComponent } from './add-midicine/add-midicine.component';
import { GDCardComponent } from './gd-card/gd-card.component';
import { AcceptToUserComponent } from './accept-to-user/accept-to-user.component';
import { DiseaseComponent } from './disease/disease.component';
import { CardComponent } from './card/card.component';
import { PubliczComponent } from './publicz/publicz.component';
import { MedicalSuppliesComponent } from './medical-supplies/medical-supplies.component';

const routes: Routes = [
    { path: '', redirectTo:'/login' , pathMatch: 'full'},
    { path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
    { path: 'login', component: LoginComponent},
    { path: 'EligibleDiseases', component: EligibleDiseasesComponent, canActivate: [AuthGuard]},
    { path: 'Card', component: CardComponent, canActivate: [AuthGuard]},
    { path: 'TreatmentHistory', component: TreatmentHistoryComponent, canActivate: [AuthGuard]},
    { path: 'reload/:page', component: ReloadpageComponent, canActivate: [AuthGuard]},
    { path: 'userview', component: UserviewComponent, canActivate: [AuthGuard]},
//    { path: '**', redirectTo:'/Error'},
    { path: 'goldCardRegister', component: GoldCardRegisterComponent, canActivate: [AuthGuard]},
    { path: 'addMidicine', component: AddMidicineComponent, canActivate: [AuthGuard]},
    { path: 'medicalSupplies', component: MedicalSuppliesComponent, canActivate: [AuthGuard]},
    { path: 'hospitalRegister', component: HospitalRegisterComponent, canActivate: [AuthGuard]},
    { path: 'GDCard', component: GDCardComponent, canActivate: [AuthGuard]},
    { path: 'AcceptToUser', component: AcceptToUserComponent, canActivate: [AuthGuard]},
    { path: 'Disease', component: DiseaseComponent, canActivate: [AuthGuard]},
    { path: 'PubZ', component: PubliczComponent, canActivate: [AuthGuard]},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}


