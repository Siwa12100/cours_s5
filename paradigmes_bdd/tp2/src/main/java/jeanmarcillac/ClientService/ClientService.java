package jeanmarcillac.ClientService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jeanmarcillac.LivresService.ILivreService;
import jeanmarcillac.modeles.Livre;

public class ClientService implements IClientService {

    protected ILivreService livreService;
    protected Map<Integer, List<Integer>> locationsClients;

    public ClientService(ILivreService livreService) {
        this.livreService = livreService;
        this.locationsClients = new HashMap<>();
        this.locationsClients.put(1, new ArrayList<>());
        this.locationsClients.put(2, new ArrayList<>());
        this.locationsClients.put(3, new ArrayList<>());
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

        if (this.livreService.recupererLivre(idLivre).isEmpty()) {
            return false;
        }

        this.locationsClients.get(idClient).add(idLivre);
        this.livreService.louerLivre(idLivre);
        return true;
    }


    @Override
    public boolean rendreLivre(int idClient, int idLivre) {

        if (!this.locationsClients.get(idClient).contains(idLivre)) {
            return false;
        }

        this.locationsClients.get(idClient).remove(idLivre);
        return true;
    }
}