import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BookServiceService {

  private Books : Book[] = [];
  constructor() { }
}
