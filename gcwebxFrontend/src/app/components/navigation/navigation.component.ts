import {Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {Routes} from '@angular/router';
import {MemberTableComponent} from '../member-table/member-table.component';
import {HomeComponent} from '../home/home.component';
import {Router} from '@angular/router';
import {NgbCarouselConfig} from '@ng-bootstrap/ng-bootstrap';
import {UserService} from '../../services/user.service';
import {Observable} from 'rxjs';
import {AuthenticationService} from '../../services/authentication.service';
import {Roles} from '../../model/roles';


@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})

export class NavigationComponent implements OnInit {
  public isMenuCollapsed = true;
  public currentRoles: Roles;
  constructor(private router: Router, public authenticationService: AuthenticationService) {
    this.authenticationService.currentRoles$.subscribe( value => {
      this.currentRoles = value;
    });

  }

  ngOnInit(): void {
    this.authenticationService.getRole();
  }

  logout(){
    this.authenticationService.logout();
    this.router.navigateByUrl('/home');
    this.authenticationService.getRole();
  }
}
