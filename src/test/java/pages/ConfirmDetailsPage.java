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
    private final By btnavisoPrivacidad = By.xpath("//input[@id='avisoPrivacidad']");
    private final By btnContinuar = By.cssSelector(".MuiButton-root");


    public void clickElementsConfirmPage(String element) {
        Map<String, By> elementMap = new HashMap<>();
        elementMap.put("solicitalo aqui", inputCurp);
        elementMap.put("privacidad", btnavisoPrivacidad);
        elementMap.put("continuar", btnContinuar);

        By locator = elementMap.get(element);
        tapElementBase(locator);
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
