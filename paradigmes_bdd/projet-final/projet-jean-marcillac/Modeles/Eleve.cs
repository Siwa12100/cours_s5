using StackExchange.Redis;
using System.Collections.Generic;

namespace projet_jean_marcillac.Modeles
{
    public class Eleve : Membre
    {
        public List<int> IdsCoursInscrits { get; set; }

        public Eleve(int id, string nom, string prenom, List<int>? idsCoursInscrits = null)
        {
            Id = id;
            Nom = nom;
            Prenom = prenom;
            IdsCoursInscrits = idsCoursInscrits ?? new List<int>();
        }

        public Eleve(HashEntry[] hashEntries)
        {
            Id = (int)hashEntries.FirstOrDefault(e => e.Name == "Id").Value;
            Nom = hashEntries.FirstOrDefault(e => e.Name == "Nom").Value;
            Prenom = hashEntries.FirstOrDefault(e => e.Name == "Prenom").Value;
            IdsCoursInscrits = hashEntries.FirstOrDefault(e => e.Name == "IdsCoursInscrits").Value.ToString().Split(',').Select(int.Parse).ToList();
        }

        public HashEntry[] ToHashEntries()
        {
            return new[]
            {
                new HashEntry("Id", Id),
                new HashEntry("Nom", Nom),
                new HashEntry("Prenom", Prenom),
                new HashEntry("IdsCoursInscrits", string.Join(",", IdsCoursInscrits))
            };
        }
    }
}