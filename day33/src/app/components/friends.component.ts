import { Component, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Friend } from '../models';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-friends',
  templateUrl: './friends.component.html',
  styleUrls: ['./friends.component.css']
})
export class FriendsComponent implements OnInit, OnChanges {

  @Output()
  onFriend = new Subject<Friend>()

  @Input()
  friend!: Friend

  form!: FormGroup

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.createForm(this.friend)
    console.info('friend: ', this.friend)
  }

  ngOnChanges(changes: SimpleChanges): void {
    const fr = changes['friend'].currentValue as Friend
    this.form = this.createForm(fr)
  }

  processForm() {
    const fr: Friend = this.form.value
    console.info('>>> fr: ', fr)
    this.onFriend.next(fr)
    this.form.reset()
  }

  private createForm(friend: Friend): FormGroup {
    return this.fb.group({
      name: this.fb.control(friend? friend.name: '', [ Validators.required ]),
      phone: this.fb.control(friend? friend.phone: '', [ Validators.required ]),
      email: this.fb.control(friend? friend.email: '', [ Validators.required, Validators.email ]),
    })
  }

}
