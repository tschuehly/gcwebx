import { Component, OnInit } from '@angular/core';
import {User} from "../models/user";
import {FormControl, FormGroup} from "@angular/forms";
import {UserService} from "../services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-registration-form',
  templateUrl: './registration-form.component.html',
  styleUrls: ['./registration-form.component.css']
})
export class RegistrationFormComponent implements OnInit {

  newUser: any;
  users: User[] =[];

  UserRegistrationForm = new FormGroup({
    username: new FormControl('usernam'),
    email: new FormControl('emailadresse'),
    password: new FormControl('passwd'),
    password_repeat: new FormControl('passwd wiederholt')

  });

  constructor(private userservice: UserService, private router:Router) { }

  ngOnInit(): void {
  }

  zurueck(){
    this.router.navigateByUrl('/getMembers');
  }

  erstelleAcc(){

    console.log(this.UserRegistrationForm.value);
    this.newUser = this.UserRegistrationForm.value;

    this.userservice
      .createAccount(this.newUser)
      .subscribe(data => {
        this.users.push(data)
      });

    //this.router.navigateByUrl('/getMembers');
    console.log(this.users);
  }

}
