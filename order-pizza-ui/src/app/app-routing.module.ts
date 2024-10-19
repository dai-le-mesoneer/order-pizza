import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {TabComponent} from './tab/tab.component';

const routes: Routes = [
  { path: 'home', component: TabComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
