package jeanmarcillac.AbonnementService;

public interface IAbonnementService {
    
    void abonnerClient(String channel, String filtre);
    void desabonnerClient();
}
