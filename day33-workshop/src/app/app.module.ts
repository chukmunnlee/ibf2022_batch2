import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms'

import { AppComponent } from './app.component';
import { CodeMakerComponent } from './components/code-maker.component';
import { CodeBreakerComponent } from './components/code-breaker.component';

@NgModule({
  declarations: [
    AppComponent,
    CodeMakerComponent,
    CodeBreakerComponent
  ],
  imports: [
    BrowserModule, ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
