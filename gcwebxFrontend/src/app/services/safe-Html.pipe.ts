import {Pipe} from "@angular/core";
import {DomSanitizer} from "@angular/platform-browser";



@Pipe({name: 'safeHtml'})
export class SafeHtml {
  constructor(private sanitizer: DomSanitizer) {
  }

  transform(value: any, args?: any): any {
    return this.sanitizer.bypassSecurityTrustHtml(value);
    // return this.sanitizer.bypassSecurityTrustStyle(style);
    // return this.sanitizer.bypassSecurityTrustXxx(style); - see docs
  }
}
