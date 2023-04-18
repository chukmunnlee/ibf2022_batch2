export interface Task {
  description: string
  priority: string
}

export interface Todo {
  name: string
  dueDate: Date
  tasks: Task[]
}
