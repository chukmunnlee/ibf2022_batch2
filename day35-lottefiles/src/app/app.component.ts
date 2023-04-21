import { Component, OnInit } from '@angular/core';
import { AnimationOptions } from 'ngx-lottie';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  options!: AnimationOptions

  ngOnInit(): void {

    this.options = {
      path: '/assets/eid-mubarak.json',
    }

  }
}
