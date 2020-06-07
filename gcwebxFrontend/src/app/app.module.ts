import { BrowserModule } from '@angular/platform-browser';
import {Injectable, NgModule} from '@angular/core';

import { AppComponent } from './app.component';
import { LoginFormComponent } from './components/login-form/login-form.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {HTTP_INTERCEPTORS, HttpClientModule, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import { MemberTableComponent } from './components/member-table/member-table.component';
import {NgbdSortableHeader} from './directives/sortable.directive';
import { EditMemberComponent } from './components/edit-member/edit-member.component';
import { ScrollingModule } from '@angular/cdk/scrolling';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { NavigationComponent } from './components/navigation/navigation.component';
import {RouterModule, Routes} from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { TeamspeakComponent } from './components/teamspeak/teamspeak.component';
export const routerConfig: Routes = [
  {
    path: 'memberTable',
    component: MemberTableComponent,
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'teamspeak',
    component: TeamspeakComponent
  },
  {path: 'login', component: LoginFormComponent},
  {path: 'register', component: RegistrationFormComponent},
  {path: '', pathMatch: 'full', component: HomeComponent}
];

import { RegistrationFormComponent } from './components/registration-form/registration-form.component';
import {AuthenticationService} from "./services/authentication.service";
import {BasicAuthInterceptService} from "./services/basic-auth-intercept.service";

@Injectable()
export class XhrInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const xhr = req.clone({
      headers: req.headers.set('X-Requested-With', 'XMLHttpRequest')
    });
    return next.handle(xhr);
  }
}

@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    AppComponent,
    MemberTableComponent,
    NgbdSortableHeader,
    EditMemberComponent,
    NavigationComponent,
    HomeComponent,
    TeamspeakComponent, NgbdSortableHeader, RegistrationFormComponent
  ],
  exports: [MemberTableComponent, HomeComponent],
  imports: [
    RouterModule.forRoot(routerConfig),
    BrowserModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    NgbModule,
    HttpClientModule,
    NoopAnimationsModule,
    ScrollingModule,
    RouterModule,
  ],
  providers: [AuthenticationService, { provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true },{ provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptService, multi: true }],
  bootstrap: [AppComponent ],

  entryComponents: [HomeComponent, MemberTableComponent, EditMemberComponent]
})
export class AppModule { }
