import { Component } from '@angular/core';

@Component({
  selector: 'app-code-maker',
  templateUrl: './code-maker.component.html',
  styleUrls: ['./code-maker.component.css']
})
export class CodeMakerComponent {

	private _code: string[] = []

	revalCode = false

	get codes(): string[] {
		return this._code
	}

	setCodes(c: string[]) {
		this._code = c
	}

	showCodes() {
		this.revalCode = !this.revalCode
	}

}
