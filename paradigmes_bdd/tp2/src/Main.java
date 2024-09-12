import java.util.Scanner;

import clis.EditeurCli;
import modeles.Livre;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Debut du programme ! \n");
        Livre l1 = new Livre(1, 1234, "Mireilha", "Mistral", "poesie", "grelh", 2);
        System.out.println(l1);

        EditeurCli editeurCli = new EditeurCli(scanner);
        editeurCli.menuEditeur();
    }
}