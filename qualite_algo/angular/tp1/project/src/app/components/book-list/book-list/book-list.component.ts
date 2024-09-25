import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BookService } from '../../../services/book-service.service';
import { Book } from '../../modeles/Book';

@Component({
  selector: 'app-book-list',
  standalone: true,
  imports: [CommonModule],
  providers: [BookService],
  templateUrl: './book-list.component.html',
})
export class BookListComponent implements OnInit {
  books: Book[] = [];

  constructor(protected bookService: BookService) {}

  ngOnInit() {
    this.books = this.bookService.getAll();
  }
}
