import { Component, OnInit } from '@angular/core';

import domtoimage from 'dom-to-image';
@Component({
  selector: 'app-logo',
  templateUrl: './logo.component.html',
  styleUrls: ['./logo.component.css']
})
export class LogoComponent implements OnInit {

  public logoUrl;
  logoName: string;
  constructor() { }

  ngOnInit(): void {
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
