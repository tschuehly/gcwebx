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
  contentID: number;
  private text: String;

  public EditContent: FormGroup;


  constructor(private backendService: BackendService) { }

  ngOnInit(): void {

    this.EditContent = new FormGroup({
      id: new FormControl(''),
      text: new FormControl('')
    });
  }

  updateContent(){


    console.log('content: ' + JSON.stringify(this.newContent));
    this.editContent = this.newContent;
    this.editContent = (this.EditContent.value as Content);
    console.log('editcontent: ' + JSON.stringify(this.editContent));
    this.backendService.updateContent(this.editContent).subscribe( data => console.log(data));

  }

  createContent(){
    this.text = this.EditContent.getRawValue();
    //console.log(FormControl[("text")].value);
    console.log(this.text);
    this.newContent = this.EditContent.getRawValue() as Content;
    console.log(this.newContent.text);

    this.backendService.createContent(this.newContent).subscribe( data => console.log(data));
  }

  deleteContent(){
    this.editContent = (this.EditContent.value as Content);
    this.backendService.deleteContent(this.editContent).subscribe( data => console.log(data));
  }


  getContent(id:number){
    this.backendService.getContentById(id).subscribe(data => {
      this.content = data;
      console.log(this.content);
      this.EditContent.setValue(this.content);
    });




  }

}
