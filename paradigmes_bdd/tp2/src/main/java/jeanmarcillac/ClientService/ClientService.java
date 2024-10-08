package jeanmarcillac.ClientService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import jeanmarcillac.ClientRepository.IClientRepository;
import jeanmarcillac.LivresService.ILivreService;
import jeanmarcillac.modeles.Livre;

public class ClientService implements IClientService {

    protected ILivreService livreService;
    protected IClientRepository clientRepository;
    protected Map<Integer, List<Integer>> locationsClients;

    public ClientService(ILivreService livreService, IClientRepository clientRepository) {
        this.livreService = livreService;
        this.clientRepository = clientRepository;

        this.clientRepository.recupererDonneesClients().ifPresentOrElse(donnees -> {
            this.locationsClients = donnees;

            for (int i = 1; i <=3; i++) {
                System.out.println("i : " + i);
                if (!this.locationsClients.containsKey(i)) {
                    this.locationsClients.put(i, new ArrayList<>());
                }
            }

        }, () -> {
            this.locationsClients = new HashMap<>();
            this.locationsClients.put(1, new ArrayList<>());
            this.locationsClients.put(2, new ArrayList<>());
            this.locationsClients.put(3, new ArrayList<>());
        });        
    }

    @Override
    public List<Livre> recupererLivresLoues(int idClient) {

        List<Livre> resultats = new ArrayList<>();
        this.locationsClients.get(idClient).forEach(idLivre -> {

            this.livreService.recupererLivre(idLivre).ifPresent(livre -> {
                resultats.add(livre);
            });
        });

        return resultats;
    }
    
    @Override
    public boolean louerLivre(int idClient, int idLivre) {

        Optional<Livre> livreALouer = this.livreService.recupererLivre(idLivre);
        if (livreALouer.isEmpty()) {
            System.out.println("[Erreur] : Le livre d'id " + idLivre + " ne peut pas etre loue.");
            return false;
        }

        if (this.locationsClients.get(idClient).contains(idLivre)) {
            System.out.println("[Erreur] : Vous louez deja un exemplaire du livre d'id " + idLivre);
            return false;
        }

        livreALouer.ifPresent((livre) -> {
            if (livre.getNbCopies() <= 0) {
                System.out.println("[Erreur] : Plus de copies disponibles du livre, location impossible");
                return;
            }
        });

        this.locationsClients.get(idClient).add(idLivre);
        this.livreService.louerLivre(idLivre);
        this.clientRepository.sauvegarderDonneesClients(locationsClients);
        return true;
    }


    @Override
    public boolean rendreLivre(int idClient, int idLivre) {

        if (!this.locationsClients.get(idClient).contains(idLivre)) {
            return false;
        }

        this.locationsClients.get(idClient).remove(this.locationsClients.get(idClient).indexOf(idLivre));
        this.livreService.rendreLivre(idLivre);
        this.clientRepository.sauvegarderDonneesClients(locationsClients);
        return true;
    }
}
