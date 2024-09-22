package jeanmarcillac.clis;

import java.util.List;
import java.util.Scanner;

import jeanmarcillac.AbonnementService.IAbonnementService;
import jeanmarcillac.ClientService.IClientService;
import jeanmarcillac.LivresService.ILivreService;
import jeanmarcillac.modeles.Livre;

public class ClientCli {
    
    protected ILivreService livreService;
    protected IClientService clientService;
    protected IAbonnementService abonnementService;
    protected Scanner scanner;
    protected int idClient = 0;

    public ClientCli(Scanner scanner, ILivreService livreService, IClientService clientService, IAbonnementService abonnementService) {
        this.livreService = livreService;
        this.scanner = scanner;
        this.clientService = clientService;
        this.abonnementService = abonnementService;
    }

    public void menuClient() {

        this.nettoyerTerminal();
        this.idClient = this.menuConnexion();

        if (this.idClient == 4) {
            return;
        }

        System.out.println("===================");
        System.out.println("Menu client n° : " + this.idClient);
        System.out.println("===================\n");
        System.out.println("1.) Afficher tous les livres.");
        System.out.println("2.) Afficher livres loues.");
        System.out.println("3.) Louer un livre.");
        System.out.println("4.) Rendre un livre.");
        System.out.println("5.) S'abonner a un canal de news.");
        System.out.println("6.) Retour menu principal.");
        System.out.print("\n---> Votre reponse : ");

        int reponse = Integer.parseInt(scanner.nextLine());
        this.nettoyerTerminal();
        switch(reponse) {

            case 1 : this.menuAffichageLivres();
            break;

            case 2 : this.menuRecuperationLivresLoues();
            break;

            case 3 : this.menuLocationLivre();
            break;

            case 4 : this.menuRenteLivre();
            break;

            case 5 : this.menuAbonnementCannal();
            break;

            case 6 : System.out.println("[Infos] : Fin du programme.");
            break;
        }
        
    }

    protected void menuAbonnementCannal() {

        System.out.println("------------------------------------------");
        System.out.println("A Quel canal souhaitez-vous vous abonner ?");
        System.out.println("------------------------------------------\n");

        System.out.println("1.) Auteur");
        System.out.println("2.) Titre");
        System.out.println("3.) Maison d'edition");
        System.out.println("4.) Isbn");
        System.out.println("5.) Nature");
        System.out.println("6.) Retour au menu");

        System.out.print("\n---> Votre reponse : ");

        int reponse = Integer.parseInt(this.scanner.nextLine());

        if (reponse == 6) {
            return;
        }

        this.nettoyerTerminal();

        System.out.println("---------------------------------------------------------");
        System.out.println("Choix du filtrage des informations publiees dans le canal");
        System.out.println("--------------------------------------------------------- \n");

        switch (reponse) {
            case 1 : System.out.print("Par quel nom d'auteur souhaitez-vous filtrer les retours du channel ? ---->  ");                
            break;

            case 2 : System.out.print("Par quel titre de livre souhaitez-vous filtrer les retours du channel ? ----> ");
            break;

            case 3 : System.out.print("Par quelle maison d'edition souhaitez-vous filtrer les retours du channel ? ----> ");
            break;

            case 4 : System.out.print("Par quel ISBN souhaitez-vous filtrer les retours du channel ? ----> ");
            break;

            case 5 : System.out.print("Par quelle nature de livre souhaitez-vous filtrer les retours du channel ? ----> ");
            break;
        
            default:
            break;
        }

        String filtreChoisi = this.scanner.nextLine();
        String channel = null;
        switch (reponse) {
            case 1: channel = "auteur";             
            break;
        
            case 2 : channel = "titre";
            break;

            case 3 : channel = "edition";
            break;
            
            case 4 : channel = "isbn";
            break;

            case 5 : channel = "nature";
            break;

            default:
            break;
        }
        this.abonnementService.abonnerClient(channel, filtreChoisi);
        this.nettoyerTerminal();
        System.out.println("--------------------------");
        System.out.println("Ecoute de channel en cours");
        System.out.println("--------------------------\n");

        System.out.println("- - - Channel : " + channel + "  |  Filtre : " + filtreChoisi + " - - -\n\n");
        System.out.print("(Entrez 0 pour quitter) > ");

        int retour = 1;
        do {
            retour = Integer.parseInt(this.scanner.nextLine());
        } while (retour != 0);

        this.nettoyerTerminal();
        this.menuClient();
    }

    protected void menuLocationLivre() {

        System.out.println("-------------------");
        System.out.println("Location d'un livre");
        System.out.println("-------------------\n");

        System.out.println("- - - Livres louables - - -\n");
        this.livreService.recupererLivresLouables().forEach(livre -> {
            System.out.println("* " + livre.getTitre() + 
                " (Id : " + livre.getId() + "). Nb Exemplaires disponibles : " + 
                livre.getNbCopies());
        });

        System.out.print("\n---> Id du livre que vous souhaitez louer (0 pour retourner en arriere): ");
        int reponse = Integer.parseInt(scanner.nextLine());
        System.out.println();
        
        if (reponse != 0 && this.clientService.louerLivre(this.idClient, reponse)) {
            System.out.println("\n[Infos] : vous avez loue le livre d'id : " + reponse);
        }        
        this.faireAvancerUtilisateur();
        this.menuClient();     
    }

    protected void menuRenteLivre() {
        
        System.out.println("----------------");
        System.out.println("Rendu d'un livre");
        System.out.println("----------------\n");

        System.out.println("- - - Livres en votre possession - - -\n");
        this.clientService.recupererLivresLoues(this.idClient).forEach(livre -> {
            System.out.println("* " + livre.getTitre() + " (Id : " + livre.getId() + ")");
        });

        System.out.print("\n---> Id du livre que vous souhaitez rendre (0 pour retourner en arriere): ");
        int reponse = Integer.parseInt(scanner.nextLine());
        System.out.println();

        if (reponse != 0 && this.clientService.rendreLivre(idClient, reponse)) {
            System.out.println("[Infos] : vous avez rendu le livre d'id " + reponse);
        }

        this.faireAvancerUtilisateur();
        this.menuClient();
    }

    protected void menuRecuperationLivresLoues() {

        System.out.println("------------------------------------");
        System.out.println("Livres loues par le client d'id : " + this.idClient);
        System.out.println("------------------------------------\n");

        List<Livre> livresLoues = this.clientService.recupererLivresLoues(idClient);
        livresLoues.forEach(livre -> {
            System.out.println("* " + livre.getTitre() + " (Id : " + livre.getId() + ")");
        });

        this.faireAvancerUtilisateur();
        this.menuClient();
    }

    protected void menuAffichageLivres() {

        System.out.println("-----------------------------------");
        System.out.println("Liste des livres de la bibliotheque");
        System.out.println("-----------------------------------\n");

        this.livreService.recupererTousLesLivres().forEach(livre -> {

            System.out.println(" * " + livre + "\n");
        });

        this.faireAvancerUtilisateur();
        this.menuClient();
    }

    protected void faireAvancerUtilisateur() {
        System.out.println("\n---> Appuyez sur ENTRER pour continuer...");
        this.scanner.nextLine();
    }

    public void nettoyerTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    protected int menuConnexion() {

        System.out.println("------------------------");
        System.out.println("Menu de connexion Client");
        System.out.println("------------------------");
        System.out.println("\n1.) Client 1.");
        System.out.println("2.) Client 2.");
        System.out.println("3.) Client 3.");
        System.out.println("4.) Retour menu principal");
        System.out.print("\n---> Votre reponse : ");

        int resultat = Integer.parseInt(this.scanner.nextLine());

        if (resultat < 1 || resultat > 5) {
            System.out.println("[Erreur] : Reponse inconnue. Fin du programme.");
        }

        this.nettoyerTerminal();
        return resultat;
    }
}
