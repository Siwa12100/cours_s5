package jeanmarcillac.clis;

import java.util.List;
import java.util.Scanner;

import jeanmarcillac.ClientService.IClientService;
import jeanmarcillac.LivresService.ILivreService;
import jeanmarcillac.modeles.Livre;

public class ClientCli {
    
    protected ILivreService livreService;
    protected Scanner scanner;
    protected IClientService clientService;
    protected int idClient = 0;

    public ClientCli(Scanner scanner, ILivreService livreService, IClientService clientService) {
        this.livreService = livreService;
        this.scanner = scanner;
        this.clientService = clientService;
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

            case 6 : System.out.println("[Infos] : Fin du programme.");
            break;
        }
        
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
        System.out.print("\n---> Votre reponse : ");

        int resultat = Integer.parseInt(this.scanner.nextLine());

        if (resultat < 1 || resultat > 4) {
            System.out.println("[Erreur] : Reponse inconnue. Fin du programme.");
        }

        this.nettoyerTerminal();
        return resultat;
    }
}
