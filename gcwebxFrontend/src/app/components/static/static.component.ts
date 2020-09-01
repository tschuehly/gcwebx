import { Component, OnInit } from '@angular/core';
import domtoimage from 'dom-to-image';
import {ActivatedRoute, Router} from '@angular/router';
@Component({
  selector: 'app-static',
  templateUrl: './static.component.html',
  styleUrls: ['./static.component.css']
})
export class StaticComponent implements OnInit {
  public logoUrl;
  logoName: string;
  public page;
  constructor(private router: Router) {
  }

  ngOnInit(): void {
    this.page = this.router.url;
    console.log(this.router.url);
  }

  downloadLogo(){
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
