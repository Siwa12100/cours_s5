import { Component } from '@angular/core';
import {  }

@Component({
  selector: 'app-book-list',
  standalone: true,
  imports: [],
  templateUrl: './book-list.component.html',
  styleUrl: './book-list.component.css'
})
export class BookListComponent {

  constructor(protected BookService bookService) {

  }
}

