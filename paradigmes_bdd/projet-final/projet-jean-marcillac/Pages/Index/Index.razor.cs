using Microsoft.AspNetCore.Components;

namespace projet_jean_marcillac.Pages.Index
{
    public partial class Index
    {
        [Inject]
        protected RedisService? RedisService { get; set; }
        protected ApplicationConsole? applicationConsole;
        protected static bool appConsoleLancee = false;
        protected string message = "Hello, World!";

        protected override async Task OnInitializedAsync()
        {
            applicationConsole = new ApplicationConsole();
            if (RedisService != null)
            {
                if (!appConsoleLancee)
                {
                    applicationConsole.Lancement(RedisService);
                    appConsoleLancee = true;
                }
            }
            else
            {
                throw new InvalidOperationException("RedisService non injecté.");
            }

            await base.OnInitializedAsync();
        }
    }
}