import {BrowserModule} from '@angular/platform-browser';
import {Injectable, NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {LoginFormComponent} from './components/login-form/login-form.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {HTTP_INTERCEPTORS, HttpClientXsrfModule, HttpClientModule, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {MemberTableComponent} from './components/member-table/member-table.component';
import {NgbdSortableHeader} from './directives/sortable.directive';
import { EditMemberComponent } from './components/edit-member/edit-member.component';
import { ScrollingModule } from '@angular/cdk/scrolling';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { NavigationComponent } from './components/navigation/navigation.component';
import {RouterModule, Routes} from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { TeamspeakComponent } from './components/teamspeak/teamspeak.component';
import {EditorModule} from 'primeng/editor';
import {XhrInterceptor} from './services/interceptor.service';
import {BasicAuthIntercept} from './services/interceptor.service';
import {HttpXsrfInterceptor} from './services/interceptor.service';

export const routerConfig: Routes = [
  {path: 'memberTable', component: MemberTableComponent},
  {path: 'home', component: HomeComponent},
  {path: 'teamspeak', component: TeamspeakComponent},
  {path: 'login', component: LoginFormComponent},
  {path: 'edit', component: EditorComponent},
  {path: '', pathMatch: 'full', component: HomeComponent},
  {path : 'userTable', pathMatch: 'full', component: UserTableComponent}
];

import {AuthenticationService} from './services/authentication.service';
import { EditorComponent } from './components/editor/editor.component';
import {BasicAuthInterceptService} from './services/basic-auth-intercept.service';
import { UserTableComponent } from './components/user-table/user-table.component';
import { EditUserComponent } from './components/edit-user/edit-user.component';
import {User} from './model/user';



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
    TeamspeakComponent,
    NgbdSortableHeader,
    UserTableComponent,
    EditUserComponent,
    EditorComponent
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
    EditorModule,
    HttpClientXsrfModule.withOptions({
      cookieName: 'XSRF-TOKEN',
      headerName: 'X-XSRF-TOKEN'
    })
  ],
  providers: [AuthenticationService,
    { provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: BasicAuthIntercept, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: HttpXsrfInterceptor, multi: true }
  ],

  bootstrap: [AppComponent ],

  entryComponents: [HomeComponent, MemberTableComponent, EditMemberComponent, NavigationComponent]
})
export class AppModule { }
