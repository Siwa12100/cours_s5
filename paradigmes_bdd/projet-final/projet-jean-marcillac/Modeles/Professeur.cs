namespace projet_jean_marcillac.Modeles
{
    public class Professeur : Membre
    {
        public List<int> IdsCoursDonnes { get; set; }

        public Professeur(int id, string nom, string prenom, List<int>? idsCoursDonnes = null)
        {
            Id = id;
            Nom = nom;
            Prenom = prenom;
            IdsCoursDonnes = idsCoursDonnes ?? new List<int>();
        }
    }
}