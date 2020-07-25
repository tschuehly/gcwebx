import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ModalDismissReasons, NgbActiveModal, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {BackendService} from '../../services/backend.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Member} from '../../model/member';
import {MemberTableComponent} from '../member-table/member-table.component';

@Component({
  selector: 'app-edit-member',
  templateUrl: './edit-member.component.html',
  styleUrls: ['./edit-member.component.css']
})
export class EditMemberComponent implements OnInit {
  @Input() member: Member;
  @Input() create: boolean;
  @Output() memberUpdated = new EventEmitter<boolean>();
  editMember: Member;
  EditForm: FormGroup;
  closeResult = '';
  constructor(public activeModal: NgbActiveModal, private backendService: BackendService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.EditForm = new FormGroup({
      memberId: new FormControl(null, [Validators.required]),
      name: new FormControl(null, [Validators.required]),
      teamspeakId: new FormControl(null),
      generalInfo: new FormControl(),
      dateOfBirth: new FormControl(),
      team: new FormControl(),
      joinDate: new FormControl(),
      acceptanceDate: new FormControl(),
      editor: new FormControl(),
      warnings: new FormControl(),
      uplayId: new FormControl()
    });
    this.EditForm.patchValue(this.member);
  }
  updateMember(){
    console.log('member: ' + JSON.stringify(this.member));
    this.editMember = this.member;
    this.editMember = (this.EditForm.value as Member);
    console.log('editmember: ' + JSON.stringify(this.editMember));
    this.backendService.updateMember(this.editMember).subscribe( data => console.log(data));
    this.memberUpdated.emit(true);
    this.activeModal.close();
  }
  createMember(){
    this.member = (this.EditForm.value as Member);
    this.member.deleted = false;
    console.log(this.member);
    this.backendService.createMember(this.member).subscribe( data => console.log(data));
    this.memberUpdated.emit(true);
    this.activeModal.close();
  }

  deleteMember(modal){
    modal.close();
    this.editMember = (this.EditForm.value as Member);
    this.editMember.deleted = true;
    this.backendService.updateMember(this.editMember).subscribe( data => console.log(data));
    this.memberUpdated.emit(true);
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
