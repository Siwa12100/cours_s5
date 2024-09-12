package modeles;

import java.util.HashMap;
import java.util.Map;

public class Livre {
    
    protected int id;
    protected int isbn;
    protected String titre;
    protected String auteur;
    protected String nature;
    protected String editeur;
    protected int nbCopies;

    public Livre(int id, int isbn, String titre, String auteur, String nature, String editeur, int nbCopies) {

        this.id = id;
        this.isbn = isbn;
        this.titre = titre;
        this.auteur = auteur;
        this.nature = nature;
        this.editeur = editeur;
        this.nbCopies = nbCopies;
    }

    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> resultat = new HashMap<>();
        resultat.put("id", this.id);
        resultat.put("isbn", this.isbn);
        resultat.put("auteur", this.auteur);
        resultat.put("editeur", this.editeur);
        resultat.put("nbCopies", this.nbCopies);
        return resultat;
    }

    @Override
    public String toString() {

        return "[Livre] " + this.titre + "  {" +
                    "Id : " + this.id + 
                    "Titre : " + this.titre + 
                    "Auteur : " + this.auteur + 
                    "Nature : " + this.nature + 
                    "Editeur : " + this.editeur + 
                    "Nombre copies : " + this.nbCopies +  
                    "Isbn : " + this.isbn +                    
        "}";
    }
}
