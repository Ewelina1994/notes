import {Component, OnInit} from '@angular/core';
import {ApiService} from "../shared/api.service";
import {User} from "../notes/model/user";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: User = {
    id: null,
    name: null,
    password: null
  };

  constructor(private apiService: ApiService) {
  }

  ngOnInit(): void {
  }

  login() {
    console.log(this.user.name, this.user.password);
    this.apiService.login(this.user).subscribe(
      res => {

      },
      err => {
        alert("Nie udało się przesłac dane do zalogowania");
      }
    )
  }
}
