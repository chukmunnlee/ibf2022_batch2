import { Component, OnInit, Output } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DeliveryOrder } from '../models';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-delivery',
  templateUrl: './delivery.component.html',
  styleUrls: ['./delivery.component.css']
})
export class DeliveryComponent implements OnInit {

  doForm!: FormGroup
  itemArray!: FormArray

  @Output()
  onNewDeliveryOrder = new Subject<DeliveryOrder>()

  priorityCtrlName = "priority"

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
     // this method is called when the component is created
     // for initialization
     this.doForm = this.createForm()
  }

  processDelivery() {
    const delivery = this.doForm.value as DeliveryOrder
    this.onNewDeliveryOrder.next(delivery)
    console.info('>>>> delivery: ', delivery)
    this.ngOnInit()
  }

  addItem() {
    const orderItem = this.createOrderItem()
    this.itemArray.push(orderItem)
  }

  deleteItem(idx: number) {
    this.itemArray.removeAt(idx)
  }

  hasError(cn: string): boolean {
    return !!(this.doForm.get(cn)?.invalid && this.doForm.get(cn)?.dirty)
  }

  isFormInvalid(): boolean {
    const dd = this.doForm.get('deliveryDate')?.value
    if (!dd)
      return true

    const deliveryDate = new Date(dd)
    const today = new Date()
    return this.doForm.invalid && (deliveryDate < today)
  }

  private createForm(): FormGroup {
    this.itemArray = this.fb.array([])
    return this.fb.group({
      name: this.fb.control<string>('', [ Validators.required, Validators.minLength(3) ]),
      address: this.fb.control<string>('1 bedrock', [ Validators.required ]),
      email: this.fb.control<string>('fred@gmail.com', [ Validators.required, Validators.email ]),
      session: this.fb.control<string>('PM', [ Validators.required ]),
      insurance: this.fb.control<boolean>(false),
      priority: this.fb.control<boolean>(false),
      deliveryDate: this.fb.control<string>('', [ Validators.required ]),
      comments: this.fb.control<string>('no comments'),
      orderItems: this.itemArray
    })
  }

  private createOrderItem(): FormGroup {
    return this.fb.group({
      item: this.fb.control<string>('', [ Validators.required ]),
      quantity: this.fb.control<number>(1, [ Validators.required, Validators.min(1) ]),
    })
  }

}
