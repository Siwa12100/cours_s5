package main.jeanmarcillac.LivresRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import main.jeanmarcillac.modeles.Livre;
import redis.clients.jedis.JedisPooled;

public class RedisLivresRepository implements ILivresRepository {

    protected JedisPooled jedis;
    
    public RedisLivresRepository(JedisPooled jedisPooled) {
        this.jedis = jedisPooled;
    }

    @Override
    public void sauvegarderLivre(Livre livreASauvegarder) {
        
        HashMap<String, Object> livreMap = livreASauvegarder.toHashMap();
        String cleLivre = "livre:" + livreASauvegarder.getId();

        for (Map.Entry<String, Object> entry : livreMap.entrySet()) {
            jedis.hset(cleLivre, entry.getKey(), entry.getValue().toString());
        }

        jedis.expire(cleLivre, Livre.dureeVieLivreEnSecondes);
    }

    @Override
    public Optional<Livre> recupererLivre(int idLivre) {
        
        String livreKey = "livre:" + idLivre;
        Map<String, String> livreData = jedis.hgetAll(livreKey);

        if (livreData.isEmpty()) {
            return null;
        }

        Livre resultat = new Livre(livreData);
        return Optional.of(resultat);
    }

    @Override
    public List<Livre> recupererLivres() {
        
        List<Livre> livres = new ArrayList<>();
        Set<String> keys = jedis.keys("livre:*");

        for (String key : keys) {
            Map<String, String> livreData = jedis.hgetAll(key);
            Livre livre = new Livre(livreData);
            livres.add(livre);
        }

        return livres;
    }

    @Override
    public void supprimerLivre(int idLivre) {
    
        String cle = "livre:" + idLivre;
        if (!jedis.exists(cle)) {
            System.out.println("[Erreur] : Aucun livre trouvé avec l'id " + idLivre + ".");
            
        }
        jedis.del(cle);
    }
    
}
