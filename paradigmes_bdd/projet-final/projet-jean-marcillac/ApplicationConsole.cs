using projet_jean_marcillac.Modeles;
using projet_jean_marcillac.Services.CoursService;

namespace projet_jean_marcillac
{
    public class ApplicationConsole
    {
        public async Task Lancement(RedisService redisService, ICoursService coursService)
        {
            Console.WriteLine("Debut de l'application console ! ");
            // await tests1(redisService, coursService);

            var cours1 = new Cours(1, "Cours de Redis", "Cours sur Redis", 10, "Contenu du cours sur Redis");
            var cours2 = new Cours(2, "Cours de C#", "Cours sur C#", 10, "Contenu du cours sur C#");
            var cours3 = new Cours(3, "Cours de C++", "Cours sur C++", 10, "Contenu du cours sur C++");
            var cours4 = new Cours(4, "Cours de Java", "Cours sur Java", 10, "Contenu du cours sur Java");
            var cours5 = new Cours(5, "Cours de Python", "Cours sur Python", 10, "Contenu du cours sur Python");
            var cours6 = new Cours(6, "Cours de JavaScript", "Cours sur JavaScript", 10, "Contenu du cours sur JavaScript");
            var cours7 = new Cours(7, "Cours de TypeScript", "Cours sur TypeScript", 10, "Contenu du cours sur TypeScript");
            var cours8 = new Cours(8, "Cours de PHP", "Cours sur PHP", 10, "Contenu du cours sur PHP");
            var cours9 = new Cours(9, "Cours de HTML", "Cours sur HTML", 10, "Contenu du cours sur HTML");
            var cours10 = new Cours(10, "Cours de CSS", "Cours sur CSS", 10, "Contenu du cours sur CSS");

            await coursService.AjouterCours(cours3);
            await coursService.AjouterCours(cours4);
            await coursService.AjouterCours(cours5);
            await coursService.AjouterCours(cours6);
            await coursService.AjouterCours(cours7);
            await coursService.AjouterCours(cours8);
            await coursService.AjouterCours(cours9);
            await coursService.AjouterCours(cours10);

            Console.WriteLine("Fin de l'application console ! ");

        }

        public async Task tests1(RedisService redisService, ICoursService coursService)
        {
            redisService.Database.StringSet("cle1", "Test de stockage d'un string dans Redis");
            string? msg = redisService.Database.StringGet("cle1");
            Console.WriteLine(msg);

            var db = redisService.Database;
            var cours1 = new Cours(1, "Cours de Redis", "Cours sur Redis", 10, "Contenu du cours sur Redis");
            var cours2 = new Cours(2, "Cours de C#", "Cours sur C#", 10, "Contenu du cours sur C#");

            await coursService.AjouterCours(cours1);
            await coursService.AjouterCours(cours2);

            Console.WriteLine("Cours enregistrés ! ");

            var resultat = await coursService.RecupererTousLesCours();

            resultat.ToList().ForEach(cours => Console.WriteLine(cours));

            await coursService.SupprimerCours(1);

            Console.WriteLine("Cours 1 supprimé ! ");

            resultat = await coursService.RecupererTousLesCours();

            resultat.ToList().ForEach(cours => Console.WriteLine(cours));

            foreach (var cours in resultat.ToList())
            {
                await coursService.SupprimerCours(cours.Id);
            }
        }
    }
}