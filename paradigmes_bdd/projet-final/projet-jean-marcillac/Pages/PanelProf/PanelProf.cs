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

        [Inject]
        protected IDialogService? DialogService { get; set; }

        

        protected List<CoursModele> Cours { get; set; } = new List<CoursModele>();

        protected override async Task OnInitializedAsync()
        {
            if (CoursService == null)
            {
                throw new InvalidOperationException("CoursService est null");
            }

            var tousLesCours = await CoursService.RecupererTousLesCours();
            Cours = tousLesCours.ToList();
        }

        private async Task OpenEditCourseDialog(CoursModele cours)
        {
            if (DialogService == null) return;

            var parameters = new DialogParameters { { "Cours", cours } };
            var options = new DialogOptions { MaxWidth = MaxWidth.Medium, FullWidth = true, CloseButton = true };

            var dialog = DialogService.Show<EditCoursDialog>("Modifier le Cours", parameters, options);
            var result = await dialog.Result;

            if (!result.Canceled && result.Data is CoursModele updatedCours)
            {
                var existingCourse = Cours.FirstOrDefault(c => c.Id == updatedCours.Id);
                if (existingCourse != null)
                {
                    existingCourse.Titre = updatedCours.Titre;
                    existingCourse.Resume = updatedCours.Resume;
                    existingCourse.Contenu = updatedCours.Contenu;
                    existingCourse.NombreDePlacesDisponibles = updatedCours.NombreDePlacesDisponibles;
                }
            }
        }
    }
}
