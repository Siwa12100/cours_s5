import { Injectable } from '@angular/core';
import { Book } from '../models/Book';
import { BOOKS } from '../data/books.stub';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  private books: Book[] = BOOKS;

  constructor() {}

  getAll(): Book[] {
    return this.books;
  }
}
