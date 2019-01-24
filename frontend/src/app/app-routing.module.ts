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

const routes: Routes = [
    { path: '', redirectTo:'/login' , pathMatch: 'full'},
    { path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
    { path: 'login', component: LoginComponent},
    { path: 'EligibleDiseases', component: EligibleDiseasesComponent, canActivate: [AuthGuard]},
    { path: 'TreatmentHistory', component: TreatmentHistoryComponent, canActivate: [AuthGuard]},
    { path: 'reload/:page', component: ReloadpageComponent, canActivate: [AuthGuard]},
    { path: 'userview', component: UserviewComponent, canActivate: [AuthGuard]},
//    { path: '**', redirectTo:'/Error'},
    { path: 'goldCardRegister', component: GoldCardRegisterComponent, canActivate: [AuthGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}


