import { Pipe, PipeTransform} from '@angular/core';
import { DatePipe } from '@angular/common';

@Pipe({
  name: 'dateFormat'
})
export class DateFormat extends DatePipe implements PipeTransform {
  transform(value: any, args?: any): any {
    ///MMM/dd/yyyy
    return super.transform(value, "yyyy");
  }
}
