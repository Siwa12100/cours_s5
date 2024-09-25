import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-test-form-template',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './test-form-template.component.html',
  styleUrl: './test-form-template.component.css'
})
export class TestFormTemplateComponent {
  book = {
    title: '',
    author: ''
  };

  onSubmit() {
    console.log('Résultat du formulaire template-driven : ', this.book);
    // Envoyer un évènement au parent avec la variable remplie
  }
}
