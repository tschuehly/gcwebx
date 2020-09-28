import {Component, OnChanges, OnInit, SimpleChanges, HostListener, Inject, Input} from '@angular/core';
import {Routes} from '@angular/router';
import {MemberTableComponent} from '../member-table/member-table.component';
import {HomeComponent} from '../home/home.component';
import {Router} from '@angular/router';
import {NgbCarouselConfig} from '@ng-bootstrap/ng-bootstrap';
import {UserService} from '../../services/user.service';
import {Observable} from 'rxjs';
import {AuthenticationService} from '../../services/authentication.service';
import {Roles} from '../../model/roles';
import {DOCUMENT} from '@angular/common';
declare const window: any;

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})



export class NavigationComponent implements OnInit {
  public isMenuCollapsed = true;
  public currentRoles: Roles;
  public currentRoute: string;
  public showTsToast = false;
public navbarCollapsed = true;
  constructor(private router: Router, public authenticationService: AuthenticationService) {
    this.authenticationService.currentRoles$.subscribe( value => {
      this.currentRoles = value;
    });
    router.events.subscribe(val => {
      this.currentRoute = location.pathname;
    });

  }
  date: Date;

  ngOnInit(): void {
    this.authenticationService.getRole();
    this.date = new Date();
  }

  logout(){
    this.authenticationService.logout();
    this.router.navigateByUrl('/home');
    this.authenticationService.getRole();
  }
  connectToTeamspeack(){
    this.showTsToast = true;
    setTimeout(() => window.open('ts3server://xperience'), 1000);
    setTimeout(() => this.showTsToast = false, 8000);
  }
}
