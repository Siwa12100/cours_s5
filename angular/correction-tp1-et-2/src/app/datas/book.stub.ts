import { Book } from "../models/book.model";

export const BOOKS: Book[] = [
    { id: 1, title: 'The Lord of the Rings - The Fellowship of the Ring', author: 'J.R.R. Tolkien', publicationDate: new Date('07/29/1954') },
    { id: 2, title: 'The Lord of the Rings - The Two Towers', author: 'J.R.R. Tolkien', publicationDate: new Date('11/11/1954') },
    { id: 3, title: 'The Lord of the Rings - The Return of the King', author: 'J.R.R. Tolkien', publicationDate: new Date('10/20/1955') },
    { id: 4, title: 'Dune', author: 'Frank Herbert', publicationDate: new Date('1965') },
    { id: 5, title: 'Dune Messiah', author: 'Frank Herbert', publicationDate: new Date('1969') },
    { id: 6, title: 'It', author: 'Stephen King', publicationDate: new Date('09/15/1986') },
    { id: 7, title: 'Do Androids Dream of Electric Sheep?', author: 'Philip K. Dick', publicationDate: new Date('1968') }
];