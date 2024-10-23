import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {TabComponent} from './tab/tab.component';
import {LoginComponent} from './login/login.component';
import {ListOrdersComponent} from './list-orders/list-orders.component';

const routes: Routes = [
  { path: 'home', component: TabComponent },
  { path: 'login', component: LoginComponent },
  { path: 'orders', component: ListOrdersComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
