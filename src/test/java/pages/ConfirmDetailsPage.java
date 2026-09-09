package pages;

import drivers.DriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BasePage;

import java.util.HashMap;
import java.util.Map;

public class ConfirmDetailsPage extends BasePage {

    private WebDriver driver;

    public ConfirmDetailsPage(){
        this.driver = new DriverProvider().get();
    }

    private final By inputCurp = By.id("curp");
    private final By btnPrivacidad = By.name("avisoPrivacidad");
    private final By txtAreaCurp = By.cssSelector(".MuiGrid2-root:nth-child(3)");
    private final By btnContinuar = By.cssSelector(".MuiButton-root");
    private final By lblMensajeError = By.xpath("//p[contains(.,'La CURP que ingresaste no es válida, favor de volver a intentar')]");


    public void clickElementsConfirmPage(String element) throws InterruptedException {
        Map<String, By> elementMap = new HashMap<>();
        elementMap.put("solicitalo aqui", inputCurp);
        elementMap.put("privacidad", btnPrivacidad);
        elementMap.put("continuar", btnContinuar);
        elementMap.put("area", txtAreaCurp);

        By locator = elementMap.get(element);
        tapElementBase(locator);
    }

    public void validateConfirmDetails(String page) {
        switch (page) {
            case "curp_invalida":
                waitUntilPresent(lblMensajeError, 240);
                validateVisibility(lblMensajeError);
                break;
            default:
                throw new IllegalArgumentException(
                        "Página no reconocida: " + page
                );
        }
    }

    public void enterTextInElement(String text, String element){
        Map<String, By> elementMap = new HashMap<>();
        elementMap.put("CURP", inputCurp);

        By locator = elementMap.get(element);
        if (element != null) {
            inputText(locator, text);
        } else {
            System.out.println("Elemento no reconocido: " + element);
        }
    }
}
