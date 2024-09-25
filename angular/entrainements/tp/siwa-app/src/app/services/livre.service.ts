import { Injectable } from '@angular/core';
import { Livre } from '../modeles/Livre';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LivreService {

  private cheminVersLivres = 'assets/Livres.json';

  constructor(private http : HttpClient) {

  }

  recupererTousLesLivres() : Observable<Livre[]> {
    return this.http.get<Livre[]>(this.cheminVersLivres);
  }
}
