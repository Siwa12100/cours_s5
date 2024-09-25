# TP2

## Sujet

1. Sur la liste des livres, afficher la date au format `Mardi 30 octobre 1990` avec un pipe
2. Dans le service `BookService` ajouter une méthode `addBook(book: Book): void` qui va rajouter un livre dans la propriété `books`
3. Rajouter un composant `BookForm` qui :
    - sera appeler dans `app.component.html`
    - contiendra un champ pour chaque attribut d'un livre (à l'exception de `id`)
    - tous les champs sont obligatoires (utiliser un validator angular)
    - à la validation devra faire remonter au composant parent qui se charger de l'ajouter à la liste de livres via le service (calculer l'id en se basant sur l'id max de votre collection de livres)
4. Installer `MatAngular` sur votre projet
5. Transformer vos `input` pour utiliser `MatAngular` (utiliser un `datepicker` pour la date de publication)
6. Utiliser un `Button` issu de `MatAngular` de type `submit` pour valider le formulaire
7. Mettre en place un menu `MatAngular` composé des liens `Liste des livres` et `Ajouter un livre` (le routing n'est pas à faire pour le moment)

## Correction

1. Afin de formater l'affichage d'une date, il faut utiliser un Datepipe de cette façon : `| date:'EEEE dd MMMM yyyy'`
2. Voir la méthode directement dans la classe `BookService` présente dans le fichier `book.service.ts`
3. Afin de rajouter un composant il suffit d'exécuter la commande suivante : `ng generate component --skip-tests=true --inline-style book-form`
4. Pour installer `MatAngular` sur votre projet il suffit d'exécuter la commande suivante : `ng add @angular/material`
5. Entourer l'`input` d'une balise `mat-form-field`, lui rajouter l'attribut `matInput` et transformer le `label` en `mat-label`
6. Ajout des attributs `mat-flat-button color="primary"` sur les balises `button`
7. Utilisation d'un élément `mat-menu` (voir documentation en annexe)

## Remarques
1. Les dates sont affichés au format français via des modifications dans les fichiers `main.ts` et `app.config.ts`**

## Bonnes pratiques

1. Toujours préciser le type de retour d'une méthode
2. Ne jamais utiliser le type `any`
3. Favoriser les interfaces pour les modèles/dto

## Annexes

1. input : https://material.angular.io/components/input/overview
2. button : https://material.angular.io/components/button/overview
3. menu : https://material.angular.io/components/menu/overview
