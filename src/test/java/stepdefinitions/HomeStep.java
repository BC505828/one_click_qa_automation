package stepdefinitions;

import io.cucumber.java.en.Given;
import pages.HomePage;

public class HomeStep {

    private HomePage homePage;


    @Given("ingresar a la pagina One Click")
    public void IngresarHome(){
        homePage = new HomePage();
        homePage.abrirPagina();
    }


}
