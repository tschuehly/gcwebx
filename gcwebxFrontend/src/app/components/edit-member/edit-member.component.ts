import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';
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
  constructor(public activeModal: NgbActiveModal, private backendService: BackendService) { }

  ngOnInit(): void {
    this.EditForm = new FormGroup({
      memberId: new FormControl(null, [Validators.required]),
      name: new FormControl(null, [Validators.required]),
      teamspeakId: new FormControl(null),
      generalInfo: new FormControl(),
      dateOfBirth: new FormControl(),
      desblTeam: new FormControl(),
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

  deleteMember(){
    this.editMember = (this.EditForm.value as Member);
    this.editMember.deleted = true;
    this.backendService.updateMember(this.editMember).subscribe( data => console.log(data));
  }
}
