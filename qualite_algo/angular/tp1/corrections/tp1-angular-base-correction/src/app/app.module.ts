import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BookListComponent } from './components/book-list/book-list.component';
import { CompSiwa } from './components/comp-siwa/comp-siwa.component';

@NgModule({
  declarations: [
    AppComponent,
    BookListComponent,
    CompSiwa
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
