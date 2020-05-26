import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class BackendService {
  private backendUrl = 'http://localhost:8080';
  constructor(
    private http: HttpClient
  ) { }
  getMemberTable(): Observable<any>{
    return this.http.get(this.backendUrl + '/getMemberTable');
  }
}
