import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {finalize, map} from "rxjs/operators";
import {User} from "../model/user";

@Injectable({
  providedIn: 'root'
})

export class AuthenticationService {
  private backendUrl = 'http://localhost:8080';
  authenticated = false;
  constructor(private http: HttpClient) {

  }
  authenticate(credentials, callback) {
    const authString = 'Basic ' + window.btoa(credentials.username + ':' + credentials.password)
    const headers = new HttpHeaders(credentials ? {
      authorization: authString
    } : {});
    console.log(this);
    this.http.get(this.backendUrl+'/user', {headers: headers}).subscribe(response => {
      console.log(response['name']);
      if (response['name']) {
        this.authenticated = true;
        sessionStorage.setItem('username', credentials.username);
        sessionStorage.setItem('basicauth',authString)
        console.log(this.authenticated);

      } else {
        this.authenticated = false;
        console.log(this.authenticated);
      }
      return callback && callback();
    });

  }

  logout() {
    this.http.post(this.backendUrl+'/logout', {}).pipe(finalize(() => {
      this.authenticated = false;
      sessionStorage.removeItem('username')
      //this.router.navigateByUrl('/login');
    })).subscribe();
  }

  isUserLogginId(){
    let user = sessionStorage.getItem('username')
    return !(user === null)
  }


}
