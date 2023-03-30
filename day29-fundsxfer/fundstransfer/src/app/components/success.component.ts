import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-success',
  templateUrl: './success.component.html',
  styleUrls: ['./success.component.css']
})
export class SuccessComponent implements OnInit {

	transactionId = ""

	constructor(private router: Router, private activatedRoute: ActivatedRoute) { }

	ngOnInit(): void {

		this.transactionId = this.activatedRoute.snapshot.params['txId']
		
	}

}
