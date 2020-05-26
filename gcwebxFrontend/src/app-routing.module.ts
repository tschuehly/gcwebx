import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginFormComponent} from "./app/login-form/login-form.component";
import {MemberTableComponent} from "./app/member-table/member-table.component";




const routes: Routes = [


  //{path: '' , redirectTo: '/login', pathMatch: 'full'},
  {path: 'getMembers', component: MemberTableComponent},
  {path: 'createAccount', component: LoginFormComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [];
