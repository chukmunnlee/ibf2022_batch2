import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { FriendsComponent } from './components/friends.component';
import { FriendsListComponent } from './components/friends-list.component';

@NgModule({
  declarations: [
    AppComponent,
    FriendsComponent,
    FriendsListComponent
  ],
  imports: [
    BrowserModule, ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
