import {ApplicationRef, Component, Injector, OnInit, PipeTransform, QueryList, ViewChildren} from '@angular/core';
import {BackendService} from '../../services/backend.service';
import {User} from '../../model/user';
import {DecimalPipe, KeyValue} from '@angular/common';
import {Observable, combineLatest, of} from 'rxjs';
import {FormControl, FormGroup} from '@angular/forms';
import {map, startWith, tap, filter} from 'rxjs/operators';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {EditUserComponent} from '../edit-user/edit-user.component';
import {AuthenticationService} from '../../services/authentication.service';


@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.css'],
  providers: [ DecimalPipe ]
})
export class UserTableComponent implements OnInit{
  user: User;
  userTable$: Observable<User[]>;
  filteredUserTable$: Observable<User[]>;
  searchFilter: FormControl;
  searchFilter$: Observable<string>;
  constructor(private backendService: BackendService,
              private modalService: NgbModal,
              private authenticationService: AuthenticationService) {
    this.userTable$ = this.backendService.getUserTable();
    this.backendService.getUserTable().
    subscribe(userTableResponse => {
      this.user = userTableResponse[4];
      console.log(userTableResponse);
      console.log(this.userTable$);
    });
    this.searchFilter = new FormControl('');
    this.searchFilter$ = this.searchFilter.valueChanges.pipe(startWith(''));
    this.filteredUserTable$ = combineLatest([this.userTable$, this.searchFilter$])
      .pipe(map(([users, filterString]) =>
        users.filter(user => user.username.toLowerCase()
          .indexOf(filterString.toLowerCase()) !== -1  ))
      );
  }
  ngOnInit(): void {
  }

  editUser(user: User){
    const  modalRef = this.modalService.open(EditUserComponent, {size: 'xl'});
    modalRef.componentInstance.user = user;
    modalRef.componentInstance.create = false;
    modalRef.componentInstance.userUpdated.subscribe((data) => {
      if (data === true) {
        window.location.reload();

        /*
        this.userTable$ = this.backendService.getUserTable();
        this.filteredUserTable$ = combineLatest([this.userTable$, this.filter$])
          .pipe(map(([users, filterString]) =>
            users.filter(user => user.name.toLowerCase()
              .indexOf(filterString.toLowerCase()) !== -1))
          ); */// TODO: Besserer Weg upzudaten?
      }
    });
  }
  authenticated() { return this.authenticationService.authenticated; }

  logout() {
    this.authenticationService.logout();
  }
  updateUserTable(){
    this.userTable$ = this.backendService.getUserTable();
    console.log('UpdateUser was called');

  }
  createUser() {
    const modalRef = this.modalService.open(EditUserComponent, {size: 'xl'});
    const user: User = {} as User;
    modalRef.componentInstance.create = true;
    modalRef.componentInstance.user = user;
    modalRef.componentInstance.userUpdated.subscribe((data) => {
      if (data === true) {window.location.reload(); }
    });
  }

  originalOrder = (a: KeyValue<number, string>, b: KeyValue<number, string>): number => {
    return 0;
  }

}
