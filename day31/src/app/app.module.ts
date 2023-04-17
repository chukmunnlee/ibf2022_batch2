import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { MyComponentComponent } from './components/my-component.component';
import { CounterComponent } from './components/counter.component';

@NgModule({
  declarations: [
    AppComponent,
    MyComponentComponent,
    CounterComponent
  ],
  imports: [ BrowserModule ],
  exports: [ MyComponentComponent ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
