import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {TabComponent} from './components/tab/tab.component';
import {LoginComponent} from './components/login/login.component';
import {ListOrdersComponent} from './components/list-orders/list-orders.component';
import {PagenotfoundComponent} from './components/pagenotfound/pagenotfound.component';
import {AccessdeniedComponent} from './components/accessdenied/accessdenied.component';

const routes: Routes = [
  { path: 'home', component: TabComponent },
  { path: 'login', component: LoginComponent },
  { path: 'orders', component: ListOrdersComponent },
  { path: 'access-denied', component: AccessdeniedComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: '**', pathMatch: 'full', component: PagenotfoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
