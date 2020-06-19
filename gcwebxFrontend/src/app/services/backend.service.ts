import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Member} from '../model/member';
import {Content} from "../model/content";

@Injectable({
  providedIn: 'root'
})

export class BackendService {
  private backendUrl = '/api';
  constructor(
    private http: HttpClient
  ) { }
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
    return this.http.post(this.backendUrl + '/getContent', id);
  }
  updateContent(content: Content): Observable<any>{
    return this.http.put(this.backendUrl + '/updateContent', content);
  }
  createContent(content: Content): Observable<any>{
    return this.http.post(this.backendUrl + '/createContent', content);
  }

  deleteContent(content: Content): Observable<any>{
    return this.http.delete(this.backendUrl + '/deleteContent');
  }
}
