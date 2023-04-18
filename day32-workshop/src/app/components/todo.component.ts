import { Component, Input, OnInit, Output } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Todo } from '../models';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {

  @Output()
  onNewTodo = new Subject<Todo>()

  @Input()
  todo!: Todo

  todoForm!: FormGroup
  taskArray!: FormArray

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    console.info('restoring todo: ', this.todo)
    this.todoForm = this.createTodoForm()
  }

  processTodo() {
    const t: Todo = this.todoForm.value
    t.dueDate = new Date(this.todoForm.get('dueDate')?.value)
    this.todoForm = this.createTodoForm()
    this.onNewTodo.next(t)
  }

  addTask() {
    this.taskArray.push(this.createTaskGroup())
  }
  removeTask(idx: number) {
    this.taskArray.removeAt(idx)
  }

  invalid(): boolean {
    return this.todoForm.invalid || this.taskArray.length <= 0
  }

  private createTaskGroup(): FormGroup {
    return this.fb.group({
      description: this.fb.control<string>('', [ Validators.required ]),
      priority: this.fb.control<string>('lo')
    })
  }

  private createTodoForm(): FormGroup {
    this.taskArray = this.fb.array([])
    return this.fb.group({
      name: this.fb.control<string>('', [ Validators.required ]),
      dueDate: this.fb.control<string>('', [ Validators.required ]),
      tasks: this.taskArray
    })
  }

}
