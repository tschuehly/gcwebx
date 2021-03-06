import {Component, OnInit} from '@angular/core';
import {Content} from '../../model/content';
import {Observable} from 'rxjs';
import {BackendService} from '../../services/backend.service';
import {map} from 'rxjs/operators';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.css']
})
export class NewsComponent implements OnInit {

  contentList: Content[];
  content: Content;
  contentList$: Observable<Content[]>;
  newsList$: Observable<Content[]>;
  firstTwentyWords$: Observable<Content[]>;
  closeResult = '';

  constructor(private backendService: BackendService, private modalService: NgbModal) { }

  ngOnInit(): void {

    this.contentList$ = this.backendService.getContent();
    this.backendService.getContent().
    subscribe(data => {
      this.contentList = data;
      this.sortNewsArray();

    });
  }

  getFirstTwentyWords(){
    this.firstTwentyWords$ = this.contentList$;

  }


  sortNewsArray(){
    this.newsList$ = this.contentList$.pipe(map((data) => {
      data.sort((a, b) => {

        return a.lastUpdatedDate > b.lastUpdatedDate ? -1 : 1;
      });
      return data;
    }));
  }

  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title', size: 'xl', centered: true}).result.then((result) => {
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
  closeModal(){
    this.modalService.dismissAll();
  }

}
