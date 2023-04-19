import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { Friend } from './models';
import { FriendsComponent } from './components/friends.component';
import { FriendsListComponent } from './components/friends-list.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, AfterViewInit {

  @ViewChild(FriendsComponent)
  friendComp!: FriendsComponent

  @ViewChild(FriendsListComponent)
  friendsList!: FriendsListComponent

  selectedFriend!: Friend;

  updateMode = false

  friends: Friend[] = [
    { name: 'barney', email: 'barney@gmail.com', phone: '23456' },
    { name: 'betty', email: 'betty@gmail.com', phone: '23456' },
  ]

  ngOnInit(): void {
    console.info('>> onInit: firendComp: ', this.friendComp)
  }

  ngAfterViewInit(): void {
    console.info('>> onAfterViewInit: firendComp: ', this.friendComp)
    // this.friendsList.onSelectedFriend.subscribe(
    //   f => {
    //     console.info('in subscribe: ', f)
    //     this.selection(f)
    //   }
    // )
  }

  unFriend() {
    let idx = this.friends.findIndex(f => f.name == this.selectedFriend.name)
    if (idx == -1)
      return

    this.friends.splice(idx, 1)
    console.info('>>> deleting: ', this.friendComp.value)
    this.friendComp.clear()
  }

  process(friend: Friend) {
    let idx = this.friends.findIndex(f => f.name == friend.name)
    console.info('idx ', idx)
    if (idx == -1)
      this.friends.push(friend)
    else
      this.friends[idx] = friend
  }

  clear() {
    this.friendComp.clear()
    this.updateMode = false
  }

  selection(name: string) {
    console.info(">>> selected friend: ", name)
    const fr = this.friends.find(f => f.name == name)
    // @ts-ignore
    this.selectedFriend = fr
    // @ts-ignore
    this.friendComp.value = fr
    this.updateMode = true
    this.friendComp.readOnly = true
  }
}
