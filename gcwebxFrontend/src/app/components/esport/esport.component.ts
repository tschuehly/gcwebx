import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {BackendService} from '../../services/backend.service';
import {Team} from '../../model/team';
import {combineLatest, Observable} from 'rxjs';
import {EditMemberComponent} from '../edit-member/edit-member.component';
import {Member} from '../../model/member';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {EditTeamComponent} from '../edit-team/edit-team.component';
import {FormControl} from '@angular/forms';
import {map, startWith} from 'rxjs/operators';
import {Match} from '../../model/match';
import {EditMatchComponent} from '../edit-match/edit-match.component';

@Component({
  selector: 'app-esport',
  templateUrl: './esport.component.html',
  styleUrls: ['./esport.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class EsportComponent implements OnInit {
  public team: Team;
  public teams$: Observable<Team[]>;
  public matches$: Observable<Match[]>;
  public sortedMatches$: Observable<Match[]>;
  public game: FormControl;
  gamefilter$: Observable<string>;
  currentTeam: Team;
  public filteredTeams$: Observable<Team[]>;
  constructor(
    private route: ActivatedRoute,
    private backendService: BackendService,
    private modalService: NgbModal,
  ) {
    this.teams$ = backendService.getTeams();
    this.game = new FormControl('');
    this.gamefilter$ = this.game.valueChanges.pipe(startWith(''));
    this.filteredTeams$ = combineLatest([this.teams$, this.route.params]).pipe(map(([teams, gameRoute]) =>
      teams.filter(team => team.game === gameRoute['game']).sort((a: Team, b:Team) => {
        return a.teamName.toLowerCase() === 'xperience' ? -1 : b.teamName.toLowerCase() === 'xperience' ? 1 : 0;
      })));
    this.matches$ = backendService.getMatches();
    this.sortMatches();
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.game.setValue(params['game']);

      this.filteredTeams$.subscribe(teams => {
        this.currentTeam = teams[0];
      });

    });
  }

  sortMatches(){
    this.sortedMatches$ = this.matches$.pipe(map((data) => {
      data.sort((a, b) => {
        return a.date > b.date ? -1 : 1;
      });
      return data;
    }));
  }
  createTeam(){
    const modalRef = this.modalService.open(EditTeamComponent, {size: 'xl', centered: true});
    const team: Team = {} as Team;
    modalRef.componentInstance.create = true;
    modalRef.componentInstance.team = team;
    modalRef.componentInstance.teamUpdated.subscribe((data) => {
      if (data === true) {window.location.reload(); }
    });
  }

  editTeam(team: Team){
    const  modalRef = this.modalService.open(EditTeamComponent, {size: 'xl', centered: true});
    modalRef.componentInstance.team = team;
    modalRef.componentInstance.create = false;
    modalRef.componentInstance.teamUpdated.subscribe((data) => {
      if (data === true) {window.location.reload(); }
    });
  }
  createMatch(){
    this.teams$.subscribe( (team) => {
      modalRef.componentInstance.teams = team;
    });
    const modalRef = this.modalService.open(EditMatchComponent, {size: 'xl', centered: true});
    const match: Match = {} as Match;
    modalRef.componentInstance.match = match;
    modalRef.componentInstance.matchUpdated.subscribe((data) => {
      if (data === true) {window.location.reload(); }
    });
  }
  deleteMatch(match: Match){
    this.backendService.deleteMatch(match.id).subscribe( (data) => {
      if (data === true){window.location.reload(); }
    });
  }

  editMatch(match: Match){
    this.teams$.subscribe( (team) => {
      modalRef.componentInstance.teams = team;
    });
    const  modalRef = this.modalService.open(EditMatchComponent, {size: 'xl', centered: true});
    modalRef.componentInstance.match = match;
    modalRef.componentInstance.create = false;
    modalRef.componentInstance.matchUpdated.subscribe((data) => {
      if (data === true) {window.location.reload(); }
    });
  }

}


