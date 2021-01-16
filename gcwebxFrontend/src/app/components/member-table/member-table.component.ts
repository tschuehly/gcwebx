import {ApplicationRef, Component, Injector, OnInit, PipeTransform, QueryList, ViewChildren} from '@angular/core';
import {BackendService} from '../../services/backend.service';
import {Member} from '../../model/member';
import {DecimalPipe, KeyValue} from '@angular/common';
import {Observable, combineLatest, of} from 'rxjs';
import {FormControl, FormGroup} from '@angular/forms';
import {map, startWith, tap, filter} from 'rxjs/operators';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {EditMemberComponent} from '../edit-member/edit-member.component';
import {AuthenticationService} from '../../services/authentication.service';


@Component({
  selector: 'app-member-table',
  templateUrl: './member-table.component.html',
  styleUrls: ['./member-table.component.css'],
  providers: [ DecimalPipe ]
})
export class MemberTableComponent implements OnInit{
  member: Member;
  memberTable$: Observable<Member[]>;
  filteredMemberTable$: Observable<Member[]>;
  searchFilter: FormControl;
  searchFilter$: Observable<string>;
  deletedFilter: FormControl;
  deletedFilter$: Observable<boolean>;
  sortingArray: string[] = ['Owner', 'Techniker', 'Entwickler', 'Veteran', 'Stammspieler', 'Streamer', 'Member', 'Trial'];
  constructor(private backendService: BackendService,
              private modalService: NgbModal,
              private authenticationService: AuthenticationService) {
    this.memberTable$ = this.backendService.getMembers();
    this.backendService.getMembers().
    subscribe(memberTableResponse => {
      this.member = memberTableResponse[4];
    });
    this.searchFilter = new FormControl('');
    this.searchFilter$ = this.searchFilter.valueChanges.pipe(startWith(''));
    this.deletedFilter = new FormControl(false);
    this.deletedFilter$ = this.deletedFilter.valueChanges.pipe(startWith(false));
    this.filteredMemberTable$ = combineLatest([this.memberTable$, this.searchFilter$, this.deletedFilter$])
      .pipe(map(([members, filterString, filterBool]) =>
        members.filter(member => member.name.toLowerCase()
          .indexOf(filterString.toLowerCase()) !== -1 && member.deleted === filterBool )
          .sort( (a, b) => this.sortingArray.indexOf(a.rank) - this.sortingArray.indexOf(b.rank)))
      );
  }
  ngOnInit(): void {
  }

  editMember(member: Member){
    const  modalRef = this.modalService.open(EditMemberComponent, {size: 'xl', centered: true});
    modalRef.componentInstance.member = member;
    modalRef.componentInstance.create = false;
    modalRef.componentInstance.memberUpdated.subscribe((data) => {
      if (data === true) {
        window.location.reload();

        /*
        this.memberTable$ = this.backendService.getMemberTable();
        this.filteredMemberTable$ = combineLatest([this.memberTable$, this.filter$])
          .pipe(map(([members, filterString]) =>
            members.filter(member => member.name.toLowerCase()
              .indexOf(filterString.toLowerCase()) !== -1))
          ); */// TODO: Besserer Weg upzudaten?
      }
    });
  }
  authenticated() { return this.authenticationService.authenticated; }

  logout() {
    this.authenticationService.logout();
  }
  updateMemberTable(){
    this.memberTable$ = this.backendService.getMembers();

  }
  createMember() {
    const modalRef = this.modalService.open(EditMemberComponent, {size: 'xl', centered: true});
    const member: Member = {} as Member;
    modalRef.componentInstance.create = true;
    modalRef.componentInstance.member = member;
    modalRef.componentInstance.memberUpdated.subscribe((data) => {
      if (data === true) {window.location.reload(); }
    });
  }

originalOrder = (a: KeyValue<number, string>, b: KeyValue<number, string>): number => {
    return 0;
  }

}
