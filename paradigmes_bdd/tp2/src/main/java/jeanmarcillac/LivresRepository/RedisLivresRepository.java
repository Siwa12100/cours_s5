package jeanmarcillac.LivresRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import jeanmarcillac.modeles.Livre;
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
        this.notifierLivreSauvegarde(livreASauvegarder);
    }

    @Override
    public Optional<Livre> recupererLivre(int idLivre) {
        
        String livreKey = "livre:" + idLivre;
        Map<String, String> livreData = jedis.hgetAll(livreKey);

        if (livreData.isEmpty()) {
            return Optional.ofNullable(null);
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
    public boolean supprimerLivre(int idLivre) {
    
        String cle = "livre:" + idLivre;
        if (!jedis.exists(cle)) {
            return false;
        }
        
        this.recupererLivre(idLivre).ifPresent(livre -> {
            this.notifierLivreSupprime(livre);
        });

        jedis.del(cle);
        return true;
    }

    @Override
    public int recupererNombreDeLivres() {
        int nbLivres = this.recupererLivres().size();
        if (nbLivres == 0) return 1;
        return nbLivres;
    }

    protected void notifierLivreSauvegarde(Livre livre) {
        jedis.publish("titre", livre.getTitre() + ". Le livre " + livre.getTitre() + " (id : " + livre.getId() +") vient d'etre sauvegarde.");
        jedis.publish("auteur", livre.getAuteur() + ". Le livre " + livre.getTitre() + " (id : " + livre.getId() + " et d'auteur " + livre.getAuteur() + " vient d'etre sauvegarde.");
        jedis.publish("nature", livre.getTitre() + ". Le livre " + livre.getTitre() + " (id : " + livre.getId() +") et de nature " + livre.getNature() + " vient d'etre sauvegarde.");
        jedis.publish("isbn", livre.getTitre() + ". Le livre " + livre.getTitre() + " (id : " + livre.getId() +") et d'isbn + " + livre.getIsbn() + " vient d'etre sauvegarde.");
        jedis.publish("edition", livre.getTitre() + ". Le livre " + livre.getTitre() + " (id : " + livre.getId() +") et d'editeur " + livre.getEditeur() + " vient d'etre sauvegarde.");
    }

    protected void notifierLivreSupprime(Livre livre) {
        jedis.publish("titre", livre.getTitre() + ". Le livre " + livre.getTitre() + " (id : " + livre.getId() +") vient d'etre supprime.");
        jedis.publish("auteur", livre.getAuteur() + ". Le livre " + livre.getTitre() + " (id : " + livre.getId() + " et d'auteur " + livre.getAuteur() + " vient d'etre supprime.");
        jedis.publish("nature", livre.getTitre() + ". Le livre " + livre.getTitre() + " (id : " + livre.getId() +") et de nature " + livre.getNature() + " vient d'etre supprime.");
        jedis.publish("isbn", livre.getTitre() + ". Le livre " + livre.getTitre() + " (id : " + livre.getId() +") et d'isbn + " + livre.getIsbn() + " vient d'etre supprime.");
        jedis.publish("edition", livre.getTitre() + ". Le livre " + livre.getTitre() + " (id : " + livre.getId() +") et d'editeur " + livre.getEditeur() + " vient d'etre supprime.");
    }
}
