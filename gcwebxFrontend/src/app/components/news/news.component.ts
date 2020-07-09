import { Component, OnInit } from '@angular/core';
import {Content} from "../../model/content";
import {Observable} from "rxjs";
import {BackendService} from "../../services/backend.service";
import {map} from "rxjs/operators";

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.css']
})
export class NewsComponent implements OnInit {

  contentList: Content[];
  contentList$: Observable<Content[]>;
  newsList$: Observable<Content[]>;

  constructor(private backendService: BackendService) { }

  ngOnInit(): void {

    this.contentList$ = this.backendService.getContent();
    this.backendService.getContent().
    subscribe(data => {
      this.contentList = data;
      console.log(data);
      console.log(this.contentList);
      this.sortNewsArray();

    });
  }

  sortNewsArray(){
    this.newsList$ = this.contentList$.pipe(map((data) => {
      data.sort((a, b) => {

        return a.lastUpdatedDate > b.lastUpdatedDate ? -1 : 1;
      });
      return data;
    }));
  }
  /*
   getContentByID(id:number){
    this.backendService.getContentById(id).subscribe(data => {
      this.content = data;
      console.log(this.content.text);
      this.EditContent.setValue(this.content);
    });
  }*/

}
