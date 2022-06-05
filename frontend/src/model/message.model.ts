import {User} from "./user.model";

export interface Message {
  sender: User,
  receiver: User,
  title: string,
  body: string
}
