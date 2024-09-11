import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { TestFormTemplateComponent } from '/home/scratch/marestit/javascript/angular-tp/src/app/components/test-form-template/test-form-template.component';
import { TestFormReactiveComponent } from '/home/scratch/marestit/javascript/angular-tp/src/app/components/test-form-reactive/test-form-reactive.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, TestFormTemplateComponent, TestFormReactiveComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'angular-tp1';
}
