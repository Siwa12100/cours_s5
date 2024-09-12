package clis;

import java.util.Scanner;

import modeles.Livre;

public class EditeurCli {
    
    protected Scanner scanner;

    public EditeurCli(Scanner scanner) {
        this.scanner = scanner;
    }

    protected void nettoyerTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }



    public void menuEditeur() {

        System.out.println("=========================");
        System.out.println("Menu d'edition des livres");
        System.out.println("=========================");

        System.out.println("1.) Ajouter un livre.");
        System.out.println("2.) Modifier un livre.");
        System.out.println("3.) Supprimer un livre.");
        System.out.println("4.) Modifier un livre");
        System.out.println("5.) Afficher tous les livres.");
        System.out.print("\n---> Votre reponse : ");

        int reponse = Integer.parseInt(scanner.nextLine());
        System.out.println("\nres : " + reponse );

        this.nettoyerTerminal();  
         

        switch(reponse) {

            case 1 : this.menuAjoutLivre();
            break;

            default : System.out.println("[Probleme] : entree inconnue.");
        }

    }

    protected void menuAjoutLivre() {

        System.out.println("Ajout d'un livre");
        System.out.println("----------------");

        System.out.print("---> Titre du livre : ");
        String titre = scanner.nextLine();

        System.out.print("---> Auteur du livre : ");
        String auteur = scanner.nextLine();

        System.out.print("---> Nature du livre : ");
        String nature = scanner.nextLine();

        System.out.print("---> Editeur du livre : ");
        String editeur = scanner.nextLine();

        System.out.print("---> Isbn du livre (<= 0) : ");
        int isbn = Integer.parseInt(scanner.nextLine());

        System.out.print("Nombre de copies du livre (<= 0) : ");
        int nbCopies = Integer.parseInt(scanner.nextLine());

        Livre nouveauLivre = new Livre(1, isbn, titre, auteur, nature, editeur, nbCopies);
        System.out.println("\n\n " + nouveauLivre);

        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }
}
