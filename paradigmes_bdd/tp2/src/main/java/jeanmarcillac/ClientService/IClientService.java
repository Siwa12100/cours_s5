package jeanmarcillac.ClientService;

import java.util.List;

import jeanmarcillac.modeles.Livre;

public interface IClientService {
    
    List<Livre> recupererLivresLoues(int idClient);
    boolean louerLivre(int idLivre);
    boolean rendreLivre(int idLivre);
}
