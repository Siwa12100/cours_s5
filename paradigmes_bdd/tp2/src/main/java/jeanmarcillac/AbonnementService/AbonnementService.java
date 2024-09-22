package jeanmarcillac.AbonnementService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.JedisPubSub;

public class AbonnementService implements IAbonnementService {

    protected Optional<String> channelSuiviParClientCourant;
    protected String filtreDuClientCourant;
    protected List<String> tousLesChannelsDisponibles;
    JedisPubSub jedisPubSub;
    protected int idClientCourant;

    public AbonnementService(JedisPooled jedisPooled) {

        this.tousLesChannelsDisponibles = new ArrayList<>();
        this.tousLesChannelsDisponibles.add("titre");
        this.tousLesChannelsDisponibles.add("auteur");
        this.tousLesChannelsDisponibles.add("nature");
        this.tousLesChannelsDisponibles.add("isbn");
        this.tousLesChannelsDisponibles.add("edition");

        this.jedisPubSub = new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                gererArriveeMessage(channel, message);
            }
        };

        new Thread(() -> {
            this.tousLesChannelsDisponibles.forEach(channel -> {
                jedisPooled.subscribe(this.jedisPubSub, channel);
            });
    
        }).start();        
    }


    @Override
    public void abonnerClient(String channel, String filtre) {

        if (!this.tousLesChannelsDisponibles.contains(channel)) {
            System.out.println("[Erreur] : Le channel " + channel + " est inconnu.");
            return;
        }
        this.channelSuiviParClientCourant = Optional.of(channel);
        this.filtreDuClientCourant = filtre;
    }

    @Override
    public void desabonnerClient() {
        this.channelSuiviParClientCourant = null;
    }

    protected void gererArriveeMessage(String channel, String message)  {

        this.channelSuiviParClientCourant.ifPresent((channelDuClient) -> {
            
            if (this.verifierMessagePasseFiltre(message)) {
                System.out.println("\n[Channel : " + channelDuClient + "] ---> " + message);
                System.out.print("(Entrez 0 pour quitter) > ");
            }
        });
    }

    protected boolean verifierMessagePasseFiltre(String message) {
        return true;
    }
    
}
