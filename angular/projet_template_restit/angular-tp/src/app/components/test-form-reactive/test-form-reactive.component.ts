import { Component, EventEmitter, Output } from '@angular/core';
import { FormControl, FormBuilder, ReactiveFormsModule, FormGroup, Validators } from '@angular/forms';
import { Book } from '../../models/book.model';

@Component({
  selector: 'app-test-form-reactive',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './test-form-reactive.component.html',
  styleUrl: './test-form-reactive.component.css'
})
export class TestFormReactiveComponent {
  @Output() addBookEvent = new EventEmitter<Book>();

  constructor(private fb: FormBuilder) { 
  }

  book: Book = { id: 0, title: '', author: '', publicationDate: new Date() }
  bookForm: FormGroup = this.fb.group({
    title: ['', [Validators.required]],
    author: ['', [Validators.required]],
    publicationDate: ['', [Validators.required]]
  });

  //VARIANTE POSSIBLE AVEC FORMGROUP
  /*bookForm: FormGroup = new FormGroup({
    title: new FormControl(this.book.title, Validators.required),
    author: new FormControl(this.book.author, Validators.required),
    publicationDate: new FormControl(this.book.publicationDate, Validators.required)
  });*/

  addBook() {
    if(this.bookForm.invalid) {
      console.log('Le formulaire reactive-driven est mal rempli');
      return;
    }

    this.book = this.bookForm.value;
    console.log('Résultat du formulaire reactive-driven : ', this.book);
    // Envoyer un évènement au parent avec la variable remplie
  }
}
