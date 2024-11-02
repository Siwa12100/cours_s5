using Microsoft.AspNetCore.Components;
using projet_jean_marcillac.Modeles;
using projet_jean_marcillac.Services.CoursService;
using projet_jean_marcillac.Services.MembreService;

namespace projet_jean_marcillac.Pages.PanelAdmin
{
    public partial class PanelAdmin
    {
        [Inject]
        protected ICoursService? CoursService { get; set; }

        [Inject]
        protected RedisService? RedisService { get; set; }

        [Inject]
        protected IMembreService? MembreService { get; set; }

        protected List<Professeur>? Professeurs { get; set; }

        protected StubData? stubData;

        protected override async Task OnInitializedAsync()
        {
            if (this.RedisService == null)
            {
                throw new ArgumentNullException(nameof(RedisService));
            }

            this.stubData = new StubData(new Services.DataService(this.RedisService));

            // await this.stubData.SupprimerToutesLesDonnees();
            // await this.stubData.ChargerStubProjet();

            if (this.MembreService == null)
            {
                throw new ArgumentNullException(nameof(MembreService));
            }

            var profsResultat = await MembreService.RecupererTousLesProfesseurs();
            if (profsResultat == null)
            {
                throw new ArgumentNullException(nameof(profsResultat));
            }
            this.Professeurs = profsResultat.ToList();
        }

        protected async Task ProfesseurSelectionChanged()
        {
            Console.WriteLine("Event changement prof levé ! ");

            if (this.MembreService == null)
            {
                throw new ArgumentNullException(nameof(MembreService));
            }

            var profsResultat = await MembreService.RecupererTousLesProfesseurs();
            if (profsResultat == null)
            {
                throw new ArgumentNullException(nameof(profsResultat));
            }
            this.Professeurs = profsResultat.ToList();
            StateHasChanged();
        }
    }
}