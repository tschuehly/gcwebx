import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {BehaviorSubject, Observable, of} from 'rxjs';
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
  public currentRoles$: BehaviorSubject<Roles>;
  public isAdmin$: BehaviorSubject<boolean>;
  authenticated = false;
  constructor(private http: HttpClient, private userService: UserService) {
    this.currentRoles$ = new BehaviorSubject<Roles>(null);

  }
  authenticate(credentials, callback) {
    const authString = 'Basic ' + window.btoa(credentials.username + ':' + credentials.password);
    console.log(authString);
    const headers = new HttpHeaders(credentials ? {
      authorization: authString
    } : {});
    this.http.get(this.backendUrl + '/user', {headers}).subscribe(response => {

      if (response['name']) {
        this.authenticated = true;
        sessionStorage.setItem('username', credentials.username);
        sessionStorage.setItem('basicauth', authString);
        this.currentRoles = response['principal']['roles'];
        this.currentRoles$.next(this.currentRoles);
      } else {
        this.authenticated = false;
      }
      return callback && callback();
    });

  }
  public getRoleObs(): Observable<Roles>{
    return this.currentRoles$.asObservable();
  }
  public getRole(){
    const username = sessionStorage.getItem('username');
    if (username){
      this.http.get(this.backendUrl + '/getRole/' + username ).subscribe(response => {
        this.currentRoles = response['roles'];
        this.currentRoles$.next(this.currentRoles);
        console.log(this.currentRoles);
      });
    }
  }

    logout()
    {
      this.currentRoles = null;
      this.authenticated = false;
      this.currentRoles$.next(this.currentRoles);
      sessionStorage.removeItem('username');
      sessionStorage.removeItem('basicauth');
      // this.router.navigateByUrl('/login');
    }

  }

