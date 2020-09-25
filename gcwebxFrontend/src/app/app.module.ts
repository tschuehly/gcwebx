import {BrowserModule} from '@angular/platform-browser';
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
import {XhrInterceptor} from './services/interceptor.service';
import {BasicAuthIntercept} from './services/interceptor.service';
import {HttpXsrfInterceptor} from './services/interceptor.service';
import {AuthenticationService} from './services/authentication.service';
import { EditorComponent } from './components/editor/editor.component';
import { UserTableComponent } from './components/user-table/user-table.component';
import { EditUserComponent } from './components/edit-user/edit-user.component';
import {EsportComponent} from './components/esport/esport.component';
import { NewsComponent } from './components/news/news.component';
import {FirstTwentyWords} from './services/first-twenty-words.pipe';
import {DateFormat} from './services/date-format.pipe';
import {NgModule} from '@angular/core';
import {SafeHtml} from './services/safe-Html.pipe';
import { EditTeamComponent } from './components/edit-team/edit-team.component';
import {NgxWigModule} from 'ngx-wig';
import { StaticComponent } from './components/static/static.component';
import { EditMatchComponent } from './components/edit-match/edit-match.component';
import {AuthGuard} from './services/auth.guard';

export const routerConfig: Routes = [
  {path: 'memberTable', component: MemberTableComponent},
  {path: 'home', component: HomeComponent},
  {path: 'teamspeak', component: TeamspeakComponent},
  {path: 'login', component: LoginFormComponent},
  {path: 'edit', component: EditorComponent, canActivate: [AuthGuard]},
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path : 'userTable', component: UserTableComponent, canActivate: [AuthGuard]},
  {path : 'esport/:game',  component: EsportComponent},
  {path : 'news', component: NewsComponent},
  {path : 'imprint', component: StaticComponent},
  {path : 'logo', component: StaticComponent}
];

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
    EditorComponent,
    NewsComponent,
    FirstTwentyWords,
    DateFormat,
    SafeHtml,
    EsportComponent,
    EditTeamComponent,
    StaticComponent,
    EditMatchComponent
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
    NgxWigModule,
    HttpClientXsrfModule.withOptions({
      cookieName: 'XSRF-TOKEN',
      headerName: 'X-XSRF-TOKEN'
    })
  ],
  providers: [AuthenticationService, AuthGuard,
    { provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: BasicAuthIntercept, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: HttpXsrfInterceptor, multi: true }
  ],

  bootstrap: [AppComponent ],

  entryComponents: [HomeComponent, MemberTableComponent, EditMemberComponent, NavigationComponent]
})
export class AppModule { }
