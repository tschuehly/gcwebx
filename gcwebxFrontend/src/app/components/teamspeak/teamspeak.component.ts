import {Component, ElementRef, OnInit, AfterViewInit, ViewChild, Input, Renderer2, Inject, ViewEncapsulation} from '@angular/core';
import {DOCUMENT} from '@angular/common';

@Component({
  selector: 'app-teamspeak',
  templateUrl: './teamspeak.component.html',
  styleUrls: ['./teamspeak.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class TeamspeakComponent implements OnInit{

constructor(
  private elementRef: ElementRef,
  private renderer2: Renderer2,
  @Inject(DOCUMENT) private document: Document) {
}
  ngOnInit(): void {
    const script = this.renderer2.createElement('script');
    script.type = 'text/javascript';
    script.async = true;
    script.src = 'https://ts3index.com/viewer/script.js';

    this.renderer2.appendChild(this.document.body, script);
  }
}
