package jeanmarcillac;
import java.util.Scanner;

import jeanmarcillac.ClientRepository.RedisClientRepository;
import jeanmarcillac.AbonnementService.AbonnementService;
import jeanmarcillac.AbonnementService.IAbonnementService;
import jeanmarcillac.ClientRepository.IClientRepository;
import jeanmarcillac.ClientService.ClientService;
import jeanmarcillac.ClientService.IClientService;
import jeanmarcillac.LivresRepository.ILivresRepository;
import jeanmarcillac.LivresRepository.RedisLivresRepository;
import jeanmarcillac.LivresService.ILivreService;
import jeanmarcillac.LivresService.LivreService;
import jeanmarcillac.clis.ClientCli;
import jeanmarcillac.clis.EditeurCli;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.JedisPooled;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        JedisPooled jedis = initialisationJedis("149.7.5.30", 6379, "senhal");
        ILivresRepository livresRepository = new RedisLivresRepository(jedis);
        ILivreService livreService = new LivreService(livresRepository);
        IClientRepository clientRepository = new RedisClientRepository(jedis);
        IClientService clientService = new ClientService(livreService, clientRepository);
        EditeurCli editeurCli = new EditeurCli(scanner, livreService);
        IAbonnementService abonnementService = new AbonnementService(jedis);
        ClientCli clientCli = new ClientCli(scanner, livreService, clientService, abonnementService);
        lancementCli(scanner, editeurCli, clientCli);
        jedis.close();
    }

    protected static void lancementCli(Scanner scanner, EditeurCli editeurCli, ClientCli clientCli) {

        int resultat = 0;
        do {
            editeurCli.nettoyerTerminal();
            System.out.println("===================================");
            System.out.println("Projet Redis - Jean Marcillac Web 1");
            System.out.println("===================================\n");
            System.out.println("1.) Menu editeur.");
            System.out.println("2.) Menu Client.");
            System.out.println("3.) Quitter le projet.");
            System.out.print("\n--> Votre reponse : ");
            resultat = Integer.parseInt(scanner.nextLine());

            switch(resultat) {
                case 1 : editeurCli.menuEditeur();
                break;

                case 2 : clientCli.menuClient();
                break;

                case 3 : System.out.println("Fin du programme.");
                break;
            }
        } while (resultat != 3);

    }

    protected static JedisPooled initialisationJedis(String ip, int port, String mdp) {

        HostAndPort localisationServeur = new HostAndPort(ip, port);
        JedisClientConfig config = DefaultJedisClientConfig.builder()
                .user("default")
                .password(mdp)
                .build();

        return new JedisPooled(localisationServeur, config);
    }
}