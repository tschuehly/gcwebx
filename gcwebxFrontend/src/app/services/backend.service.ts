import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Member} from '../model/member';
import {User} from '../model/user';
import {Content} from '../model/content';

@Injectable({
  providedIn: 'root'
})

export class BackendService {
  private backendUrl = '/api';
  constructor(
    private http: HttpClient
  ) { }
  getTeams(): Observable<any>{
    return this.http.get(this.backendUrl + '/getTeams');
  }

  getMemberTable(): Observable<any>{
    return this.http.get(this.backendUrl + '/getMembers');
  }
  updateMember(member: Member): Observable<any>{
    return this.http.put(this.backendUrl + '/updateMember', member);
  }
  createMember(member: Member): Observable<any>{
    return this.http.post(this.backendUrl + '/createMember', member);
  }

  getContentById(id: number): Observable<any>{
    return this.http.post(this.backendUrl + '/getContentByID', id);
  }

  getContent(): Observable<any>{
    return this.http.get(this.backendUrl + '/getContent');
  }
  updateContent(content: Content): Observable<any>{
    return this.http.put(this.backendUrl + '/updateContent', content);
  }
  createContent(content: Content): Observable<any>{
    return this.http.post(this.backendUrl + '/createContent', content);
  }

  deleteContent(id: number): Observable<any>{
    return this.http.delete(`${this.backendUrl + '/deleteContent/'}${id}`);
  }
  getUserTable(): Observable<any>{
    return this.http.get(this.backendUrl + '/getUsers');
  }
  updateUser(user: User): Observable<any>{
    return this.http.put(this.backendUrl + '/updateUser', user);
  }
  createUser(user: User): Observable<any>{
    return this.http.post(this.backendUrl + '/createUser', user);
  }
  deleteUser(user: User): Observable<any>{
    return this.http.post(this.backendUrl + '/deleteUser', user );
  }
}
