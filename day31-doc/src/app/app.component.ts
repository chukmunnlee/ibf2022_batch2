import { Component, OnInit } from '@angular/core';
import { Card, DeckOfCards } from './models';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  players = ['fred', 'barney', 'betty']

  playerHand: Card[] = []

  newCard(card: Card | undefined) {
    console.info(">>>> c: ", card)
    // @ts-ignore
    this.playerHand.push(card)

  }
}
