package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.HomePage;

public class HomeStep {

    private HomePage homePage;


    @Given("ingresar a la pagina One Click")
    public void IngresarHome(){
        homePage = new HomePage();
        homePage.abrirPagina();
    }

    @Then("debo visualizar los elementos de la pagina {string}")
    public void validateElements(String page){
        homePage.validateHomePage(page);
    }

}
