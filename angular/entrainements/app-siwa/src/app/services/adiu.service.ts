import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdiuService {

  constructor() { }

  direAdiu() : string {
    return "Adiu !";
  }
}
