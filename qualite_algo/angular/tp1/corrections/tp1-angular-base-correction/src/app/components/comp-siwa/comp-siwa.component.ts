import { Component } from '@angular/core';
import { BookService } from 'src/app/services/book.service';
import { Book } from 'src/app/models/book.model';

@Component({

    selector:"comp-siwa",
    templateUrl:"./comp-siwa.component.html",
    providers:[BookService]
})
export class CompSiwa {

    message : string = "Gardarem la memoria.";
    yooooooouh : string = "yooooooouhaaaaa";
    livres? : Book[];

    constructor(protected service : BookService ) {

    }

    ngOnInit() {

        if (this.livres == null) {
            this.livres = this.service.getAllBooks();
        }
    }

    clication(msg : string) {

        console.log("Bouton clique !!! -----> " + msg);
    }
}