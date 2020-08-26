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
    // this.active_page = window.location.pathname;
  }

  // ngOnChanges(changes: SimpleChanges) {
  //   this.active_page = window.location.pathname;
  // }

  logout(){
    this.authenticationService.logout();
    this.router.navigateByUrl('/home');
    this.authenticationService.getRole();
  }
  connectToTeamspeack(){
    this.showTsToast = true;
    setTimeout(() => window.open('ts3server://xperience'), 2000);
    setTimeout(() => this.showTsToast = false, 8000);
  }
  /*

  @HostListener("window:scroll",[])
  onWindowScroll(){
    const number = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop || 0;
    if (number > 20) {
      document.querySelectorAll('.navbar').forEach((nav) => {
        nav.classList.remove('bg-transparent');
        nav.classList.add('nav_bg');
      })
    } else if (number < 20) {
      document.querySelectorAll('.navbar').forEach((nav) => {
        nav.classList.add('bg-transparent');
        nav.classList.remove('nav_bg');
      })}
  }*/
}
