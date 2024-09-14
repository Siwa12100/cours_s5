package main.jeanmarcillac;
import java.util.Optional;
import java.util.Scanner;

import javax.net.ssl.SSLSocketFactory;

import main.jeanmarcillac.LivresRepository.ILivresRepository;
import main.jeanmarcillac.LivresRepository.RedisLivresRepository;
import main.jeanmarcillac.LivresService.ILivreService;
import main.jeanmarcillac.LivresService.LivreService;
import main.jeanmarcillac.clis.EditeurCli;
import main.jeanmarcillac.modeles.Livre;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisClientConfig;
import redis.clients.jedis.JedisPooled;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Debut du programme ! \n");
        JedisPooled jedis = initialisationJedis("149.7.5.30", 6379, "senhal");
        ILivresRepository livresRepository = new RedisLivresRepository(jedis);
        ILivreService livreService = new LivreService(livresRepository);
        EditeurCli editeurCli = new EditeurCli(scanner, livreService);
        editeurCli.menuEditeur();

        // Livre l1 = new Livre(1, 1234, "Mireilha", "Mistral", "poesie", "grelh", 2);
        // livresRepository.sauvegarderLivre(l1);
        // Optional<Livre> rep = livresRepository.recupererLivre(l1.getId());
        // if (rep.isPresent()) {
        //     l1 = rep.get();
        //     System.out.println("Reponse redis : \n" + l1);

        //     livresRepository.supprimerLivre(l1.getId());
        // }

        jedis.close();
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