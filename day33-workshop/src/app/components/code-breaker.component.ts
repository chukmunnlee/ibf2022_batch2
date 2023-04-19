import { Component, OnInit } from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from '@angular/forms';

import { COLOURS } from '../models'

@Component({
  selector: 'app-code-breaker',
  templateUrl: './code-breaker.component.html',
  styleUrls: ['./code-breaker.component.css']
})
export class CodeBreakerComponent implements OnInit {

	form!: FormGroup
	allRows!: FormArray
	currentRow!: FormGroup

	guesses: string[][] = []

	readonly COLOURS = COLOURS

	constructor(private fb: FormBuilder) { }

	ngOnInit(): void {
		this.intializeBoard()
	}

	intializeBoard() {
		this.form = this.fb.group({
			colour0: this.fb.control('', [ Validators.required ]),
			colour1: this.fb.control('', [ Validators.required ]),
			colour2: this.fb.control('', [ Validators.required ]),
			colour3: this.fb.control('', [ Validators.required ])
		})
	}

	invalid() {
		return this.form.invalid
	}

	commit(): string[] {
		const guess: string[] = []
		for (let i = 0; i < 4; i++) {
			const ci = parseInt(this.form.value[`colour${i}`])
			guess.push(this.COLOURS[ci])
		}
		this.guesses.push(guess)
		this.form.reset()
		return guess
	}

}
