import { Component, Output } from '@angular/core';
import { Subject } from 'rxjs';

import { Card, DeckOfCards } from '../models';

@Component({
  selector: 'app-deck-of-cards',
  templateUrl: './deck-of-cards.component.html',
  styleUrls: ['./deck-of-cards.component.css']
})
export class DeckOfCardsComponent {


  @Output()
  onCardSelection = new Subject<Card | undefined>()

  cards: DeckOfCards

  constructor() {
    this.cards = new DeckOfCards()
    this.cards.shuffle()
  }

  take() {
    const c = this.cards.take()
    console.info('>>> taken: ', c)
    this.onCardSelection.next(c)
  }

}
