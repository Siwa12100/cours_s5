using StackExchange.Redis;
using System.Collections.Generic;
using System.Numerics;
using System.Threading.Tasks;

namespace projet_jean_marcillac.Services.CoursService
{
    public class CoursService : ICoursService
    {
        private readonly RedisService _redisService;

        public CoursService(RedisService redisService)
        {
            _redisService = redisService;
        }

        public async Task<Cours> AjouterCours(Cours cours)
        {
            await _redisService.Database.HashSetAsync($"cours:{cours.Id}", cours.ToHashEntries());
            return cours;
        }

        public async Task<IEnumerable<Cours>> RecupererTousLesCours()
        {
            var serveur = _redisService.Server;
            var cours = new List<Cours>();
            var cles = serveur.Keys(pattern: "cours:*").ToList();

            foreach (var cle in cles)
            {
                var hashEntries = await _redisService.Database.HashGetAllAsync(cle);
                cours.Add(new Cours(hashEntries));
            }

            return cours;
        }

        public async Task<Cours> RecupererCours(int id)
        {
            var hashEntries = await _redisService.Database.HashGetAllAsync($"cours:{id}");
            return new Cours(hashEntries);

        }

        public async Task<Cours> ModifierCours(int id, Cours updatedCours)
        {
            await _redisService.Database.HashSetAsync($"cours:{id}", updatedCours.ToHashEntries());
            return updatedCours;

        }

        public async Task<Cours?> SupprimerCours(int id)
        {
                var cours = await RecupererCours(id);
                await _redisService.Database.KeyDeleteAsync($"cours:{id}");
                return cours;
        }
    }
}
