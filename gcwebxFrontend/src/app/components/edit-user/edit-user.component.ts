import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ModalDismissReasons, NgbActiveModal, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {BackendService} from '../../services/backend.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {User} from '../../model/user';
import {UserTableComponent} from '../user-table/user-table.component';

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
      username: new FormControl(null, [Validators.required]),
      role: new FormControl(null)
    });
    this.EditForm.patchValue(this.user);
  }
  updateUser(){
    console.log('user: ' + JSON.stringify(this.user));
    this.editUser = this.user;
    this.editUser = (this.EditForm.value as User);
    console.log('edituser: ' + JSON.stringify(this.editUser));
    this.backendService.updateUser(this.editUser).subscribe( data => console.log(data));
    this.userUpdated.emit(true);
    this.activeModal.close();
  }
  createUser(){
    this.user = (this.EditForm.value as User);
    console.log(this.user);
    this.backendService.createUser(this.user).subscribe( data => console.log(data));
    this.userUpdated.emit(true);
    this.activeModal.close();
  }

  deleteUser(modal){
    modal.close();
    this.editUser = (this.EditForm.value as User);
    this.backendService.updateUser(this.editUser).subscribe( data => console.log(data));
    this.userUpdated.emit(true);
    this.activeModal.close();
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
