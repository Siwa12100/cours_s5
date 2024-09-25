# TP2

## Sujet

1. Sur la liste des livres, afficher la date au format `Mardi 30 octobre 1990` avec un pipe
2. Dans le service `BookService` ajouter une méthode `addBook(book: Book): void` qui va rajouter un livre dans la propriété `books`
3. Rajouter un composant `BookForm` qui :
    - sera appeler dans `app.component.html`
    - contiendra un champ pour chaque attribut d'un livre (à l'exception de `id`)
    - tous les champs sont obligatoires (utiliser un validator angular)
    - à la validation devra faire remonter au composant parent qui se charger de l'ajouter à la liste de livres via le service
4. Installer `MatAngular` sur votre projet
5. Transformer vos `input` pour utiliser `MatAngular` (utiliser un `datepicker` pour la date de publication)
6. Utiliser un `Button` issu de `MatAngular` de type `submit` pour valider le formulaire
7. Mettre en place un menu `MatAngular` composé des liens `Liste des livres` et `Ajouter un livre` (le routing n'est pas à faire pour le moment)

## Annexes

1. https://material.angular.io/
