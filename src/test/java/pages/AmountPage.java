package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.BasePage;

import java.util.HashMap;
import java.util.Map;

public class AmountPage extends BasePage {

    //Elementos pagina Paso 2
    private final By txtPaso2 = By.xpath("//h6[contains(.,'Paso 2')]");
    private final By txtDescripcion1 = By.xpath("//p[contains(.,'Recibe tu préstamo de manera ágil y sin esfuerzos')]");
    private final By txtDescripcion2 = By.xpath("//p[contains(.,'Monto pre-aprobado:')]");
    private final By txtMonto = By.cssSelector(".MuiTypography-h4");
    private final By btnSeleccionarMonto = By.cssSelector(".MuiSlider-rail.css-1m3hq23");
    private final By btnPlazo = By.name("plazo");
    private final By txtSecciMensual = By.name("//p[contains(.,'Tu pago mensual sería de:')]");
    private final By inputMnsualidad = By.name(".css-obhz4r");
    private final By btnContinuar = By.cssSelector(".css-1p3qsoh");


    public void desplazarSlider(String element, double porcentajeInicial, double porcentajeFinal) {
        Map<String, By> elementMap = new HashMap<>();
        elementMap.put("monto", btnSeleccionarMonto);

        By locator = elementMap.get(element);
        WebElement slider = find(locator);

        swipeHorizontal(
                50,
                porcentajeInicial,
                porcentajeFinal,
                slider
        );
    }

    public void clickElementsAmountPage(String element) throws InterruptedException {
        Map<String, By> elementMap = new HashMap<>();
        elementMap.put("continuar", btnContinuar);

        By locator = elementMap.get(element);
        //tapElementBase(locator);
        isCheckEnable(locator);
    }

    public void validateAmountPage(String page) {
        switch (page) {
            case "selecciona el monto":
                waitUntilVisible(txtDescripcion1, 240);
                validateVisibility(txtDescripcion2, txtMonto, btnSeleccionarMonto, btnPlazo);
                break;

            default:
                throw new IllegalArgumentException(
                        "Página no reconocida: " + page
                );
        }
    }

}
