# TP1

**ATTENTION la correction a été faite en Angular 16, il y a donc le fichier `app.module.ts`**

## Sujet du TP1

1. Création d'une application Angular via la CLI
2. Mise en place d'un modèle en TypeSript `Book`
  - composé d'un `id` (type `number`), `title` (type `string`), `author` (type `string`), `publicationDate` (type `Datetime`)
3. Mise en place d'un stub JSON contenant 3 livres
4. Création d'un service `BookService`
  - qui chargera dans une propriété `books: Book[]` les livres issues du stub JSON
  - qui contiendra une méthode `getAll(): Book[]`
5. Création d'un composant `book-list` qui affichera l'ensemble des livres
6. BONUS : création d'un composant `book-form` permetant d'ajouter un formulaire dans la liste des livres présents dans le service

A noter, que pour la création des composants il n'est pas nécessaire de générer le fichier `.spec.ts` ni le fichier `.css` ou `.scss`

## Correction

1. Exécuter la commande `ng new tp1` (pour les options ne pas prendre le routing et sélectionner le format scss)
2. Dans le dossier `app` créer un dossier `models` et créer un fichier `book.model.ts`
3. Dans le dossier `app` créer un dossier `datas` et créer un fichier `books.stub.ts` qui contiendra une `const`
4. Dans le dossier `app` créer un dossier `services` et créer un fichier `book.service.ts`
  - Le décorateur `@Injectable()` permettra d'injecter le service dans les différents composants
5. Créer un composant via la commande `ng generate component --skip-tests=true --inline-style book-list` qui permettra de générer le composant sans le fichier `.spec.ts` et sans le fichier `scss`
6. La question bonus sera traité + en profondeur dans le TP2

## Bonnes pratiques

1. Toujours préciser le type de retour d'une méthode
2. Ne jamais utiliser le type `any`
3. Favoriser les interfaces pour les modèles/dto
4. Respecter l'arborescence de fichiers
5. Ne jamais commit ou distribuer le dossier `node_modules`, il faut l'ignorer via le `.gitignore`