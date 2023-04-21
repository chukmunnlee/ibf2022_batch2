import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { LottieModule  } from 'ngx-lottie';
import player from 'lottie-web'

import { AppComponent } from './app.component';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    LottieModule.forRoot({ player: () => player })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
