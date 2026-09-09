package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import pages.ConfirmDetailsPage;

public class ConfirmDetailsStep {

    private ConfirmDetailsPage confirmDetails;

    @Before
    public void inicializar() {
        confirmDetails = new ConfirmDetailsPage();
    }

    @Given("doy clic en el elemento {string} pagina ingresar informacion")
    public void clickElementoIngresarInformacion(String element) throws InterruptedException {
        confirmDetails.clickElementsConfirmPage(element);
    }

    @When("ingreso {string} en el campo {string} de la pagina ingresar informacion")
    public void ingresoEnCampoIngresarInformacion(String text, String input) {
        confirmDetails.enterTextInElement(text, input);
    }

    @Then("debo visualizar los elementos {string} de la pagina captura de informacion paso 1")
    public void deboVisualizarElementosPaginaCaptura(String element) throws InterruptedException {
        confirmDetails.validateConfirmDetails(element);
    }

}
