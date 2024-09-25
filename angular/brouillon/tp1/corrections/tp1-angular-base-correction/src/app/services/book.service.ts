import { Injectable } from "@angular/core";
import { BOOKS } from "../datas/books.stub";
import { Book } from "../models/book.model";

@Injectable()
export class BookService {
    private books: Book[];

    constructor(){
        this.books = BOOKS;
    }

    getAllBooks(): Book[]{
        return this.books;
    }
}