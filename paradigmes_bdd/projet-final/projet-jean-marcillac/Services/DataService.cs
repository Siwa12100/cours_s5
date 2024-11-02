using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using projet_jean_marcillac.Modeles;
using projet_jean_marcillac.Services.CoursService;
using projet_jean_marcillac.Services.MembreService;

namespace projet_jean_marcillac.Services
{
    public class DataService : IMembreService, ICoursService
    {
        protected readonly IMembreService _membreService;
        protected readonly ICoursService _coursService;

        public DataService(IMembreService membreService, ICoursService coursService)
        {
            _membreService = membreService;
            _coursService = coursService;
        }

        // Gestion des cours
        public async Task<Cours> AjouterCours(Cours cours)
        {
            var addedCours = await _coursService.AjouterCours(cours);

            // Mise à jour des élèves inscrits à ce cours
            foreach (var eleveId in cours.IdsElevesInscrits)
            {
                var eleve = await _membreService.RecupererEleve(eleveId);
                eleve.IdsCoursInscrits.Add(cours.Id);
                await _membreService.ModifierEleve(eleve.Id, eleve);
            }

            // Mise à jour du professeur donnant ce cours
            if (cours.IdProfesseur != -2)
            {
                var professeur = await _membreService.RecupererProfesseur(cours.IdProfesseur);
                professeur.IdsCoursDonnes.Add(cours.Id);
                await _membreService.ModifierProfesseur(professeur.Id, professeur);
            }

            return addedCours;
        }

        public async Task<IEnumerable<Cours>> RecupererTousLesCours()
        {
            return await _coursService.RecupererTousLesCours();
        }

        public async Task<Cours> RecupererCours(int id)
        {
            return await _coursService.RecupererCours(id);
        }

        public async Task<Cours> ModifierCours(int id, Cours updatedCours)
        {
            var existingCours = await _coursService.RecupererCours(id);
            var modifiedCours = await _coursService.ModifierCours(id, updatedCours);

            // Mise à jour des élèves inscrits à ce cours
            var existingEleves = existingCours.IdsElevesInscrits;
            var updatedEleves = updatedCours.IdsElevesInscrits;

            var elevesToAdd = updatedEleves.Except(existingEleves).ToList();
            var elevesToRemove = existingEleves.Except(updatedEleves).ToList();

            foreach (var eleveId in elevesToAdd)
            {
                var eleve = await _membreService.RecupererEleve(eleveId);
                eleve.IdsCoursInscrits.Add(updatedCours.Id);
                await _membreService.ModifierEleve(eleve.Id, eleve);
            }

            foreach (var eleveId in elevesToRemove)
            {
                var eleve = await _membreService.RecupererEleve(eleveId);
                eleve.IdsCoursInscrits.Remove(id);
                await _membreService.ModifierEleve(eleve.Id, eleve);
            }

            // Mise à jour du professeur donnant ce cours
            if (existingCours.IdProfesseur != updatedCours.IdProfesseur)
            {
                if (existingCours.IdProfesseur != -2)
                {
                    var oldProfesseur = await _membreService.RecupererProfesseur(existingCours.IdProfesseur);
                    oldProfesseur.IdsCoursDonnes.Remove(id);
                    await _membreService.ModifierProfesseur(oldProfesseur.Id, oldProfesseur);
                }

                if (updatedCours.IdProfesseur != -2)
                {
                    var newProfesseur = await _membreService.RecupererProfesseur(updatedCours.IdProfesseur);
                    newProfesseur.IdsCoursDonnes.Add(updatedCours.Id);
                    await _membreService.ModifierProfesseur(newProfesseur.Id, newProfesseur);
                }
            }

            return modifiedCours;
        }

        public async Task<Cours?> SupprimerCours(int id)
        {
            var cours = await _coursService.SupprimerCours(id);

            if (cours != null)
            {
                // Mise à jour des élèves inscrits à ce cours
                foreach (var eleveId in cours.IdsElevesInscrits)
                {
                    var eleve = await _membreService.RecupererEleve(eleveId);
                    eleve.IdsCoursInscrits.Remove(id);
                    await _membreService.ModifierEleve(eleve.Id, eleve);
                }

                // Mise à jour du professeur donnant ce cours
                if (cours.IdProfesseur != -2)
                {
                    var professeur = await _membreService.RecupererProfesseur(cours.IdProfesseur);
                    professeur.IdsCoursDonnes.Remove(id);
                    await _membreService.ModifierProfesseur(professeur.Id, professeur);
                }
            }

            return cours;
        }

        // Gestion des élèves
        public async Task<Eleve> AjouterEleve(Eleve eleve)
        {
            return await _membreService.AjouterEleve(eleve);
        }

        public async Task<IEnumerable<Eleve>> RecupererTousLesEleves()
        {
            return await _membreService.RecupererTousLesEleves();
        }

        public async Task<Eleve> RecupererEleve(int id)
        {
            return await _membreService.RecupererEleve(id);
        }

        public async Task<Eleve> ModifierEleve(int id, Eleve updatedEleve)
        {
            return await _membreService.ModifierEleve(id, updatedEleve);
        }

        public async Task<Eleve?> SupprimerEleve(int id)
        {
            return await _membreService.SupprimerEleve(id);
        }

        // Gestion des professeurs
        public async Task<Professeur> AjouterProfesseur(Professeur professeur)
        {
            return await _membreService.AjouterProfesseur(professeur);
        }

        public async Task<IEnumerable<Professeur>> RecupererTousLesProfesseurs()
        {
            return await _membreService.RecupererTousLesProfesseurs();
        }

        public async Task<Professeur> RecupererProfesseur(int id)
        {
            return await _membreService.RecupererProfesseur(id);
        }

        public async Task<Professeur> ModifierProfesseur(int id, Professeur updatedProfesseur)
        {
            return await _membreService.ModifierProfesseur(id, updatedProfesseur);
        }

        public async Task<Professeur?> SupprimerProfesseur(int id)
        {
            return await _membreService.SupprimerProfesseur(id);
        }
    }
}