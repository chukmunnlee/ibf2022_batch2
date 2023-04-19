import { Component, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Friend } from '../models';
import { Subject, debounceTime } from 'rxjs';

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

  readOnly = false

  form!: FormGroup

  get value(): Friend {
    return this.form.value as Friend
  }
  set value(f: Friend) {
    console.info('>>> set value: ', f)
    this.initForm(f)
  }

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.initForm(this.friend)
    console.info('friend: ', this.friend)
  }

  ngOnChanges(changes: SimpleChanges): void {
    // const fr = changes['friend'].currentValue as Friend
    // console.info('******* changes: ', changes)
    // this.initForm(fr)
  }

  initForm(friend: Friend | null = null) {
    this.form = this.createForm(friend)
    this.readOnly = true
    /*
    this.form.statusChanges.subscribe(
      v => {
        console.info('status changes: ', v)
      }
    )
    this.form.valueChanges
      .pipe(
        debounceTime(300)
      )
      .subscribe(
      v => {
        console.info('value changes: ', v['name'])
      }
    )
    */
  }

  clear() {
    this.initForm()
  }

  // value(): Friend {
  //   return this.form.value as Friend
  // }

  processForm() {
    const fr: Friend = this.form.value
    console.info('>>> fr: ', fr)
    this.onFriend.next(fr)
    this.form.reset()
  }

  private createForm(friend: Friend | null = null): FormGroup {
    console.info('>>> create form: ', friend)
    return this.fb.group({
      name: this.fb.control(friend? friend.name: '', [ Validators.required ]),
      phone: this.fb.control(friend? friend.phone: '', [ Validators.required ]),
      email: this.fb.control(friend? friend.email: '', [ Validators.required, Validators.email ]),
    })
  }

}
