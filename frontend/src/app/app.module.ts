import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { GoldCardRegisterComponent } from './gold-card-register/gold-card-register.component';
import { LoginComponent ,ErrorComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule} from '@angular/forms';
import { HeaderComponent } from './header/header.component';
import { AppMaterialModule } from './app-material/app-material.module';
import { HomeComponent } from './home/home.component';
import { AuthGuard } from './auth/auth.guard';
import { AuthService } from './auth/auth.service';
import { HospitalRegisterComponent } from './hospital-register/hospital-register.component';
import { GDCardComponent } from './gd-card/gd-card.component';
import { PubliczComponent } from './publicz/publicz.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {
  MatAutocompleteModule,
  MatBadgeModule,
  MatBottomSheetModule,
  MatButtonModule,
  MatButtonToggleModule,
  MatCardModule,
  MatCheckboxModule,
  MatChipsModule,
  MatDatepickerModule,
  MatDialogModule,
  MatDividerModule,
  MatExpansionModule,
  MatGridListModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatMenuModule,
  MatNativeDateModule,
  MatPaginatorModule,
  MatProgressBarModule,
  MatProgressSpinnerModule,
  MatRadioModule,
  MatRippleModule,
  MatSelectModule,
  MatSidenavModule,
  MatSliderModule,
  MatSlideToggleModule,
  MatSnackBarModule,
  MatSortModule,
  MatStepperModule,
  MatTableModule,
  MatTabsModule,
  MatToolbarModule,
  MatTooltipModule,
  MatTreeModule,

} from '@angular/material';
import { SheetComponent,Opensheet } from './sheet/sheet.component';
import { GoldcardService } from './goldcard.service';
import { HttpClientModule } from '@angular/common/http';
import { platformBrowserDynamic} from '@angular/platform-browser-dynamic';
import { AlertComponent } from './alert/alert.component';
import { AlertService } from './alert.service';
import { UserviewComponent } from './userview/userview.component';
import { EligibleDiseasesComponent } from './eligible-diseases/eligible-diseases.component';
import { ReloadpageComponent } from './reloadpage/reloadpage.component';
import { TreatmentHistoryComponent,Thpass } from './treatment-history/treatment-history.component';
import { AddMidicineComponent } from './add-midicine/add-midicine.component';
import { EditMidicineComponent } from './edit-midicine/edit-midicine.component';
import { AcceptToUserComponent,Sheet,Sheetq } from './accept-to-user/accept-to-user.component';
import { DiseaseComponent,Success } from './disease/disease.component';
import { CardComponent } from './card/card.component';
import { MedicalSuppliesComponent } from './medical-supplies/medical-supplies.component';
import { EditMedicalSuppliesComponent } from './edit-medical-supplies/edit-medical-supplies.component';
import { AddTrainingComponent } from './add-training/add-training.component';

@NgModule({
  declarations: [
    AppComponent,
    GoldCardRegisterComponent,
    LoginComponent,
    HeaderComponent,
    HomeComponent,
    SheetComponent,
    Opensheet,
    AlertComponent,
    UserviewComponent,
    EligibleDiseasesComponent,
    ReloadpageComponent,
    TreatmentHistoryComponent,
    HospitalRegisterComponent,
    AddMidicineComponent,
    EditMidicineComponent,
    GDCardComponent,
    AcceptToUserComponent,
    Sheet,
    Sheetq,
    Success,
    Thpass,
    DiseaseComponent,
    CardComponent,
    PubliczComponent,
    MedicalSuppliesComponent,
    EditMedicalSuppliesComponent,
    AddTrainingComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatAutocompleteModule,
        MatBadgeModule,
        MatBottomSheetModule,
        MatButtonModule,
        MatButtonToggleModule,
        MatCardModule,
        MatCheckboxModule,
        MatChipsModule,
        MatStepperModule,
        MatDatepickerModule,
        MatDialogModule,
        MatDividerModule,
        MatExpansionModule,
        MatGridListModule,
        MatIconModule,
        MatInputModule,
        MatListModule,
        MatMenuModule,
        MatNativeDateModule,
        MatPaginatorModule,
        MatProgressBarModule,
        MatProgressSpinnerModule,
        MatRadioModule,
        MatRippleModule,
        MatSelectModule,
        MatSidenavModule,
        MatSliderModule,
        MatSlideToggleModule,
        MatSnackBarModule,
        MatSortModule,
        MatTableModule,
        MatTabsModule,
        MatToolbarModule,
        MatTooltipModule,
        MatTreeModule,
        ReactiveFormsModule,
        MatFormFieldModule,
        HttpClientModule,
            MatNativeDateModule,
            FormsModule
  ],
  exports:[
    MatDialogModule
  ],
  providers: [AuthService, AuthGuard,Opensheet,GoldcardService,AlertService],
  entryComponents: [SheetComponent,Opensheet,EditMidicineComponent,Sheet,Sheetq,Thpass,Success,EditMedicalSuppliesComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }

platformBrowserDynamic().bootstrapModule(AppModule);
