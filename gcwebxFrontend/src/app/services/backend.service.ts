import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Member} from '../model/member';
import {User} from '../model/user';
import {Content} from '../model/content';
import {Team} from '../model/team';
import {Match} from '../model/match';

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

  getTeams(): Observable<any>{
    return this.http.get(this.backendUrl + '/getTeams');
  }
  updateTeam(team: Team): Observable<any>{
    return this.http.put(this.backendUrl + '/updateTeam', team);
  }
  createTeam(team: Team): Observable<any>{
    return this.http.post(this.backendUrl + '/createTeam', team);
  }
  deleteTeam(team: Team): Observable<any>{
    return this.http.post(this.backendUrl + '/deleteTeam', team);
  }
  addMemberToTeam(member: Member, teamId: number): Observable<any>{
    return this.http.post(this.backendUrl + '/addMemberToTeam/' + teamId, member);
  }
  getMatches(): Observable<any>{
    return this.http.get(this.backendUrl + '/getMatches');
  }
  createMatch(match: Match): Observable<any>{
    return this.http.post(this.backendUrl + '/createMatch', match);
  }
  deleteMatch(matchId: number): Observable<any>{
    return this.http.delete(this.backendUrl + '/deleteMatch/' + matchId);
  }

  updateMatch(editMatch: Match) {
    return this.http.put(this.backendUrl + '/updateMatch', editMatch);
  }
  getStreamer(): Observable<any>{
    return this.http.get(this.backendUrl + '/getStreamer');
  }
}
