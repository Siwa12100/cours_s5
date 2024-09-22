package jeanmarcillac.LivresService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jeanmarcillac.LivresRepository.ILivresRepository;
import jeanmarcillac.modeles.Livre;

public class LivreService implements ILivreService {

    protected ILivresRepository livresRepository;
    protected int valeurProchainIdLivre; 

    public LivreService(ILivresRepository livresRepository) {
        this.livresRepository = livresRepository;
        this.valeurProchainIdLivre = this.livresRepository.recupererNombreDeLivres();
    }

    protected int definirIdLivre() {

        if (this.valeurProchainIdLivre <= 10000) {
            this.valeurProchainIdLivre++;
            return this.valeurProchainIdLivre;
        }
        throw new RuntimeException("Impossible ajouter nouveaux livres.");
    }

    @Override
    public void ajouterLivre(Livre livreAAjouter) {
        livreAAjouter.setId(this.definirIdLivre());
        this.livresRepository.sauvegarderLivre(livreAAjouter);
    }

    @Override
    public Optional<Livre> recupererLivre(int idLivreARecuperer) {
        return this.livresRepository.recupererLivre(idLivreARecuperer);
    }

    @Override
    public List<Livre> recupererTousLesLivres() {
        return this.livresRepository.recupererLivres();
    }

    @Override
    public boolean supprimerLivre(int idLivre) {
        return this.livresRepository.supprimerLivre(idLivre);
    }

    @Override
    public List<Livre> recupererLivresLouables() {

        List<Livre> resultat = new ArrayList<>();
        this.recupererTousLesLivres().forEach(livre -> {

            if (livre.getNbCopies() != 0) {
                resultat.add(livre);
            }
        });
        return resultat;
    }

    @Override
    public boolean louerLivre(int idLivre) {

        Optional<Livre> livre = this.livresRepository.recupererLivre(idLivre);
        if (livre.isPresent()) {
            livre.get().setNbCopies(livre.get().getNbCopies() -1);
            this.livresRepository.sauvegarderLivre(livre.get());
            return true;
        }

        System.out.println("Le livre d'id " + idLivre + " n'est pas reconnu.");
        return false;
    }

    @Override
    public boolean rendreLivre(int idLivre) {
        Optional<Livre> livre = this.livresRepository.recupererLivre(idLivre);
        if (livre.isPresent()) {
            livre.get().setNbCopies(livre.get().getNbCopies() + 1);
            this.livresRepository.sauvegarderLivre(livre.get());
            return true;
        }

        System.out.println("Le livre d'id " + idLivre + " n'est pas reconnu");
        return false;
    }   
}
