import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { BookFormComponent } from './components/book-form/book-form.component';
import { BookListComponent } from './components/book-list/book-list.component';
import { BookMenuComponent } from './components/book-menu/book-menu.component';


import { BookService } from './services/book-service';
import { Book } from './models/book.model';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, BookListComponent, BookFormComponent, BookMenuComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
  providers: [
    BookService
  ]
})
export class AppComponent {
  title = 'angular-tp2-correct';

  constructor(protected bookService: BookService){ }
  
  addBook($event: Book): void {
    this.bookService.addBook($event);
  }
}
