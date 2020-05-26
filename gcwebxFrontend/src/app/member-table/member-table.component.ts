import { Component, OnInit } from '@angular/core';
import {BackendService} from '../backend.service';

@Component({
  selector: 'app-member-table',
  templateUrl: './member-table.component.html',
  styleUrls: ['./member-table.component.css']
})
export class MemberTableComponent implements OnInit {

  constructor(private backendService: BackendService) { }
  memberTable: any;
  ngOnInit(): void {
    this.backendService.getMemberTable().
    subscribe(memberTable => this.memberTable = memberTable );

  }

}
