using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Components;
using MudBlazor;
using CoursModele = projet_jean_marcillac.Modeles.Cours;

namespace projet_jean_marcillac.Composants.Cours.CoursDataGrid.EditCoursDialog
{
    public partial class EditCoursDialog
    {
        [CascadingParameter]
        protected MudDialogInstance? MudDialog { get; set; }

        [Parameter]
        public CoursModele Cours { get; set; } = new CoursModele();

        private void SaveChanges()
        {
            MudDialog?.Close(DialogResult.Ok(Cours));
        }

        private void Cancel()
        {
            MudDialog?.Cancel();
        }
    }
}