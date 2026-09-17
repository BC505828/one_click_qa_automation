package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.AmountPage;

public class AmountStep {

    private AmountPage amountPage;

    @Before
    public void inicializar() {
        amountPage = new AmountPage();
    }

    @When("desplizo el elemento {string} desde {int} hasta {int}")
    public void desplizoElElementoDesdeHasta(String element, int argA, int argB) {
        amountPage.desplazarSlider(element, argA, argB);
    }

    @When("doy clic en el elemento {string} pagina selecciona el monto")
    public void doyClicEnElElementoPaginaSeleccionaElMonto(String element) throws InterruptedException {
        amountPage.clickElementsAmountPage(element);
    }

    @Then("debo visualizar los elementos {string} de la pagina selecciona un monto paso 2")
    public void deboVisualizarElementosPaginaSeleccionaMonto(String page) {
        amountPage.validateAmountPage(page);
    }
}
