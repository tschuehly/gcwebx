import {Component, Input, OnInit} from "@angular/core";
import {BackendService} from '../../services/backend.service';
import {Content} from "../../model/content";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Member} from "../../model/member";

//Init } from '@angular/core';

@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css']
})
export class EditorComponent implements OnInit {

  @Input() newContent: Content;
  private text: String;
  private contents: Content[] =[];

  public EditContent: FormGroup;


  constructor(private backendService: BackendService) { }

  ngOnInit(): void {

    this.EditContent = new FormGroup({
      text: new FormControl('')
    });
  }

  save(){
    this.text = this.EditContent.getRawValue();
    //console.log(FormControl[("text")].value);
    console.log(this.text);
    this.newContent = this.EditContent.getRawValue() as Content;
    console.log(this.newContent.text);

    this.backendService.createContent(this.newContent).subscribe( data => console.log(data));

  }

}
