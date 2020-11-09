import {Component, Input, OnInit} from '@angular/core';
import {BackendService} from '../../services/backend.service';
import {Content} from '../../model/content';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Member} from '../../model/member';
import {Observable} from 'rxjs';
import {ModalDismissReasons, NgbActiveModal, NgbModal} from '@ng-bootstrap/ng-bootstrap';
// Init } from '@angular/core';

@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css']
})
export class EditorComponent implements OnInit {

  newContent: Content;
  content: Content;
  editContent: Content;
  contentList: Content[];
  contentList$: Observable<Content[]>;
  index: number;
  news: boolean;
  creationDate: Date;
  lastUpdatedDate: Date;
  closeResult = '';
  showNews = true;

  public EditContent: FormGroup;


  constructor(private backendService: BackendService, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.EditContent = new FormGroup({
      id: new FormControl(''),
      title: new FormControl(''),
      text: new FormControl(''),
      news: new FormControl(''),
      creationDate: new FormControl(''),
      lastUpdatedDate: new FormControl(''),
    });


    this.contentList$ = this.backendService.getContent();
    this.backendService.getContent().
    subscribe(data => {
      this.contentList = data;
      console.log(data);
      console.log(this.contentList);
    });

  }


  updateContent(){
    console.log('content: ' + JSON.stringify(this.EditContent.getRawValue()));
    this.editContent = this.EditContent.getRawValue() as Content;
    this.lastUpdatedDate = new Date();
    this.editContent.lastUpdatedDate = this.lastUpdatedDate;
    this.backendService.updateContent(this.editContent).subscribe( data => console.log(data));

  }

  createContent(){
    this.creationDate = new Date();
    this.lastUpdatedDate = new Date();
    console.log('Date = ' + this.lastUpdatedDate);
    this.EditContent.patchValue({id: 'null'});
    this.newContent = this.EditContent.getRawValue() as Content;
    this.newContent.news = this.news;
    this.newContent.creationDate = this.creationDate;
    this.newContent.lastUpdatedDate = this.lastUpdatedDate;
    console.log((this.EditContent.getRawValue() as Content));
    this.backendService.createContent(this.newContent).subscribe( data => this.contentList.push(data));
    this.modalService.dismissAll();
  }

  deleteContent(){
    this.content = (this.EditContent.getRawValue() as Content);
    if (this.content.news){
      console.log('zu löschendes Element' + JSON.stringify(this.content));
      this.index = this.contentList.findIndex(content => content.id === this.content.id);

      this.backendService.deleteContent(this.content.id).subscribe();
      this.contentList.splice(this.index, 1);
    }
  }

  getContentByID(id: number){
    this.backendService.getContentById(id).subscribe(data => {
      this.content = data;
      console.log(this.content);
      this.EditContent.setValue(this.content);
    });
  }

  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
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
