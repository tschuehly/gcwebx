import {Component, OnInit, Pipe, PipeTransform, ViewChild} from '@angular/core';
import {NgbCarousel, NgbCarouselConfig, NgbSlideEvent, NgbSlideEventSource} from '@ng-bootstrap/ng-bootstrap';
import {Router} from '@angular/router';
import {Content} from "../../model/content";
import {Observable} from "rxjs";
import {BackendService} from "../../services/backend.service";
import {DomSanitizer, SafeHtml} from "@angular/platform-browser";
import {map} from "rxjs/operators";
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})


export class HomeComponent implements OnInit {


  images = ['https://ts.xperience-gaming.de/preview/images/background.jpg',
            'https://i.imgur.com/fTa59Lw.jpg',
            'https://i.imgur.com/W5Tq6YD.jpg',
            'https://i.imgur.com/0Nqqv5J.jpg'];
  paused = false;
  unpauseOnArrow = false;
  pauseOnIndicator = false;
  pauseOnHover = true;

  content: Content;
  contentList: Content[];
  news: Content[];
  contentList$: Observable<Content[]>;
  newsList$: Observable<Content[]>;

  index: number;
  newsIndex: number = 0;
  staticText1: SafeHtml;
  staticTitle1: SafeHtml;
  news1: Content;
  news2: Content;
  news3: Content;
  constructor(config: NgbCarouselConfig, private backendService: BackendService, private sanitizer: DomSanitizer) {
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
      console.log(data);
      console.log(this.contentList);

      this.staticText1 = this.sanitizer.bypassSecurityTrustHtml(this.contentList[0].text);
      this.staticTitle1 = this.sanitizer.bypassSecurityTrustHtml(this.contentList[0].title);
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


  togglePaused() {
    if (this.paused) {
      this.carousel.cycle();
    } else {
      this.carousel.pause();
    }
    this.paused = !this.paused;
  }

  onSlide(slideEvent: NgbSlideEvent) {
    if (this.unpauseOnArrow && slideEvent.paused &&
      (slideEvent.source === NgbSlideEventSource.ARROW_LEFT || slideEvent.source === NgbSlideEventSource.ARROW_RIGHT)) {
      this.togglePaused();
    }
    if (this.pauseOnIndicator && !slideEvent.paused && slideEvent.source === NgbSlideEventSource.INDICATOR) {
      this.togglePaused();
    }
  }

}


@Pipe({name: 'safeHtml'})
export class Safe {
  constructor(private sanitizer: DomSanitizer) {
  }

  transform(value: any, args?: any): any {
    return this.sanitizer.bypassSecurityTrustHtml(value);
    // return this.sanitizer.bypassSecurityTrustStyle(style);
    // return this.sanitizer.bypassSecurityTrustXxx(style); - see docs
  }
}
