import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import { MemberTableComponent } from './components/member-table/member-table.component';
import {NgbdSortableHeader} from './directives/sortable.directive';
import { EditMemberComponent } from './components/edit-member/edit-member.component';
import { VirtualScrollerModule } from 'ngx-virtual-scroller';
import {VirtualScrollModule} from 'od-virtualscroll';
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
  }
];

import {AppRoutingModule} from "../app-routing.module";

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
    TeamspeakComponent
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
    AppRoutingModule,
    VirtualScrollerModule,
    VirtualScrollModule,
    NoopAnimationsModule,
    ScrollingModule,
    RouterModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [HomeComponent, MemberTableComponent, EditMemberComponent]
})
export class AppModule { }
