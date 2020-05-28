import {ApplicationRef, Component, OnInit, PipeTransform, QueryList, ViewChildren} from '@angular/core';
import {BackendService} from '../../services/backend.service';
import {Member} from '../../model/member';
import {DecimalPipe, KeyValue} from '@angular/common';
import {Observable, combineLatest, of} from 'rxjs';
import {FormControl} from '@angular/forms';
import {map, startWith, tap} from 'rxjs/operators';
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
  filter: FormControl;
  filter$: Observable<string>;

  constructor(private backendService: BackendService, private modalService: NgbModal) {
    this.memberTable$ = this.backendService.getMemberTable();
    this.backendService.getMemberTable().
    subscribe(memberTableResponse => {
      this.member = Object.keys(memberTableResponse[4]);
    });
    this.filter = new FormControl('');
    this.filter$ = this.filter.valueChanges.pipe(startWith(''));
    this.filteredMemberTable$ = combineLatest([this.memberTable$, this.filter$])
      .pipe(map(([members, filterString]) =>
        members.filter(member => member.name.toLowerCase()
          .indexOf(filterString.toLowerCase()) !== -1))
      );
  }

  editMember(member: Member){
    const  modalRef = this.modalService.open(EditMemberComponent, {size: 'xl'});
    modalRef.componentInstance.member = member;
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

ngOnInit(): void {
  }

updateMemberTable(){
    this.memberTable$ = this.backendService.getMemberTable();
    console.log('UpdateMember was called');

  }

originalOrder = (a: KeyValue<number, string>, b: KeyValue<number, string>): number => {
    return 0;
  }

}
