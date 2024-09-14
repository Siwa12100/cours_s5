package main.jeanmarcillac;
import java.util.Optional;
import java.util.Scanner;

import javax.net.ssl.SSLSocketFactory;

import main.jeanmarcillac.LivresRepository.ILivresRepository;
import main.jeanmarcillac.LivresRepository.RedisLivresRepository;
import main.jeanmarcillac.LivresService.ILivreService;
import main.jeanmarcillac.LivresService.LivreService;
import main.jeanmarcillac.clis.ClientCli;
import main.jeanmarcillac.clis.EditeurCli;
import main.jeanmarcillac.modeles.Livre;
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
        EditeurCli editeurCli = new EditeurCli(scanner, livreService);
        ClientCli clientCli = new ClientCli(scanner, livreService);
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