using projet_jean_marcillac.Services.CoursService;

namespace projet_jean_marcillac
{
    public class ApplicationConsole
    {
        public async Task Lancement(RedisService redisService, ICoursService coursService)
        {
            Console.WriteLine("Debut de l'application console ! ");
            // await tests1(redisService, coursService);


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