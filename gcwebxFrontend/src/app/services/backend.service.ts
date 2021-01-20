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

  getMembers(): Observable<any>{
    return this.http.get(this.backendUrl + '/member');
  }
  updateMember(member: Member): Observable<any>{
    return this.http.put(this.backendUrl + '/member', member);
  }
  createMember(member: Member): Observable<any>{
    return this.http.post(this.backendUrl + '/member', member);
  }
  deleteMember(member: Member): Observable<any>{
    return this.http.post(this.backendUrl + '/member/delete', member);
  }
  getStreamer(): Observable<any>{
    return this.http.get(this.backendUrl + '/member/getStreamer');
  }
  getYoutuber(): Observable<any>{
    return this.http.get(this.backendUrl + '/member/getYoutuber');
  }


  getContentById(id: number): Observable<any>{
    return this.http.get(`${this.backendUrl + '/content/'}${id}`);
  }

  getContent(): Observable<any>{
    return this.http.get(this.backendUrl + '/content');
  }
  updateContent(content: Content): Observable<any>{
    return this.http.put(this.backendUrl + '/content', content);
  }
  createContent(content: Content): Observable<any>{
    return this.http.post(this.backendUrl + '/content', content);
  }

  deleteContent(id: number): Observable<any>{
    return this.http.delete(`${this.backendUrl + '/content/'}${id}`);
  }


  getUserTable(): Observable<any>{
    return this.http.get(this.backendUrl + '/user');
  }
  updateUser(user: User): Observable<any>{
    return this.http.put(this.backendUrl + '/user', user);
  }
  createUser(user: User): Observable<any>{
    return this.http.post(this.backendUrl + '/user', user);
  }
  deleteUser(user: User): Observable<any>{
    return this.http.post(this.backendUrl + '/user', user );
  }


  getTeams(): Observable<any>{
    return this.http.get(this.backendUrl + '/team');
  }
  updateTeam(team: Team): Observable<any>{
    return this.http.put(this.backendUrl + '/team', team);
  }
  createTeam(team: Team): Observable<any>{
    return this.http.post(this.backendUrl + '/team', team);
  }
  deleteTeam(team: Team): Observable<any>{
    return this.http.post(this.backendUrl + '/team/delete',team);
  }
  addMemberToTeam(member: Member, teamId: number): Observable<any>{
    return this.http.post(this.backendUrl + '/team/addMember/' + teamId, member);
  }
  removeMemberFromTeam(member: Member, teamId: number): Observable<any>{
    return this.http.post(this.backendUrl + '/team/removeMember/' + teamId, member);
  }


  getMatches(): Observable<any>{
    return this.http.get(this.backendUrl + '/match');
  }
  createMatch(match: Match): Observable<any>{
    return this.http.post(this.backendUrl + '/match', match);
  }
  deleteMatch(matchId: number): Observable<any>{
    return this.http.delete(this.backendUrl + '/match/' + matchId);
  }
  updateMatch(editMatch: Match) {
    return this.http.put(this.backendUrl + '/match', editMatch);
  }

}
