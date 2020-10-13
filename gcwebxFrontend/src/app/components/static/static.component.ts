import { Component, OnInit } from '@angular/core';
import domtoimage from 'dom-to-image';
import {ActivatedRoute, Router} from '@angular/router';
import 'twitch-player';
import {TwitchEmbed, TwitchEmbedLayout} from 'twitch-player';
import {Observable} from 'rxjs';
import {Content} from '../../model/content';
import {BackendService} from '../../services/backend.service';
import {DomSanitizer, SafeHtml} from '@angular/platform-browser';
@Component({
  selector: 'app-static',
  templateUrl: './static.component.html',
  styleUrls: ['./static.component.css']
})

export class StaticComponent implements OnInit {
  public logoUrl;
  logoName: string;
  public page;
  staticTitle1: SafeHtml;
  contentList: Content[];
  staticText1: SafeHtml;

  constructor(private router: Router, private backendService: BackendService
              , private sanitizer: DomSanitizer) {
  }

  ngOnInit(): void {
    this.page = this.router.url;
    console.log(this.router.url);
    console.log(window.window.innerWidth);
    this.backendService.getContent().
    subscribe(data => {
      this.contentList = data;
      const imprint: Content = this.contentList.filter(obj => obj.id === 2).pop();
      this.staticText1 = this.sanitizer.bypassSecurityTrustHtml(imprint.text);
      this.staticTitle1 = this.sanitizer.bypassSecurityTrustHtml(imprint.title);

    });
  }


downloadLogo() {
    domtoimage.toPng(document.querySelector('#logo')).then((dataUrl) => {
      this.logoUrl = dataUrl;
      const link = document.createElement('a');
      link.download = this.logoName;
      link.href = dataUrl;
      document.body.appendChild(link);
      link.click();
      document.body.removeChild(link);
    });
  }

}
