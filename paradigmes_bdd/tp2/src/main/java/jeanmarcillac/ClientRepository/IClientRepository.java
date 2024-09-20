package jeanmarcillac.ClientRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IClientRepository {

    Optional<Map<Integer, List<Integer>>> recupererDonneesClients();
    void sauvegarderDonneesClients(Map<Integer, List<Integer>> donneesClients);   
    
}
