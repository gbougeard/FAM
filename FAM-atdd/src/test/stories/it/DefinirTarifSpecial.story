using "thucydides"

//import net.thucydides.demos.jobboard.requirements.JobBoardApplication.ManageCategories.AddNewCategory
//import net.thucydides.demos.jobboard.steps.AdministratorSteps

thucydides.uses_default_base_url "http://prodrit"
thucydides.uses_steps_from TarifSpeciauxSteps
thucydides.tests_story AjouterUnNouveauTarifSpecial

scenario "L'utilisateur ajoute un tarif special",
{
	given "l'utilisateur est sur l'ecran des tarifs speciaux"
	when "l'utilisateur ajoute un tarif special"
    then "le tarif special est bien affiché dans la liste des tarifs speciaux"
}
