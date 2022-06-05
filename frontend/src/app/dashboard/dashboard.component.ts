import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {NgForm} from "@angular/forms";
import {DashboardService} from "../../service/dashboard.service";
import { UserList } from "../../model/userlist.model";
import {delay, map} from "rxjs/operators";
import {Message} from "../../model/message.model";
import { interval, Subscription } from 'rxjs';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  loggedInUser: string;
  recipients: string[] = [];
  messages: Message[] = [];
  subscription: Subscription;

  constructor(private route: ActivatedRoute, private dashboardService: DashboardService) {
    this.loggedInUser = this.route.snapshot.params['userName'];
    const source = interval(5000);
    this.subscription = source.subscribe(val => this.fetchMessages());
  }

  ngOnInit() {
    this.dashboardService.saveUser(this.loggedInUser);
    this.fetchRecipients();
    this.fetchMessages();
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
  }

  fetchRecipients() {
    this.dashboardService.fetchRecipients().subscribe(resData => {
      for (let u of resData.users) {
        this.recipients.push(u as unknown as string);
      }
    });
  }

  fetchMessages() {
    this.dashboardService.fetchMessages(this.loggedInUser).subscribe(resData => {
      this.messages = [];
      for (let message of resData.messages) {
        this.messages.unshift(message);
      }
    });
  }

  onSendClick(form: NgForm) {
    this.dashboardService.saveMessage(this.loggedInUser, form.value.recipient, form.value.title, form.value.message);
    form.resetForm();
  }

}
