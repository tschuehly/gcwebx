import {ApplicationRef, Component, Injector, OnInit, PipeTransform, QueryList, ViewChildren} from '@angular/core';
import {BackendService} from '../../services/backend.service';
import {Member} from '../../model/member';
import {DecimalPipe, KeyValue} from '@angular/common';
import {Observable, combineLatest, of} from 'rxjs';
import {FormControl, FormGroup} from '@angular/forms';
import {map, startWith, tap, filter} from 'rxjs/operators';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {EditMemberComponent} from '../edit-member/edit-member.component';

@Component({
  selector: 'app-member-table',
  templateUrl: './member-table.component.html',
  styleUrls: ['./member-table.component.css'],
  providers: [ DecimalPipe ]
})
export class MemberTableComponent implements OnInit{
  member: Array<string>;
  memberTable$: Observable<Member[]>;
  filteredMemberTable$: Observable<Member[]>;
  searchFilter: FormControl;
  searchFilter$: Observable<string>;
  deletedFilter: FormControl;
  deletedFilter$: Observable<boolean>;
  constructor(private backendService: BackendService, private modalService: NgbModal) {
    this.memberTable$ = this.backendService.getMemberTable();
    this.backendService.getMemberTable().
    subscribe(memberTableResponse => {
      this.member = Object.keys(memberTableResponse[4]);
    });
    this.searchFilter = new FormControl('');
    this.searchFilter$ = this.searchFilter.valueChanges.pipe(startWith(''));
    this.deletedFilter = new FormControl(false);
    this.deletedFilter$ = this.deletedFilter.valueChanges.pipe(startWith(false));
    this.filteredMemberTable$ = combineLatest([this.memberTable$, this.searchFilter$, this.deletedFilter$])
      .pipe(map(([members, filterString, filterBool]) =>
        members.filter(member => member.name.toLowerCase()
          .indexOf(filterString.toLowerCase()) !== -1 && member.deleted === filterBool ))
      );
  }
  ngOnInit(): void {
  }

  editMember(member: Member){
    const  modalRef = this.modalService.open(EditMemberComponent, {size: 'xl'});
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



updateMemberTable(){
    this.memberTable$ = this.backendService.getMemberTable();
    console.log('UpdateMember was called');

  }
  createMember() {
    const modalRef = this.modalService.open(EditMemberComponent, {size: 'xl'});
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
