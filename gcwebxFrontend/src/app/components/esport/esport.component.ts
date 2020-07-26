import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {BackendService} from '../../services/backend.service';
import {Team} from '../../model/team';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-esport',
  templateUrl: './esport.component.html',
  styleUrls: ['./esport.component.css']
})
export class EsportComponent implements OnInit {

  public teams: Array<Team> = new Array<Team>();
  public teams$: Observable<any>;
  public page: string;
  constructor(
    private route: ActivatedRoute,
    private backendService: BackendService,
  ) {
    this.teams$ = backendService.getTeams();
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      console.log(params['game']);
      this.page = params['game'];

    });
    this.teams$.subscribe(pteams => {
      let team: Team;
      for (team of pteams){
        this.teams.push(team);
      }
    });
    console.log(this.teams);
  }
}
