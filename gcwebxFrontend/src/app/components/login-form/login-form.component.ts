import {ApplicationRef, ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Router} from '@angular/router';
import {User} from '../../model/user';
import {UserService} from '../../services/user.service';
import {HttpClient} from '@angular/common/http';
import {AuthenticationService} from '../../services/authentication.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {


  constructor(private router: Router,
              private authenticationService: AuthenticationService,
              private http: HttpClient,
              private changeDetectorRef: ChangeDetectorRef) { }

  credential: User;
  successfulLogin = true;

  UserForm = new FormGroup({
    username: new FormControl(),
    password: new FormControl()

  });

  ngOnInit(): void {
  }

  logIn() {
    this.credential = this.UserForm.value;
    console.log(this.credential);
    this.authenticationService.authenticate(this.credential, () => {
      this.successfulLogin = true;
      this.router.navigateByUrl('/home');
      this.authenticationService.getRole();
    });
    this.successfulLogin = false;
    return false;
  }

  onEnterPressed() {
    console.log("Enter pressed");
    this.logIn();
  }

  onSubmitClicked() {
    console.log("Button clicked");
    this.logIn();
  }


}
