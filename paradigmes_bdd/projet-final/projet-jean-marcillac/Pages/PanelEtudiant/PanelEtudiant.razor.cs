using Microsoft.AspNetCore.Components;
using projet_jean_marcillac.Modeles;
using projet_jean_marcillac.Services.CoursService;
using projet_jean_marcillac.Services.MembreService;

namespace projet_jean_marcillac.Pages.PanelEtudiant
{
    public partial class PanelEtudiant
    {
        [Inject]
        protected NavigationManager? NavigationManager { get; set; }

        [Inject]
        protected IMembreService? MembreService { get; set; }

        [Inject]
        protected ICoursService? CoursService { get; set; }

        protected List<Cours>? CoursAbonnes { get; set; }
        protected List<Cours>? CoursNonAbonnes { get; set; }
        protected Eleve? MembreConnecte { get; set; }
        protected List<Membre>? TousLesEtudiants { get; set; }

        protected override async Task OnInitializedAsync()
        {
            if (this.MembreService == null)
            {
                throw new InvalidOperationException("MembreService est null");
            }

            if (this.CoursService == null)
            {
                throw new InvalidOperationException("CoursService est null");
            }

            this.TousLesEtudiants = new List<Membre>();
            var tousLesEtudiants = await this.MembreService.RecupererTousLesEleves();
            tousLesEtudiants.ToList().ForEach(etudiant => this.TousLesEtudiants.Add(etudiant));

            this.CoursAbonnes = new List<Cours>();
            var tousLesCours = await this.CoursService.RecupererTousLesCours();
            this.CoursNonAbonnes = tousLesCours.ToList();

            this.MembreConnecte = new Eleve(4, "Jean", "Marcillac", new List<int> { 1, 2 });
        }


        protected void OnConnexion(Membre membre)
        {
            MembreConnecte = (Eleve)membre;
            this.FilterCours();
            StateHasChanged();
        }

        protected void FilterCours()
        {
            if (MembreConnecte == null)
            {
                return;
            }

            if (this.CoursAbonnes == null || this.CoursNonAbonnes == null)
            {
                return;
            }

            var coursADeplacer = new List<Cours>();
            this.MembreConnecte.IdsCoursInscrits.ForEach(idCours => {

                if (this.CoursNonAbonnes.Find(cours => cours.Id == idCours) != null)
                {
                    var cours = this.CoursNonAbonnes.Find(cours => cours.Id == idCours);
                    if (cours != null)
                    {
                        coursADeplacer.Add(cours);
                    }
                }
            });

            coursADeplacer.ForEach(cours => {
                this.CoursAbonnes.Add(cours);
                this.CoursNonAbonnes.Remove(cours);
            });
        }
    }
}