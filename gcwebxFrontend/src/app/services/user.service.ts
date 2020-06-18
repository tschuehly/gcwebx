
import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})

export class UserService {
  private url: string = 'https://localhost:8080/';

  constructor(private http: HttpClient) {

  }

  createAccount(user: any): Observable<any> {
    return this.http.post<any>(`${this.url}login`, user);
  }

}

