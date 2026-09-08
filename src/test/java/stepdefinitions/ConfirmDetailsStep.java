package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.ConfirmDetailsPage;

public class ConfirmDetailsStep {

    private ConfirmDetailsPage confirmDetails;

    @Given("doy clic en el elemento {string} pagina ingresar informacion")
    public void clickElementoIngresarInformacion(String element) {
        confirmDetails.clickElementsConfirmPage(element);
    }

    @When("ingreso {string} en el campo {string} de la pagina ingresar informacion")
    public void ingresoEnCampoIngresarInformacion(String text, String input) {
        confirmDetails.enterTextInElement(text, input);
    }
}
