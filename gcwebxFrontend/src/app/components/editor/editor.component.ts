import {Component, Input, OnInit} from "@angular/core";
import {BackendService} from '../../services/backend.service';
import {Content} from "../../model/content";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Member} from "../../model/member";
import {Observable} from "rxjs";

//Init } from '@angular/core';

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

  public EditContent: FormGroup;


  constructor(private backendService: BackendService) { }

  ngOnInit(): void {

    this.EditContent = new FormGroup({
      id: new FormControl(''),
      text: new FormControl('')
    });

    this.contentList$ = this.backendService.getContent();
    this.backendService.getContent().
    subscribe(data => {
      this.contentList = data;
      console.log(data);
      console.log(this.contentList);
    });

    //this.backendService.getContent().subscribe(data => this.contentList$ = <Content>data)


  }

  updateContent(){
    console.log('content: ' + JSON.stringify(this.EditContent.getRawValue()));
    this.editContent = this.EditContent.getRawValue() as Content;
    this.backendService.updateContent(this.editContent).subscribe( data => console.log(data));

  }

  createContent(){
    this.EditContent.patchValue({id: "null"});
    this.newContent = this.EditContent.getRawValue() as Content;
    console.log((this.EditContent.getRawValue() as Content));
    this.backendService.createContent(this.newContent).subscribe( data => this.contentList.push(data));
  }

  deleteContent(){
    this.content = (this.EditContent.getRawValue() as Content);
    console.log("zu löschendes Element" + JSON.stringify(this.content));
    this.index = this.contentList.findIndex(content => content.id === this.content.id);

    this.backendService.deleteContent(this.content.id).subscribe();
    this.contentList.splice(this.index, 1);
  }

  getContentByID(id:number){
    this.backendService.getContentById(id).subscribe(data => {
      this.content = data;
      console.log(this.content.text);
      this.EditContent.setValue(this.content);
    });




  }

}
