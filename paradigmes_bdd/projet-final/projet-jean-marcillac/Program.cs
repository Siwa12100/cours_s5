using Microsoft.AspNetCore.Components;
using Microsoft.AspNetCore.Components.Web;
using projet_jean_marcillac.Services.CoursService;

var builder = WebApplication.CreateBuilder(args);
builder.Services.AddRazorPages();
builder.Services.AddServerSideBlazor();
builder.Services.AddSingleton(sp => new RedisService("149.7.5.30", 6379, "senhal"));
builder.Services.AddSingleton<ICoursService, CoursService>();

var app = builder.Build();

if (!app.Environment.IsDevelopment())
{
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}

app.UseHttpsRedirection();

app.UseStaticFiles();

app.UseRouting();

app.MapBlazorHub();
app.MapFallbackToPage("/_Host");

app.Run();
