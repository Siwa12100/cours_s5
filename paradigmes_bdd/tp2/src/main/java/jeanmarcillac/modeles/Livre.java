package jeanmarcillac.modeles;

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

    public static final int dureeVieLivreEnSecondes = 20000;
    protected static final int valeurIdNonDefini = -1;

    public Livre(int id, int isbn, String titre, String auteur, String nature, String editeur, int nbCopies) {

        this.id = id;
        this.isbn = isbn;
        this.titre = titre;
        this.auteur = auteur;
        this.nature = nature;
        this.editeur = editeur;
        this.nbCopies = nbCopies;
    }

    public Livre(int isbn, String titre, String auteur, String nature, String editeur, int nbCopies) {
        this(Livre.valeurIdNonDefini, isbn, titre, auteur, nature, editeur, nbCopies);
    }

    public Livre(Map<String, String> livreData) {

           this.id = Integer.parseInt(livreData.get("id"));
           this.isbn = Integer.parseInt(livreData.get("isbn"));
           this.titre = livreData.get("titre");
           this.auteur = livreData.get("auteur");
           this.nature = livreData.get("nature");
           this.editeur = livreData.get("editeur");
           this.nbCopies = Integer.parseInt(livreData.get("nbCopies"));
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return this.titre;
    }

    public HashMap<String, Object> toHashMap() {
        HashMap<String, Object> resultat = new HashMap<>();
        resultat.put("id", this.id);
        resultat.put("titre", this.titre);
        resultat.put("nature", this.nature);
        resultat.put("isbn", this.isbn);
        resultat.put("auteur", this.auteur);
        resultat.put("editeur", this.editeur);
        resultat.put("nbCopies", this.nbCopies);
        return resultat;
    }

    @Override
    public String toString() {
        return "[Livre] => " + this.titre + " {" + "\n" + "\t" + 
                    "Id : " + this.id + "\n" + "\t" + 
                    "Titre : " + this.titre + "\n" + "\t" + 
                    "Auteur : " + this.auteur + "\n" + "\t" + 
                    "Nature : " + this.nature + "\n" + "\t" + 
                    "Editeur : " + this.editeur + "\n" + "\t" + 
                    "Nombre copies : " + this.nbCopies +  "\n" + "\t" + 
                    "Isbn : " + this.isbn +              "\n" +       
        "}";
    }
}
