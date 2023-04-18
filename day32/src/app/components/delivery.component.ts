import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-delivery',
  templateUrl: './delivery.component.html',
  styleUrls: ['./delivery.component.css']
})
export class DeliveryComponent implements OnInit {

  doForm!: FormGroup

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
     // this method is called when the component is created
     // for initialization
     this.doForm = this.createForm()
  }

  processDelivery() {
    const delivery = this.doForm.value
    console.info('>>> delivery: ', delivery)
  }

  private createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control<string>(''),
      address: this.fb.control<string>(''),
      email: this.fb.control<string>(''),
      deliveryDate: this.fb.control<string>(''),
      comments: this.fb.control<string>('')
    })
  }

}
