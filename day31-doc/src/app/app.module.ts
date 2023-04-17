import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { DeckOfCardsComponent } from './components/deck-of-cards.component';
import { PlayerComponent } from './components/player.component';

@NgModule({
  declarations: [
    AppComponent,
    DeckOfCardsComponent,
    PlayerComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
