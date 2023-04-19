import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import {CodeBreakerComponent} from './components/code-breaker.component';
import {CodeMakerComponent} from './components/code-maker.component';
import {generateCode} from './mastermind';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, AfterViewInit {

	@ViewChild(CodeMakerComponent)
	codeMaker!: CodeMakerComponent

	@ViewChild(CodeBreakerComponent)
	codeBreaker!: CodeBreakerComponent

	code: string[] = []

	ngOnInit(): void {
		this.code = generateCode()
		console.info('>>> code: ', this.code)
	}

	ngAfterViewInit(): void {
		this.codeMaker.setCodes(this.code)
	}

	guessCode() {
		const g = this.codeBreaker.commit()
		console.info(g)
	}

	invalid() {
		return !this.codeBreaker || this.codeBreaker.invalid()
	}
}
