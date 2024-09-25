import { Component, NgModule } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ListeLivresComponent } from '../listeLivres/liste-livres/liste-livres.component';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, ListeLivresComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  titre = 'Application Siwa';
}
