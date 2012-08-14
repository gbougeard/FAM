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

/*> Cliquer sur la carto Logis
  > Cliquer sur une région
  > Cliquer sur un département
  > On obtient la carto Via Michelin (nos pages)
  > En bas de la carto, il y a la liste des villes sur lesquelles il y a un logis
  > Lorsque l'on clique dessus --> 2686 Logis correspodnent à votre requete. Vueillez affiner
*/
