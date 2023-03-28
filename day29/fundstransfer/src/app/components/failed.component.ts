import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-failed',
  templateUrl: './failed.component.html',
  styleUrls: ['./failed.component.css']
})
export class FailedComponent implements OnInit {

	error: any = {}

	constructor(private activatedRoute: ActivatedRoute) { }

	ngOnInit(): void {
		this.error = JSON.parse(this.activatedRoute.snapshot.queryParams['error'])['error']
	}

}
