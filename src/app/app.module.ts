import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatInputModule} from '@angular/material/input';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MDBBootstrapModule} from 'angular-bootstrap-md';
import {CablesComponent} from "./cables/cables.component";
import {CableDetailComponent} from './cables/cable-detail/cable-detail.component';
import {CableEditComponent} from './cables/cable-edit/cable-edit.component';
import {CableListComponent} from './cables/cable-list/cable-list.component';
import {ShoppingListComponent} from './shopping-list/shopping-list.component';
import {HttpService} from "./shared/http.service";
import {CableService} from "./cables/cable.service";
import {HttpClientModule} from '@angular/common/http';
import {MatSortModule} from "@angular/material/sort";
import {
  NgxMatDatetimePickerModule,
  NgxMatNativeDateModule,
  NgxMatTimepickerModule
} from "@angular-material-components/datetime-picker";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatSelectModule} from "@angular/material/select";
import {MatDatepickerModule} from "@angular/material/datepicker";
import { UploadImagesComponent } from './upload-images/upload-images.component';
import {MatCardModule} from "@angular/material/card";
import {MatListModule} from "@angular/material/list";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatIconModule} from "@angular/material/icon";
import { DialogAnimationsComponent} from "./dialog-animations/dialog-animations.component";
import { NotificationComponent } from './notification/notification.component';
import {MatSnackBar, MatSnackBarModule} from "@angular/material/snack-bar";

@NgModule({
  declarations: [
    AppComponent,
    CableDetailComponent,
    CableEditComponent,
    CableListComponent,
    CablesComponent,
    ShoppingListComponent,
    UploadImagesComponent,
    DialogAnimationsComponent,
    NotificationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatFormFieldModule,
    MatTableModule,
    MatPaginatorModule,
    MatInputModule,
    BrowserAnimationsModule,
    MDBBootstrapModule,
    HttpClientModule,
    MatSortModule,
    NgxMatTimepickerModule,
    NgxMatDatetimePickerModule,
    NgxMatNativeDateModule,
    ReactiveFormsModule,
    MatSelectModule,
    MatDatepickerModule,
    MatCardModule,
    MatListModule,
    MatToolbarModule,
    MatIconModule,
    MatSnackBarModule,
    FormsModule
  ],
  providers: [
    CableService,
    HttpService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
