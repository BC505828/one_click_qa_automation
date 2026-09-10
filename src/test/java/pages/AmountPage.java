package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.BasePage;

public class AmountPage extends BasePage {

    //Elementos pagina Paso 2
    private final By txtPaso2 = By.xpath("//h6[contains(.,'Paso 2')]");
    private final By txtDescripcion1 = By.xpath("//p[contains(.,'Recibe tu préstamo de manera ágil y sin esfuerzos')]");
    private final By txtDescripcion2 = By.xpath("//p[contains(.,'Monto pre-aprobado:')]");
    private final By txtMonto = By.cssSelector(".MuiTypography-h4");
    private final By btnSeleccionarMonto = By.cssSelector(".MuiSlider-thumb");
    private final By btnPlazo = By.name("plazo");
    private final By txtSecciMensual = By.name("//p[contains(.,'Tu pago mensual sería de:')]");
    private final By inputMnsualidad = By.name(".css-obhz4r");
    private final By btnContinuar = By.name(".css-obhz4r");


    public void desplazarSlider(
            By locator,
            double porcentajeInicial,
            double porcentajeFinal
    ) {
        WebElement slider = find(locator);

        swipeHorizontal(
                50,
                porcentajeInicial,
                porcentajeFinal,
                slider
        );
    }


}
