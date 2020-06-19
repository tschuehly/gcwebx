
import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class UserService {
  private backendUrl = 'http://localhost:8080/';
  private currentRole;
  constructor(private http: HttpClient) {

  }

  createAccount(user: any): Observable<any> {
    return this.http.post<any>(`${this.backendUrl}login`, user);
  }


  setRole(role){

  }


}

