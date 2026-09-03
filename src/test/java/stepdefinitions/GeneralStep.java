package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import utilities.BasePage;

public class GeneralStep extends BasePage {

    @And("pausa {int} segundos")
    public void iPauseSecond(int second) {
        sleep(second);
    }

    @And("Desplazo hacia abajo hasta donde esta el elemento {string}")
    public void scrollDownElemen(String text) {
        scrollDown(text);
    }

    @When("Desplazo hacia abajo {int}")
    public void scrollUpXY(Integer alto) {
        scrollUp(alto);
    }

    @And("Ocultar teclado")
    public void hideKey() throws InterruptedException {
        getDriver().hideKeyboard();
        Thread.sleep(1000);
    }
}
