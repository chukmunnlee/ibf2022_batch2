import { Component, Input, Output } from '@angular/core';
import { Card, Player } from '../models';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-player',
  templateUrl: './player.component.html',
  styleUrls: ['./player.component.css']
})
export class PlayerComponent {

  @Input()
  player!: Player

  @Output()
  onDrawCard = new Subject<string>()

  drawACard() {
    console.info(`name: ${this.player.name}`)
    this.onDrawCard.next(this.player.name)
  }

}
