import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import {CablesComponent} from "./cables/cables.component";
import {CableDetailComponent} from "./cables/cable-detail/cable-detail.component";
import {CableEditComponent} from "./cables/cable-edit/cable-edit.component";
import {ShoppingListComponent} from "./shopping-list/shopping-list.component";
import {CableListComponent} from "./cables/cable-list/cable-list.component";

const routes: Routes = [
  {path: '', redirectTo: '/cables', pathMatch: 'full'},
  { path: 'cables', component: CablesComponent, children: [
      {path: '', component: CableListComponent},
      {path: 'new', component: CableEditComponent},
      {path: ':id', component: CableDetailComponent},
      {path: ':id/edit', component: CableEditComponent},
    ]},
  {path: 'shopping-list', component: ShoppingListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
