namespace projet_jean_marcillac
{
    public class Cours
    {
        public int Id { get; set; }
        public string Titre { get; set; }
        public string Resume { get; set; }
        public int NombreDePlacesDisponibles { get; set; }
        public string Contenu { get; set; }

        public Cours(int id, string titre, string resume, int nombreDePlacesDisponibles, string contenu)
        {
            Id = id;
            Titre = titre;
            Resume = resume;
            NombreDePlacesDisponibles = nombreDePlacesDisponibles;
            Contenu = contenu;
        }

        public Dictionary<string, Object> ToDictionary()
        {
            Dictionary<string, Object> cours = new Dictionary<string, Object>();
            cours.Add("Id", Id);
            cours.Add("Titre", Titre);
            cours.Add("Resume", Resume);
            cours.Add("NombreDePlacesDisponibles", NombreDePlacesDisponibles);
            cours.Add("Contenu", Contenu);
            return cours;
        }
    }
}