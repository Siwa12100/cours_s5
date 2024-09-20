package jeanmarcillac.LivresService;

import java.util.List;
import java.util.Optional;

import jeanmarcillac.modeles.Livre;

public interface ILivreService {
    
    void ajouterLivre(Livre livreAAjouter);
    Optional<Livre> recupererLivre(int idLivreARecuperer);
    List<Livre> recupererTousLesLivres();
    List<Livre> recupererLivresLouables();
    List<Livre> recupererLivresLoues();
    boolean supprimerLivre(int idLivre);
    boolean louerLivre(int idLivre);
    boolean rendreLivre(int idLivre);
    void abonnerClient(String nomClient, String channel);
    void desabonnerClient(String nomClient, String channel);
}
