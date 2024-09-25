import { Component, OnInit } from '@angular/core';
import { Livre } from '../../../modeles/Livre';
import { LivreService } from '../../../services/livre.service';

@Component({
  selector: 'app-liste-livres',
  standalone: true,
  imports: [],
  templateUrl: './liste-livres.component.html',
  styles: ``
})
export class ListeLivresComponent implements OnInit {

  livres : Livre[]  = [];

  constructor(private livreService : LivreService) {}

  ngOnInit(): void {

    this.livreService.recupererTousLesLivres().subscribe(donnees => {
      this.livres = donnees;
    });
  }
}
