using projet_jean_marcillac.Modeles;
using StackExchange.Redis;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace projet_jean_marcillac.Services.MembreService
{
    public class MembreService : IMembreService
    {
        private readonly RedisService redisService;

        public MembreService(RedisService redisService)
        {
            this.redisService = redisService;
        }

        public async Task<Eleve> AjouterEleve(Eleve eleve)
        {
            await redisService.Database.HashSetAsync($"eleve:{eleve.Id}", eleve.ToHashEntries());
            return eleve;
        }

        public async Task<IEnumerable<Eleve>> RecupererTousLesEleves()
        {
            var serveur = redisService.Server;
            var eleves = new List<Eleve>();
            var cles = serveur.Keys(pattern: "eleve:*").ToList();

            foreach (var cle in cles)
            {
                var hashEntries = await redisService.Database.HashGetAllAsync(cle);
                eleves.Add(new Eleve(hashEntries));
            }

            return eleves;
        }

        public async Task<Eleve> RecupererEleve(int id)
        {
            var hashEntries = await redisService.Database.HashGetAllAsync($"eleve:{id}");
            return new Eleve(hashEntries);
        }

        public async Task<Eleve> ModifierEleve(int id, Eleve updatedEleve)
        {
            await redisService.Database.HashSetAsync($"eleve:{id}", updatedEleve.ToHashEntries());
            return updatedEleve;
        }

        public async Task<Eleve?> SupprimerEleve(int id)
        {
            var eleve = await RecupererEleve(id);
            await redisService.Database.KeyDeleteAsync($"eleve:{id}");
            return eleve;
        }

        // Gestion des professeurs
        public async Task<Professeur> AjouterProfesseur(Professeur professeur)
        {
            await redisService.Database.HashSetAsync($"professeur:{professeur.Id}", professeur.ToHashEntries());
            return professeur;
        }

        public async Task<IEnumerable<Professeur>> RecupererTousLesProfesseurs()
        {
            var serveur = redisService.Server;
            var professeurs = new List<Professeur>();
            var cles = serveur.Keys(pattern: "professeur:*").ToList();

            foreach (var cle in cles)
            {
                var hashEntries = await redisService.Database.HashGetAllAsync(cle);
                professeurs.Add(new Professeur(hashEntries));
            }

            return professeurs;
        }

        public async Task<Professeur> RecupererProfesseur(int id)
        {
            var hashEntries = await redisService.Database.HashGetAllAsync($"professeur:{id}");
            return new Professeur(hashEntries);
        }

        public async Task<Professeur> ModifierProfesseur(int id, Professeur updatedProfesseur)
        {
            await redisService.Database.HashSetAsync($"professeur:{id}", updatedProfesseur.ToHashEntries());
            return updatedProfesseur;
        }

        public async Task<Professeur?> SupprimerProfesseur(int id)
        {
            var professeur = await RecupererProfesseur(id);
            await redisService.Database.KeyDeleteAsync($"professeur:{id}");
            return professeur;
        }
    }
}