using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Components;
using MudBlazor;
using projet_jean_marcillac.Services.CoursService;
using CoursModele = projet_jean_marcillac.Modeles.Cours;

namespace projet_jean_marcillac.Composants.Cours.CoursDataGrid.TableauCours
{
    public partial class TableauCours
    {
        [Inject]
        protected IDialogService? DialogService { get; set; }

        [Parameter]
        public List<CoursModele>? Cours { get; set; }

        [Parameter]
        public EventCallback OnCoursModifie { get; set; }

        [Inject]
        protected ICoursService? CoursService { get; set; }
        
        private async Task OuvrirFenetreEditionCours(CoursModele cours)
        {
            if (DialogService == null) return;
            if (CoursService == null) return;
            this.Cours ??= new List<CoursModele>();

            var parameters = new DialogParameters { { "Cours", cours } };
            var options = new DialogOptions { MaxWidth = MaxWidth.Medium, FullWidth = true, CloseButton = true };

            var dialog = DialogService.Show<projet_jean_marcillac.Composants.Cours.
                CoursDataGrid.EditCoursDialog.EditCoursDialog>("Modifier le Cours", parameters, options);
            var resultat = await dialog.Result;

            if (resultat == null) return;

            if (!resultat.Canceled && resultat.Data is CoursModele coursModifie)
            {
                await CoursService.ModifierCours(coursModifie.Id, coursModifie);
                await OnCoursModifie.InvokeAsync();
            }
        }

        private async Task SupprimerCours(CoursModele cours)
        {
            if (CoursService == null) return;
            await CoursService.SupprimerCours(cours.Id);
            await OnCoursModifie.InvokeAsync();
        }
    }
}