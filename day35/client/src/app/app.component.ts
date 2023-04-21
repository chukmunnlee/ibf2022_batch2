import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UppercaseService } from './services/uppercase.service';
import { UppercaseResponse } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  form!: FormGroup
  response$!: Promise<UppercaseResponse>

  constructor(private uppercaseSvc: UppercaseService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      method: this.fb.control<number>(0, [ Validators.required ]),
      message: this.fb.control<string>('', [ Validators.required ])
    })
  }

  process() {
    const submit = this.form.value;
    submit['method'] = parseInt(submit['method'])

    switch (submit['method']) {
      case 0: // GET
        this.response$ = this.uppercaseSvc.getUppercase(submit['message'])
        break

      case 1: // POST with JSON
        this.response$ = this.uppercaseSvc.postUppercaseAsJson(submit['message'])
        break

      case 2: // POST with url encoded form
        this.response$ = this.uppercaseSvc.postUppercaseAsForm(submit['message'])
        break

      default:
    }
  }
}
