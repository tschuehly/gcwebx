import {Pipe, PipeTransform} from "@angular/core";

@Pipe({name: 'first20words'})
export class FirstTwentyWords implements PipeTransform {
    transform(value: String, ...args): any {

        let array = value.split(" ", 10);
        let returnString = array.join(" ");
        if(value.split(" ").length > 10){
          returnString += " ...";
        }
        return returnString
    }
}
