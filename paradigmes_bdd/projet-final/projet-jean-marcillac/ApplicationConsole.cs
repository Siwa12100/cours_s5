namespace projet_jean_marcillac
{
    public class ApplicationConsole
    {
        public void Lancement(RedisService redisService)
        {
            Console.WriteLine("Debut de l'application console ! ");

            redisService.Database.StringSet("cle1", "Test de stockage d'un string dans Redis");
            string? msg = redisService.Database.StringGet("cle1");
            Console.WriteLine(msg);

            var db = redisService.Database;
            var cours1 = new Cours(1, "Cours de Redis", "Cours sur Redis", 10, "Contenu du cours sur Redis");
            var cours2 = new Cours(2, "Cours de C#", "Cours sur C#", 10, "Contenu du cours sur C#");

            db.HashSet("cours:1", cours1.ToHashEntries());
            db.HashSet("cours:2", cours2.ToHashEntries());

            Console.WriteLine("Cours enregistrés ! ");

            var resultat = db.HashGetAll("cours:*");

            var res1 = db.HashGet("cours:1", "Titre");
            var res2 = db.HashGetAll("cours:2");

            Console.WriteLine("Resultat 1 : " + res1.ToString());

            var serveur = redisService.Server;
            serveur.Keys(pattern: "cours:*").ToList().ForEach(key => Console.WriteLine(" - " + key.ToString()));

            foreach (var hashEntry in res2)
            {
                Console.WriteLine($"{hashEntry.Name} : {hashEntry.Value}");
            }

            // if (resultat != null)
            // {
            //     Console.WriteLine("Resultat non null");
            //     int cpt = 1;
            //     foreach (var hashEntry in resultat)
            //     {
            //         Console.WriteLine($"Cours {cpt}");
            //         Console.WriteLine($"{hashEntry.Name} : {hashEntry.Value}");
            //     }
            // }
            // else {
            //     Console.WriteLine("Aucun cours trouvé dans Redis");
            // }

        }
    }
}