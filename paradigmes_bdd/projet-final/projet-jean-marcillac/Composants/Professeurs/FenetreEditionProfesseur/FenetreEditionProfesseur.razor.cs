using Microsoft.AspNetCore.Components;
using MudBlazor;
using projet_jean_marcillac.Modeles;

namespace projet_jean_marcillac.Composants.Professeurs.FenetreEditionProfesseur
{
    public partial class FenetreEditionProfesseur
    {
        [CascadingParameter]
        MudDialogInstance? MudDialog { get; set; }

        [Parameter]
        public Professeur Professeur { get; set; } = new Professeur();

        protected void Annuler()
        {
            MudDialog?.Cancel();
        }

        protected void Sauvegarder()
        {
            MudDialog?.Close(DialogResult.Ok(Professeur));
        }
    }
}