import { Component, OnInit } from '@angular/core';
import {Routes} from '@angular/router';
import {MemberTableComponent} from '../member-table/member-table.component';
import {HomeComponent} from '../home/home.component';
import {Router} from '@angular/router';
import {NgbCarouselConfig} from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})

export class NavigationComponent implements OnInit {
  public isNavbarCollapsed = true;
  constructor(private router: Router) {


  }

  ngOnInit(): void {
  }

}
