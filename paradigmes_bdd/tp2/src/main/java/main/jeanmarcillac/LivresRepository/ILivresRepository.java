package main.jeanmarcillac.LivresRepository;

import java.util.List;
import java.util.Optional;

import main.jeanmarcillac.modeles.Livre;

public interface ILivresRepository {
    
    void sauvegarderLivre(Livre livreASauvegarder);
    Optional<Livre> recupererLivre(int idLivre);
    List<Livre> recupererLivres();
    boolean supprimerLivre(int idLivre);
    int recupererNombreDeLivres();
}
