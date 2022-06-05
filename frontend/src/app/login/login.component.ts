import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {Form, NgForm} from "@angular/forms";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  onBtnClick(form: NgForm) {
    this.router.navigateByUrl('/dashboard/' + form.value.userName);
  }

}
