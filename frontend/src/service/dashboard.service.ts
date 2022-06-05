import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {UserList} from "../model/userlist.model";
import {Message} from "../model/message.model";
import {MessageList} from "../model/messagelist.model";
import { environment } from "../environments/environment";

@Injectable({providedIn: 'root'})
export class DashboardService {

  constructor(private http: HttpClient) {
  }

  saveUser(loggedInUser: string) {
    let user = {
      "name": loggedInUser
    };
    this.http.post(environment.server_path + '/user', user).subscribe();
  }

  fetchRecipients() {
    return this.http.get<UserList>(environment.server_path + '/users');
  }

  saveMessage(from: string, to: string, title: string, body: string) {
    let message = {
      "sender": from,
      "receiver": to,
      "title": title,
      "body": body
    }
    this.http.post(environment.server_path + '/message', message).subscribe();
  }

  fetchMessages(loggedInUser: string) {
    let queryParams = new HttpParams().append("userName", loggedInUser);
    return this.http.get<MessageList>(environment.server_path + '/messages', {params: queryParams} );
  }

}
