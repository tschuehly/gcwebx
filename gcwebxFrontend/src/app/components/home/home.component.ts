import {Component, ElementRef, OnInit, ViewChild, ViewEncapsulation} from '@angular/core';
import {
  ModalDismissReasons,
  NgbCarousel,
  NgbCarouselConfig,
  NgbModal,
  NgbSlideEvent,
  NgbSlideEventSource
} from '@ng-bootstrap/ng-bootstrap';
import {Router} from '@angular/router';
import {Content} from '../../model/content';
import {Observable} from 'rxjs';
import {BackendService} from '../../services/backend.service';
import {DomSanitizer, SafeHtml} from '@angular/platform-browser';
declare let html2canvas: any;
import {map} from 'rxjs/operators';
import {Match} from '../../model/match';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  encapsulation: ViewEncapsulation.None,
})

export class HomeComponent implements OnInit {

  paused = false;
  unpauseOnArrow = false;
  pauseOnIndicator = false;
  pauseOnHover = true;
  private elementRef: ElementRef;
  content: Content;
  contentList: Content[];
  news: Content[];
  contentList$: Observable<Content[]>;
  newsList$: Observable<Content[]>;
  staticTitle1: SafeHtml;
  closeResult = '';
  index: number;
  staticText1: SafeHtml;
  matches$: Observable<Match[]>;
  sortedMatches$: Observable<Match[]>;

  constructor(config: NgbCarouselConfig, private backendService: BackendService,
              private sanitizer: DomSanitizer, private modalService: NgbModal) {
    config.interval = 20000;
    config.wrap = true;
    config.pauseOnHover = false;
  }
  @ViewChild('carousel', {static : true}) carousel: NgbCarousel;

  ngOnInit(): void {

    this.contentList$ = this.backendService.getContent();
    this.backendService.getContent().
    subscribe(data => {
      this.contentList = data;
      const homeText: Content = this.contentList.filter(obj => obj.id === 1).pop();
      this.staticText1 = this.sanitizer.bypassSecurityTrustHtml(homeText.text);
      this.staticTitle1 = this.sanitizer.bypassSecurityTrustHtml(homeText.title);
      this.sortNewsArray();

    });
    this.matches$ = this.backendService.getMatches();
    this.matches$.subscribe(data => this.sortMatches());
  }

  sortNewsArray(){
    this.newsList$ = this.contentList$.pipe(map((data) => {
      data.sort((a, b) => {

        return a.lastUpdatedDate > b.lastUpdatedDate ? -1 : 1;
      });
      return data;
    }));
  }
  sortMatches(){
    this.sortedMatches$ = this.matches$.pipe(map((data) => {
      data.sort((a, b) => {
        return a.date > b.date ? -1 : 1;
      });
      return data;
    }));
  }




  togglePaused() {
    if (this.paused) {
      this.carousel.cycle();
    } else {
      this.carousel.pause();
    }
    this.paused = !this.paused;
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



