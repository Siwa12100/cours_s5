package main.jeanmarcillac.clis;

import java.util.Scanner;

import main.jeanmarcillac.LivresService.ILivreService;
import main.jeanmarcillac.modeles.Livre;

public class EditeurCli {
    
    protected Scanner scanner;
    protected ILivreService livreService;

    public EditeurCli(Scanner scanner, ILivreService livreService) {
        this.scanner = scanner;
        this.livreService = livreService;
    }

    public void nettoyerTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void menuEditeur() {

        this.nettoyerTerminal(); 
        System.out.println("=========================");
        System.out.println("Menu d'edition des livres");
        System.out.println("=========================");
        System.out.println("1.) Ajouter un livre.");
        System.out.println("2.) Supprimer un livre.");
        System.out.println("3.) Afficher tous les livres.");
        System.out.println("4.) Retour menu principal.");
        System.out.print("\n---> Votre reponse : ");

        int reponse = Integer.parseInt(scanner.nextLine());   
        this.nettoyerTerminal(); 
        switch(reponse) {

            case 1 : this.menuAjoutLivre();
            break;

            case 2 : this.menuSuppressionLivre();
            break;

            case 3 : this.menuAffichageLivres();
            break;

            case 4 : System.out.println("\n[Infos] : fin du programme.");
            break;

            default : System.out.println("[Probleme] : entree inconnue.");
        }
    }

    protected void menuAffichageLivres() {

        System.out.println("-----------------------------------");
        System.out.println("Liste des livres de la bibliotheque");
        System.out.println("-----------------------------------\n");

        this.livreService.recupererTousLesLivres().forEach(livre -> {

            System.out.println(" * " + livre + "\n");
        });

        this.faireAvancerUtilisateur();
        this.menuEditeur();
    }

    protected void menuSuppressionLivre() {

        System.out.println("-----------------------");
        System.out.println("Suppression d'un livre");
        System.out.println("-----------------------\n");

        System.out.println("- - - - - - Tous les livres : - - - - -");
        this.livreService.recupererTousLesLivres().forEach(livre -> {

            System.out.println(" * Id : " + livre.getId() + "| Titre : " + livre.getTitre() + ".\n");
        });

        System.out.print("\n---> Id du livre a supprimer : ");
        int reponse = Integer.parseInt(scanner.nextLine());
        boolean resultat = this.livreService.supprimerLivre(reponse);

        if (resultat) {
            System.out.println("\n[Infos] : Le livre d'id " + reponse + " bien supprime.");
        } else {
            System.out.println("\n[Erreur] : Aucun livre trouvé avec l'id " + reponse + ".");
        }

        this.faireAvancerUtilisateur();
        this.menuEditeur();
    }

    protected void menuAjoutLivre() {

        System.out.println("----------------");
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

        Livre nouveauLivre = new Livre(isbn, titre, auteur, nature, editeur, nbCopies);
        this.livreService.ajouterLivre(nouveauLivre);

        System.out.println("\n[Infos] : Livre suivant bien ajoute : " + titre + ".");
        this.faireAvancerUtilisateur();
        this.menuEditeur();
    }

    protected void faireAvancerUtilisateur() {
        System.out.println("\n---> Appuyez sur ENTRER pour continuer...");
        this.scanner.nextLine();
    }
}
