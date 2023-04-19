import { Component } from '@angular/core';
import { Friend } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  selectedFriend!: Friend;

  friends: Friend[] = [
    { name: 'barney', email: 'barney@gmail.com', phone: '23456' },
    { name: 'betty', email: 'betty@gmail.com', phone: '23456' },
  ]

  process(friend: Friend) {
    let idx = this.friends.findIndex(f => f.name == friend.name)
    console.info('idx ', idx)
    if (idx == -1)
      this.friends.push(friend)
    else
      this.friends[idx] = friend
  }

  selection(name: string) {
    console.info(">>> selected friend: ", name)
    const fr = this.friends.find(f => f.name == name)
    // @ts-ignore
    this.selectedFriend = fr
  }
}
