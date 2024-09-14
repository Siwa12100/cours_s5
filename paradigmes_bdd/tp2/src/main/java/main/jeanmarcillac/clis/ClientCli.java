package main.jeanmarcillac.clis;

import java.util.Scanner;

import main.jeanmarcillac.LivresService.ILivreService;

public class ClientCli {
    
    protected ILivreService livreService;
    protected Scanner scanner;

    public ClientCli(Scanner scanner, ILivreService livreService) {
        this.livreService = livreService;
        this.scanner = scanner;
    }

    public void menuClient() {

        this.nettoyerTerminal();
        System.out.println("===========");
        System.out.println("Menu client");
        System.out.println("===========\n");
        System.out.println("1.) Afficher tous les livres.");
        System.out.println("2.) Afficher livres loues.");
        System.out.println("3.) Louer un livre.");
        System.out.println("4.) Rendre un livre.");
        System.out.println("5.) S'abonner a un canal de news.");
        System.out.println("6.) Retour menu principal.");
        System.out.print("\n---> Votre reponse : ");

        int reponse = Integer.parseInt(scanner.nextLine());
        switch(reponse) {

            case 6 : System.out.println("[Infos] : Fin du programme.");
            break;
        }
        
    }

    public void nettoyerTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
