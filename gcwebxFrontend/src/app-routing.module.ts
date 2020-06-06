import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginFormComponent} from "./app/login-form/login-form.component";
import {MemberTableComponent} from "./app/member-table/member-table.component";
import {RegistrationFormComponent} from "./app/registration-form/registration-form.component";




const routes: Routes = [


  //{path: '' , redirectTo: '/login', pathMatch: 'full'},
  {path: 'getMembers', component: MemberTableComponent},
  {path: 'login', component: LoginFormComponent},
  {path: 'register', component: RegistrationFormComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [];
