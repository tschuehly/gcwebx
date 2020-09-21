import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Member} from '../../model/member';
import {Match} from '../../model/match';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ModalDismissReasons, NgbActiveModal, NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {BackendService} from '../../services/backend.service';
import {Team} from '../../model/team';

@Component({
  selector: 'app-edit-match',
  templateUrl: './edit-match.component.html',
  styleUrls: ['./edit-match.component.css']
})
export class EditMatchComponent implements OnInit {
  @Input() match: Match;
  @Input() teams: Team[];
  @Output() matchUpdated = new EventEmitter<boolean>();
  editMatch: Match;
  EditForm: FormGroup;
  closeResult = '';
  AddMemberForm: FormGroup;
  constructor(public activeModal: NgbActiveModal, private backendService: BackendService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.EditForm = new FormGroup({
      id: new FormControl(null, [Validators.required]),
      date: new FormControl(),
      hometeam: new FormControl(),
      title: new FormControl(),
      opponent: new FormControl(),
      opponentLogo: new FormControl(),
      scoreHome: new FormControl(0),
      scoreOpponent: new FormControl(0)
    });
    this.EditForm.patchValue(this.match);
  }

  createMatch(){
    console.log(this.EditForm.value);
    this.match = (this.EditForm.value as Match);
    console.log(this.match);
    this.backendService.createMatch(this.match).subscribe( data => {
      console.log(data);
      this.matchUpdated.emit(true);
    });
    this.activeModal.close();
  }
  deleteMatch(modal) {
    modal.close();
    this.editMatch = (this.EditForm.value as Match);
    this.backendService.deleteMatch(this.editMatch).subscribe(data => {
      console.log(data);
      this.matchUpdated.emit(true);
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
