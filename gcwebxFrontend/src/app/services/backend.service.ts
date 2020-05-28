import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Member} from '../model/member';

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
  updateMember(member: Member): Observable<any>{
    return this.http.put(this.backendUrl + '/updateMember', member);
  }
}
