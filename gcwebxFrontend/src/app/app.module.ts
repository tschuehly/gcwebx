import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import { MemberTableComponent } from './member-table/member-table.component';
import {NgbdSortableHeader} from './directives/sortable.directive';


@NgModule({
  declarations: [
    AppComponent,
    MemberTableComponent, NgbdSortableHeader
  ],
  exports: [MemberTableComponent],
  imports: [
    BrowserModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    NgbModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent, MemberTableComponent ]
})
export class AppModule { }
