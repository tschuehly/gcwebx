import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Router} from "@angular/router";
import {User} from "../models/user";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {


  constructor(private userservice: UserService, private router:Router) { }

  newUser: any;
  users: User[] =[];

  UserForm = new FormGroup({
    username: new FormControl('usernam'),
    email: new FormControl('emailadresse'),
    password: new FormControl('passwd'),
    password_repeat: new FormControl('passwd wiederholt')

  });

  ngOnInit(): void {
  }

  zurueck(){
    this.router.navigateByUrl('/getMembers');
  }

  erstelleAcc(){

    console.log(this.UserForm.value);
    this.newUser = this.UserForm.value;

    this.userservice
      .createAccount(this.newUser)
      .subscribe(data => {
        this.users.push(data)
      });

    //this.router.navigateByUrl('/getMembers');
    console.log(this.users);
  }

}
