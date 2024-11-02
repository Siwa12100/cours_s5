using Microsoft.AspNetCore.Components;
using projet_jean_marcillac.Services.CoursService;
using projet_jean_marcillac.Services.MembreService;

namespace projet_jean_marcillac.Pages.PanelAdmin
{
    public partial class PanelAdmin
    {
        [Inject]
        protected ICoursService? CoursService { get; set; }
        protected IMembreService? MembreService { get; set; }
    }
}