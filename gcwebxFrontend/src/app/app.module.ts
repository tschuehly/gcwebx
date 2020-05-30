import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
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

@NgModule({
  declarations: [
    AppComponent,
    MemberTableComponent, NgbdSortableHeader, EditMemberComponent
  ],
  exports: [MemberTableComponent],
  imports: [
    BrowserModule,
    NgbModule,
    FormsModule,
    ReactiveFormsModule,
    CommonModule,
    NgbModule,
    HttpClientModule,
    VirtualScrollerModule,
    VirtualScrollModule,
    NoopAnimationsModule,
    ScrollingModule
  ],
  providers: [],
  bootstrap: [AppComponent, MemberTableComponent ],
  entryComponents:[EditMemberComponent]
})
export class AppModule { }
