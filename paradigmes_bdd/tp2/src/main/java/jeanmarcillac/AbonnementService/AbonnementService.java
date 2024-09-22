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
        this.channelSuiviParClientCourant = Optional.ofNullable(null);
    }

    protected void gererArriveeMessage(String channel, String message)  {

        if (this.channelSuiviParClientCourant == null) return;

        this.channelSuiviParClientCourant.ifPresent((channelDuClient) -> {
            
            if (this.verifierMessagePasseFiltre(message)) {
                System.out.println(this.mettreAuPropreMessage(message));
                System.out.print("(Entrez 0 pour quitter) > ");
            }
        });
    }

    protected boolean verifierMessagePasseFiltre(String message) {

        if (filtreDuClientCourant == null) {
            return false;
        }
        return message.split("\\.", 2)[0].trim().equalsIgnoreCase(this.filtreDuClientCourant);
    }

    protected String mettreAuPropreMessage(String message) {
        int indexPoint = message.indexOf('.');
        if (indexPoint == -1) {
            return message;
        }
        return message.substring(indexPoint + 1).trim();
    }
}
