package LivresService;

import java.util.List;

import modeles.Livre;

public interface ILivreService {
    
    void ajouterLivre(Livre livreAAjouter);
    Livre recupererLivre(int idLivreARecuperer);
    List<Livre> recupererTousLesLivres();
    void modifierLivre(Livre livreAModifier);
    void supprimerLivre(int idLivre);
    void louerLivre(int idLivre, int idLoueur);
    void rendreLivre(int idLivre, int idRendeur);
}
