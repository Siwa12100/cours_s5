using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using projet_jean_marcillac.Modeles;
using projet_jean_marcillac.Services.CoursService;
using projet_jean_marcillac.Services;
using projet_jean_marcillac.Services.MembreService;

namespace projet_jean_marcillac.Services
{
    public class DataService : IMembreService, ICoursService
    {
        protected readonly IMembreService MembreService;
        protected readonly ICoursService CoursService;
        public RedisService RedisService { get; }

        public DataService(RedisService redisService)
        {   
            if (redisService == null)
            {
                throw new ArgumentNullException(nameof(redisService));
            }
            this.RedisService = redisService;
            this.MembreService = new MembreService.MembreService(redisService);
            this.CoursService = new CoursService.CoursService(redisService);
        }

        // Gestion des cours
        public async Task<Cours> AjouterCours(Cours cours)
        {
            var addedCours = await CoursService.AjouterCours(cours);

            // Mise à jour des élèves inscrits à ce cours
            foreach (var eleveId in cours.IdsElevesInscrits)
            {
                var eleve = await MembreService.RecupererEleve(eleveId);
                eleve.IdsCoursInscrits.Add(cours.Id);
                await MembreService.ModifierEleve(eleve.Id, eleve);
            }

            // Mise à jour du professeur donnant ce cours
            if (cours.IdProfesseur != -2)
            {
                var professeur = await MembreService.RecupererProfesseur(cours.IdProfesseur);
                professeur.IdsCoursDonnes.Add(cours.Id);
                await MembreService.ModifierProfesseur(professeur.Id, professeur);
            }

            return addedCours;
        }

        public async Task<IEnumerable<Cours>> RecupererTousLesCours()
        {
            return await CoursService.RecupererTousLesCours();
        }

        public async Task<Cours> RecupererCours(int id)
        {
            return await CoursService.RecupererCours(id);
        }

        public async Task<Cours> ModifierCours(int id, Cours nouvelleVersionDuCours)
        {
          
            var coursExistant = await CoursService.RecupererCours(id);
            var elevesExistants = coursExistant.IdsElevesInscrits;
            var elevesModifies = nouvelleVersionDuCours.IdsElevesInscrits;

            var elevesAAjouter = elevesModifies.Except(elevesExistants).ToList();
            var elevesASupprimer = elevesExistants.Except(elevesModifies).ToList();

            foreach (var eleveId in elevesAAjouter)
            {
                var eleve = await MembreService.RecupererEleve(eleveId);
                eleve.IdsCoursInscrits.Add(nouvelleVersionDuCours.Id);
                await MembreService.ModifierEleve(eleve.Id, eleve);
            }

            foreach (var eleveId in elevesASupprimer)
            {
                var eleve = await MembreService.RecupererEleve(eleveId);
                eleve.IdsCoursInscrits.Remove(id);
                await MembreService.ModifierEleve(eleve.Id, eleve);
            }

            if (coursExistant.IdProfesseur != nouvelleVersionDuCours.IdProfesseur)
            {
                if (coursExistant.IdProfesseur != -2)
                {
                    var oldProfesseur = await MembreService.RecupererProfesseur(coursExistant.IdProfesseur);
                    oldProfesseur.IdsCoursDonnes.Remove(id);
                    await MembreService.ModifierProfesseur(oldProfesseur.Id, oldProfesseur);
                }

                if (nouvelleVersionDuCours.IdProfesseur != -2)
                {
                    var newProfesseur = await MembreService.RecupererProfesseur(nouvelleVersionDuCours.IdProfesseur);
                    newProfesseur.IdsCoursDonnes.Add(nouvelleVersionDuCours.Id);
                    await MembreService.ModifierProfesseur(newProfesseur.Id, newProfesseur);
                }
            }

            var nouveauNbPlacesDispos = nouvelleVersionDuCours.NombreDePlacesDisponibles;
            nouveauNbPlacesDispos = nouveauNbPlacesDispos - nouvelleVersionDuCours.IdsElevesInscrits.Count;
            nouvelleVersionDuCours.NombreDePlacesDisponibles = nouveauNbPlacesDispos;

            await this.CoursService.ModifierCours(id, nouvelleVersionDuCours);
            return nouvelleVersionDuCours;
        }

        public async Task<Cours?> SupprimerCours(int id)
        {
            var cours = await CoursService.SupprimerCours(id);

            if (cours != null)
            {
                // Mise à jour des élèves inscrits à ce cours
                foreach (var eleveId in cours.IdsElevesInscrits)
                {
                    var eleve = await MembreService.RecupererEleve(eleveId);
                    eleve.IdsCoursInscrits.Remove(id);
                    await MembreService.ModifierEleve(eleve.Id, eleve);
                }

                // Mise à jour du professeur donnant ce cours
                if (cours.IdProfesseur != -2)
                {
                    var professeur = await MembreService.RecupererProfesseur(cours.IdProfesseur);
                    professeur.IdsCoursDonnes.Remove(id);
                    await MembreService.ModifierProfesseur(professeur.Id, professeur);
                }
            }

            return cours;
        }

        // Gestion des élèves
        public async Task<Eleve> AjouterEleve(Eleve eleve)
        {
            return await MembreService.AjouterEleve(eleve);
        }

        public async Task<IEnumerable<Eleve>> RecupererTousLesEleves()
        {
            return await MembreService.RecupererTousLesEleves();
        }

        public async Task<Eleve> RecupererEleve(int id)
        {
            return await MembreService.RecupererEleve(id);
        }

        public async Task<Eleve> ModifierEleve(int id, Eleve updatedEleve)
        {
            var eleveActuel = await MembreService.RecupererEleve(id);
            var coursADesinscrireEleve = new List<int>();
            var coursAInscrireEleve = new List<int>();

            eleveActuel.IdsCoursInscrits.ForEach(idCours => {
                if (!updatedEleve.IdsCoursInscrits.Contains(idCours))
                {
                    coursADesinscrireEleve.Add(idCours);
                }
            });

            updatedEleve.IdsCoursInscrits.ForEach(idCours => {
                if (!eleveActuel.IdsCoursInscrits.Contains(idCours))
                {
                    coursAInscrireEleve.Add(idCours);
                }
            });

            foreach (var idCours in coursADesinscrireEleve)
            {
                var cours = await CoursService.RecupererCours(idCours);
                cours.IdsElevesInscrits.Remove(id);
                await CoursService.ModifierCours(idCours, cours);
            }

            foreach (var idCours in coursAInscrireEleve)
            {
                var cours = await CoursService.RecupererCours(idCours);
                cours.IdsElevesInscrits.Add(id);
                await CoursService.ModifierCours(idCours, cours);
            }

            return await MembreService.ModifierEleve(id, updatedEleve);
        }

        public async Task<Eleve?> SupprimerEleve(int id)
        {
            return await MembreService.SupprimerEleve(id);
        }

        // Gestion des professeurs
        public async Task<Professeur> AjouterProfesseur(Professeur professeur)
        {
            return await MembreService.AjouterProfesseur(professeur);
        }

        public async Task<IEnumerable<Professeur>> RecupererTousLesProfesseurs()
        {
            return await MembreService.RecupererTousLesProfesseurs();
        }

        public async Task<Professeur> RecupererProfesseur(int id)
        {
            return await MembreService.RecupererProfesseur(id);
        }

        public async Task<Professeur> ModifierProfesseur(int id, Professeur updatedProfesseur)
        {
            return await MembreService.ModifierProfesseur(id, updatedProfesseur);
        }

        public async Task<Professeur?> SupprimerProfesseur(int id)
        {
            return await MembreService.SupprimerProfesseur(id);
        }
    }
}