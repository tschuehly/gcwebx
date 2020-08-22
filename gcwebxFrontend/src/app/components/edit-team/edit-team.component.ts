import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Member} from '../../model/member';
import {Team} from '../../model/team';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ModalDismissReasons, NgbActiveModal, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {BackendService} from '../../services/backend.service';

@Component({
  selector: 'app-edit-team',
  templateUrl: './edit-team.component.html',
  styleUrls: ['./edit-team.component.css']
})
export class EditTeamComponent implements OnInit {
  @Input() team: Team;
  @Input() create: boolean;
  @Output() teamUpdated = new EventEmitter<boolean>();
  editTeam: Team;
  EditForm: FormGroup;
  closeResult = '';
  AddMemberForm: FormGroup;
  constructor(public activeModal: NgbActiveModal, private backendService: BackendService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.EditForm = new FormGroup({
      teamId: new FormControl(null, [Validators.required]),
      teamName: new FormControl(null, [Validators.required]),
      game: new FormControl(null),
      generalInfo: new FormControl(),
      members: new FormControl(),
    });
    this.AddMemberForm = new FormGroup({
      memberId: new FormControl()
    });
    this.EditForm.patchValue(this.team);
  }


  updateTeam(){
    console.log('team: ' + JSON.stringify(this.team));
    this.editTeam = this.team;
    this.editTeam = (this.EditForm.value as Team);
    this.editTeam.members = null;
    console.log('editteam: ' + JSON.stringify(this.editTeam));
    this.backendService.updateTeam(this.editTeam).subscribe( data => {
      console.log(data);
      this.teamUpdated.emit(true);
    } );
    this.activeModal.close();
  }
  createTeam(){
    this.team = (this.EditForm.value as Team);
    console.log(this.team);
    this.backendService.createTeam(this.team).subscribe( data =>{
      console.log(data);
      this.teamUpdated.emit(true);
    });
    this.activeModal.close();
  }
  addMemberToTeam(){
    this.editTeam = this.team;
    this.editTeam = (this.EditForm.value as Team);
    const member = this.AddMemberForm.value as Member;
    this.backendService.addMemberToTeam(member, this.editTeam.teamId).subscribe( data =>{
      console.log(data);
      this.teamUpdated.emit(true);
    });
    console.log('member: ' + JSON.stringify(member) + 'to Team: ' + this.editTeam.teamId);
  }
  deleteTeam(modal) {
    modal.close();
    this.editTeam = (this.EditForm.value as Team);
    this.backendService.deleteTeam(this.editTeam).subscribe(data => {
      console.log(data);
      this.teamUpdated.emit(true);
    });
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
