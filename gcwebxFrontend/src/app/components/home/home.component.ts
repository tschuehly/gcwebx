import {Component, OnInit, Pipe, PipeTransform, ViewChild} from '@angular/core';
import {NgbCarousel, NgbCarouselConfig, NgbSlideEvent, NgbSlideEventSource} from '@ng-bootstrap/ng-bootstrap';
import {Router} from '@angular/router';
import {Content} from "../../model/content";
import {Observable} from "rxjs";
import {BackendService} from "../../services/backend.service";
import {DomSanitizer, SafeHtml} from "@angular/platform-browser";
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})


export class HomeComponent implements OnInit {

  img1 = 'https://ts.xperience-gaming.de/preview/images/background.jpg';
  img2 = 'https://staticctf.akamaized.net/J3yJr34U2pZ2Ieem48Dwy9uqj5PNUQTn/1Bg0RXEfNZM1nOiufjbj0O/fdd5eb79c5353ad9499bf4dde0a20bb0/r6s-home-operators-y5s2.png';
  img3 = 'https://i.imgur.com/W5Tq6YD.jpg';
  paused = false;
  unpauseOnArrow = false;
  pauseOnIndicator = false;
  pauseOnHover = true;

  content: Content;
  contentList: Content[];
  contentList$: Observable<Content[]>;
  index: number;
  staticText1: SafeHtml;
  constructor(config: NgbCarouselConfig, private backendService: BackendService, protected sanitizer: DomSanitizer) {
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
    });

   // this.staticText1 = this.sanitizer.bypassSecurityTrustHtml(this.contentList[1].text);
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
