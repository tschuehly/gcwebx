import { Component, OnInit } from '@angular/core';
import domtoimage from 'dom-to-image';
import {Router} from '@angular/router';
import 'twitch-player';
import {Content} from '../../model/content';
import {BackendService} from '../../services/backend.service';
import {DomSanitizer, SafeHtml} from '@angular/platform-browser';
import {Member} from '../../model/member';
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
  streamer: Member[];
  youtuber: Member[];
  contentList: Content[];
  staticText1: SafeHtml;

  constructor(private router: Router, private backendService: BackendService,  private sanitizer: DomSanitizer) {
  }

  ngOnInit(): void {
    this.page = this.router.url;
    this.backendService.getContent().
    subscribe(data => {
      this.contentList = data;
      const imprint: Content = this.contentList.filter(obj => obj.id === 2).pop();
      this.staticText1 = this.sanitizer.bypassSecurityTrustHtml(imprint.text);
      this.staticTitle1 = this.sanitizer.bypassSecurityTrustHtml(imprint.title);

    });
    this.backendService.getStreamer().subscribe(data => {
        this.streamer  = data;
      }
    );
    this.backendService.getYoutuber().subscribe(data => {
        this.youtuber  = data;
      }
    );
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
  getTwitchURL(name: string): SafeHtml{
    const url = 'https://player.twitch.tv/?channel=' + name + '&parent=' + window.location.hostname + '&muted';
    return this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }
  getYoutubeURL(name: string): SafeHtml{
    let url;
    if (name.startsWith('UC')){
      const channelId = name.replace('UC', 'UU');
      url = 'https://www.youtube.com/embed/videoseries?list=' + channelId;
    }else  {
      url = 'https://www.youtube.com/embed?max-results=1&showinfo=0&rel=0&listType=user_uploads&list=' + name;
    }
    return this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }
}
