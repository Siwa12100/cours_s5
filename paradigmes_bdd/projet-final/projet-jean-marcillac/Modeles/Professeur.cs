using StackExchange.Redis;
using System.Collections.Generic;

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

        public Professeur(HashEntry[] hashEntries)
        {
            Id = (int)hashEntries.FirstOrDefault(e => e.Name == "Id").Value;
            Nom = hashEntries.FirstOrDefault(e => e.Name == "Nom").Value;
            Prenom = hashEntries.FirstOrDefault(e => e.Name == "Prenom").Value;
            IdsCoursDonnes = hashEntries.FirstOrDefault(e => e.Name == "IdsCoursDonnes").Value.ToString().Split(',').Select(int.Parse).ToList();
        }

        public HashEntry[] ToHashEntries()
        {
            return new[]
            {
                new HashEntry("Id", Id),
                new HashEntry("Nom", Nom),
                new HashEntry("Prenom", Prenom),
                new HashEntry("IdsCoursDonnes", string.Join(",", IdsCoursDonnes))
            };
        }
    }
}