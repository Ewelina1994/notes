import {Component, OnInit} from '@angular/core';
import {ApiService} from '../shared/api.service';
import {User} from '../model/user';
import {ActivatedRoute, Route, Router} from '@angular/router';
import {AuthService} from './auth.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginRequest: {
    name: string;
    password: string
  }
  message: any;
  errorMessage = 'Invalid Credentials';
  successMessage: string;
  invalidLogin = false;
  loginSuccess = false;
  loginForm;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private authenticationService: AuthService, private apiService: ApiService, formBuilder: FormBuilder) {
    this.loginForm = formBuilder.group({
      name: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void {
  }

  login(loginRequest: any) {
    this.loginRequest = {...this.loginForm, ...loginRequest.value};
    console.log(this.loginRequest.name);
    let res = this.authenticationService.authenticationService(this.loginRequest.name, this.loginRequest.password);
    res.subscribe(data => {
      this.loginSuccess = true;
      this.successMessage = 'Login successful';
      this.router.navigate(['note']);
    }, () => {
      this.invalidLogin = true;
      this.loginSuccess = false;
    });
  }
}
