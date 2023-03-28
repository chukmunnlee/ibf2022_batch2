import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Params, Router} from '@angular/router';
import {FundsTransferService} from '../fundstransfer.service';
import {Transfer} from '../models';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

	form!: FormGroup

	constructor(private fb: FormBuilder, private router: Router
			, private transferSvc: FundsTransferService) { }

	ngOnInit(): void {
		this.form = this.createForm()
	}

	process() {
		const transfer: Transfer = this.form.value as Transfer
		this.transferSvc.transfer(transfer)
			.then(result => {
				this.router.navigate([ '/success', result.transactionId ])
			})
			.catch(error => {
				const queryParams: Params = { error: JSON.stringify(error) }
				this.router.navigate([ '/failed' ], { queryParams })
			})
	}

	private createForm(): FormGroup {
		return this.fb.group({
			srcAcct: this.fb.control<string>('', [ Validators.required, Validators.minLength(2) ]),
			destAcct: this.fb.control<string>('', [ Validators.required, Validators.minLength(2) ]),
			amount: this.fb.control<number>(1, [ Validators.required, Validators.min(1) ])
		})
	}

}
