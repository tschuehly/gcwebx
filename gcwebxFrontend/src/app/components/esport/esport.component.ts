import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {BackendService} from '../../services/backend.service';
import {Team} from '../../model/team';
import {Observable} from 'rxjs';
import {EditMemberComponent} from '../edit-member/edit-member.component';
import {Member} from '../../model/member';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {EditTeamComponent} from '../edit-team/edit-team.component';

@Component({
  selector: 'app-esport',
  templateUrl: './esport.component.html',
  styleUrls: ['./esport.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class EsportComponent implements OnInit {
  public team: Team;
  public teams$: Observable<Team[]>;
  public page: string;
  public currentTeam: Team;
  constructor(
    private route: ActivatedRoute,
    private backendService: BackendService,
    private modalService: NgbModal,
  ) {
    this.teams$ = backendService.getTeams();
    this.teams$.subscribe(teams => {
       this.currentTeam = teams.pop();
    });
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      console.log(params['game']);
      this.page = params['game'];

    });
  }

  createTeam(){
    const modalRef = this.modalService.open(EditTeamComponent, {size: 'xl'});
    const team: Team = {} as Team;
    modalRef.componentInstance.create = true;
    modalRef.componentInstance.team = team;
    modalRef.componentInstance.teamUpdated.subscribe((data) => {
      if (data === true) {window.location.reload(); }
    });
  }

  editTeam(team: Team){
    const  modalRef = this.modalService.open(EditTeamComponent, {size: 'xl'});
    modalRef.componentInstance.team = team;
    modalRef.componentInstance.create = false;
    modalRef.componentInstance.teamUpdated.subscribe((data) => {
      if (data === true) {window.location.reload(); }
    });
  }

}


