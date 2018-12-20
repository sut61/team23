import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GoldCardRegisterComponent } from './gold-card-register/gold-card-register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { AuthGuard } from './auth/auth.guard';
import { AppComponent } from './app.component';

const routes: Routes = [
 //   { path: '', component: HomeComponent},
    { path: '', component: LoginComponent},
    { path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
    { path: 'login', component: LoginComponent},
 //   { path: '**', redirectTo: ''},
    { path: 'goldcardregister', component: GoldCardRegisterComponent, canActivate: [AuthGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}


