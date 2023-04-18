import { Component } from '@angular/core';
import { DeliveryOrder } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  orders: DeliveryOrder[] = []

  processNewOrder(order: DeliveryOrder) {
    this.orders.unshift(order)
  }
}
