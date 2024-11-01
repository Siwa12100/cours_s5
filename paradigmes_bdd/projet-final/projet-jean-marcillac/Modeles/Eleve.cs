using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace projet_jean_marcillac.Modeles
{
    public class Eleve : Membre
    {
        List<int> IdsCoursSuivis {get; set;}

        public Eleve(int id, string nom, string prenom, List<int>? idsCoursSuivis = null)
        {
            Id = id;
            Nom = nom;
            Prenom = prenom;
            IdsCoursSuivis = idsCoursSuivis ?? new List<int>();
        }
    }
}