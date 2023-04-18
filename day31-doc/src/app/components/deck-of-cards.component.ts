import { Component, Input, Output } from '@angular/core';
import { Subject } from 'rxjs';

import { Card, DeckOfCards } from '../models';

@Component({
  selector: 'app-deck-of-cards',
  templateUrl: './deck-of-cards.component.html',
  styleUrls: ['./deck-of-cards.component.css']
})
export class DeckOfCardsComponent {

  @Input()
  remainingCards = 0

  @Output()
  onCardSelection = new Subject<void>()

  cards: DeckOfCards

  constructor() {
    this.cards = new DeckOfCards()
    this.cards.shuffle()
  }

  take() {
    this.onCardSelection.next()
  }

}
