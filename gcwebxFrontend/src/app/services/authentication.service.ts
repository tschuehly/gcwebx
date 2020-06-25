import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {finalize, map} from 'rxjs/operators';
import {User} from '../model/user';
import {UserService} from './user.service';
import {Roles} from '../model/roles';

@Injectable({
  providedIn: 'root'
})

export class AuthenticationService {
  private backendUrl = 'http://localhost:8080';
  public currentRole = [];
  public currentRoles: Roles;
  authenticated = false;
  constructor(private http: HttpClient, private userService: UserService) {

  }
  authenticate(credentials, callback) {
    const authString = 'Basic ' + window.btoa(credentials.username + ':' + credentials.password);
    const headers = new HttpHeaders(credentials ? {
      authorization: authString
    } : {});
    console.log(this);
    this.http.get(this.backendUrl + '/user', {headers}).subscribe(response => {

      console.log(response['name']);
      console.log(response);
      if (response['name']) {
        this.authenticated = true;
        sessionStorage.setItem('username', credentials.username);
        sessionStorage.setItem('basicauth', authString);
        this.currentRole = response['principal']['roles'];
        console.log(this.authenticated);

      } else {
        this.authenticated = false;
        console.log(this.authenticated);
      }
      return callback && callback();
    });

  }
  public getRole(): any {
    const username = sessionStorage.getItem('username');
    if (username){
      this.http.get(this.backendUrl + '/getRole/' + username ).subscribe(response => {
        console.log(response);
        this.currentRoles = response['roles'];
        console.log(this.currentRoles);
        console.log(this.currentRoles.roleAdmin);
        return this.currentRole;
      });
    }else{
      return null;
    }

  }

    logout()
    {
      this.currentRole = null;
      this.authenticated = false;
      sessionStorage.removeItem('username');
      sessionStorage.removeItem('basicauth');
      // this.router.navigateByUrl('/login');
    }

  }

