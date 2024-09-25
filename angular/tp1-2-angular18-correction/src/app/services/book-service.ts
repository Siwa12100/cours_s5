import { Injectable } from "@angular/core";
import { BOOKS } from "../datas/book.stub";
import { Book } from '../models/book.model';

@Injectable()
export class BookService {
    private books: Book[];

    constructor(){
        this.books = BOOKS;
    }

    getAll(): Book[]{
        return this.books;
    }    

    addBook(book: Book): void{
        if(book.id === 0){
            book.id = Math.max(...this.books.map(b => b.id)) + 1;
        }

        this.books.push(book);
    }

    getBook(id: number): Book | undefined{
      const book = this.books.find(b => b.id == id);console.log('Fetching book with ID:', id);
      console.log(this.books);
      return book;
    }
}