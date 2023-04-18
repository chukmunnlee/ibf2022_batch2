import { Component, OnInit } from '@angular/core';
import { Todo } from './models';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  todo!: Todo

  ngOnInit(): void {
      const d = localStorage.getItem('todo')
      if (!d)
        return
      // @ts-ignore
      const t: any = JSON.parse(d)
      t['dueDate'] = new Date(t['dueDate'])
      this.todo = {...t}
      console.info('>>> t: ', this.todo)
  }

  processTodo(todo: Todo) {
    console.info('>>>> todo: ', todo)
    localStorage.setItem('todo', JSON.stringify(todo))
  }
}
