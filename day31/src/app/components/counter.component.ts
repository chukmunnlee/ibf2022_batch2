import { Component, Input, Output } from '@angular/core';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-counter',
  templateUrl: './counter.component.html',
  styleUrls: ['./counter.component.css']
})
export class CounterComponent {

  @Input()
  counter = 0

  @Output()
  onValueChanged = new Subject<number>()

  dec() {
    this.count(-1)
  }

  inc() {
    this.count(1)
  }

  count(delta: number) {
    this.counter += delta
    // notify our subscribers
    this.onValueChanged.next(this.counter)
  }

}
