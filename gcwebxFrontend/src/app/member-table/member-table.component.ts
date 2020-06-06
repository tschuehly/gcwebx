import {Component, OnInit, PipeTransform, QueryList, ViewChildren} from '@angular/core';
import {BackendService} from '../services/backend.service';
import {Member} from '../model/member';
import {DecimalPipe, KeyValue} from '@angular/common';
import {Observable} from 'rxjs';
import {debounceTime, map, startWith} from 'rxjs/operators';
import {FormControl} from '@angular/forms';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {AuthenticationService} from "../services/authentication.service";
import { finalize } from 'rxjs/operators'

let memberTable: Member[] = [];


function search(text: string, pipe: PipeTransform): Member[] {
  return memberTable.filter(member => {
    const term = text.toLowerCase();
    return member.name.toLowerCase().includes(term);
  });
}
@Component({
  selector: 'app-member-table',
  templateUrl: './member-table.component.html',
  styleUrls: ['./member-table.component.css'],
  providers: [ DecimalPipe ]
})
export class MemberTableComponent implements OnInit{
  members$: Observable<Member[]>;
  member: Array<string>;
  filter = new FormControl('');

  constructor(private backendService: BackendService, pipe: DecimalPipe, private http: HttpClient, private router: Router, private authenticationService: AuthenticationService) {



    this.backendService.getMemberTable().
    subscribe(memberTableResponse => {
      memberTable = memberTableResponse;
      console.log(memberTable);
      this.member = Object.keys(memberTable[0]);
      console.log(this.member);
    });

    this.members$ = this.filter.valueChanges.pipe(
      startWith('test'),
      map(text => search(text, pipe)) // TODO: entries only start showing after typing

    );
    console.log(this.filter);
  }

  ngOnInit(): void {
    }

  authenticated() { return this.authenticationService.authenticated;}


  originalOrder = (a: KeyValue<number, string>, b: KeyValue<number, string>): number => {
    return 0;
  }

  logout() {
      this.authenticationService.logout();
  }

}
