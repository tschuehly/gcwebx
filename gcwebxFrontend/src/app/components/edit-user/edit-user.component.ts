import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ModalDismissReasons, NgbActiveModal, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {BackendService} from '../../services/backend.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {User} from '../../model/user';
import {UserTableComponent} from '../user-table/user-table.component';
import * as bcrypt from 'bcryptjs';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {
  @Input() user: User;
  @Input() create: boolean;
  @Output() userUpdated = new EventEmitter<boolean>();
  editUser: User;
  EditForm: FormGroup;
  closeResult = '';
  constructor(public activeModal: NgbActiveModal, private backendService: BackendService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.EditForm = new FormGroup({
      id: new FormControl(),
      username: new FormControl(null, [Validators.required]),
      roleUser: new FormControl(null),
      roleEditor: new FormControl(null),
      roleSupport: new FormControl(null),
      roleModerator: new FormControl(null),
      roleAdmin: new FormControl(null),
      password: new FormControl()
    });
    this.EditForm.patchValue(this.user);
  }
  updateUser(){
    this.editUser = this.user;
    this.editUser = (this.EditForm.value as User);
    if (this.editUser.password != null){
      const salt = bcrypt.genSaltSync(10);
      this.editUser.password = bcrypt.hashSync(this.editUser.password, 10);
    }
    this.backendService.updateUser(this.editUser).subscribe(
      data => {
        this.userUpdated.emit(true);
      });
    this.activeModal.close();
  }
  createUser(){
    this.user = (this.EditForm.value as User);
    const salt = bcrypt.genSaltSync(10);
    this.user.password = bcrypt.hashSync(this.user.password, 10);

    this.backendService.createUser(this.user).subscribe( data => {
      this.userUpdated.emit(true);
    });
    this.activeModal.close();
  }

  deleteUser(modal){
    modal.close();
    this.editUser = (this.EditForm.value as User);
    this.backendService.deleteUser(this.editUser).subscribe( data => {
      this.userUpdated.emit(true);
      this.activeModal.close();
    });
  }

  openConfirm(content){
    this.modalService.open(content, {centered: true, size: 'sm', ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }
}
