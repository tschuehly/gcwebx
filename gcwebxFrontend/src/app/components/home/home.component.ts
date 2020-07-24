import {Component, ElementRef, OnInit, Pipe, PipeTransform, ViewChild} from '@angular/core';
import {NgbCarousel, NgbCarouselConfig, NgbSlideEvent, NgbSlideEventSource} from '@ng-bootstrap/ng-bootstrap';
import {Router} from '@angular/router';
import {Content} from '../../model/content';
import {Observable} from 'rxjs';
import {BackendService} from '../../services/backend.service';
import {DomSanitizer, SafeHtml} from '@angular/platform-browser';
declare let html2canvas: any;
import { saveAs } from 'file-saver';
import domtoimage from 'dom-to-image';
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
  private elementRef: ElementRef;
  content: Content;
  contentList: Content[];
  contentList$: Observable<Content[]>;
  index: number;
  staticText1: SafeHtml;
  logoName: string;
  public logoUrl;
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

  downloadLogo(){
    domtoimage.toPng(document.querySelector('#logo')).then((dataUrl) => {
      this.logoUrl = dataUrl;
      let link = document.createElement('a');
      link.download = this.logoName;
      link.href = dataUrl;
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    });

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
