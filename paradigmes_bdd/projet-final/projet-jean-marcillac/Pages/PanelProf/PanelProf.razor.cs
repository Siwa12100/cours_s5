using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Components;
using MudBlazor;
using projet_jean_marcillac.Composants.Cours.CoursDataGrid.EditCoursDialog;
using projet_jean_marcillac.Services.CoursService;
using CoursModele = projet_jean_marcillac.Modeles.Cours;


namespace projet_jean_marcillac.Pages.PanelProf
{
    public partial class PanelProf
    {
        [Inject]
        protected ICoursService? CoursService { get; set; }       

        protected List<CoursModele>? Cours { get; set; }

        protected override async Task OnInitializedAsync()
        {
            this.Cours = new List<CoursModele>();
            if (CoursService == null)
            {
                throw new InvalidOperationException("CoursService est null");
            }

            var tousLesCours = await CoursService.RecupererTousLesCours();
            Cours = tousLesCours.ToList();
        }

        protected async Task OnCoursModifieAsync()
        {
            Console.WriteLine("Passage dans OnCoursModifieAsync");
            if (CoursService == null)
            {
                throw new InvalidOperationException("CoursService est null");
            }
            var reponse = await this.CoursService.RecupererTousLesCours();
            this.Cours = reponse.ToList();
            StateHasChanged();
        }
    }
}
