using Microsoft.AspNetCore.Components;
using MudBlazor;
using projet_jean_marcillac.Modeles;
using System.Text.RegularExpressions;

namespace projet_jean_marcillac.Composants.Professeurs.FenetreEditionProfesseur
{
    public partial class FenetreEditionProfesseur
    {
        [CascadingParameter]
        MudDialogInstance? MudDialog { get; set; }

        [Parameter]
        public Professeur Professeur { get; set; } = new Professeur();

        private bool nomErreur = false;
        private bool prenomErreur = false;
        private bool nomTouched = false; // Suivi de l'interaction avec le champ "Nom"
        private bool prenomTouched = false; // Suivi de l'interaction avec le champ "Prénom"
        private string nomErreurTexte = string.Empty;
        private string prenomErreurTexte = string.Empty;

        private bool FormulaireValide()
        {
            ValidateNom();
            ValidatePrenom();
            return !nomErreur && !prenomErreur;
        }

        protected void Annuler()
        {
            MudDialog?.Cancel();
        }

        protected void Sauvegarder()
        {
            if (FormulaireValide())
            {
                MudDialog?.Close(DialogResult.Ok(Professeur));
            }
        }

        private void ValidateNom()
        {
            nomTouched = true; // Marque le champ comme "touché" après la première validation
            var value = Professeur.Nom;
            if (string.IsNullOrWhiteSpace(value))
            {
                nomErreur = true;
                nomErreurTexte = "Le nom est requis.";
            }
            else if (!Regex.IsMatch(value, @"^[a-zA-ZÀ-ÖØ-öø-ÿ\s-]+$"))
            {
                nomErreur = true;
                nomErreurTexte = "Le nom ne doit contenir que des lettres et des espaces.";
            }
            else
            {
                nomErreur = false;
                nomErreurTexte = string.Empty;
            }
        }

        private void ValidatePrenom()
        {
            prenomTouched = true; // Marque le champ comme "touché" après la première validation
            var value = Professeur.Prenom;
            if (string.IsNullOrWhiteSpace(value))
            {
                prenomErreur = true;
                prenomErreurTexte = "Le prénom est requis.";
            }
            else if (!Regex.IsMatch(value, @"^[a-zA-ZÀ-ÖØ-öø-ÿ\s-]+$"))
            {
                prenomErreur = true;
                prenomErreurTexte = "Le prénom ne doit contenir que des lettres et des espaces.";
            }
            else
            {
                prenomErreur = false;
                prenomErreurTexte = string.Empty;
            }
        }
    }
}
