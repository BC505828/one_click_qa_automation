package pages;

import drivers.DriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BasePage;
import utilities.ConfigReader;

import java.util.HashMap;
import java.util.Map;

public class HomePage extends BasePage {

    private WebDriver driver;

    private final By imgLogo = By.cssSelector(".css-3052zt img");
    private final By imgBienvenidos = By.xpath("//h4[contains(.,'¡Bienvenido a Profuturo préstamos!')]");
    private final By btnSolicitaAqui = By.xpath("//p[contains(.,'Solicítalo aquí')]");
    private final By btnSolicitaA = By.cssSelector(".css-c0jx4o");

    //paso1

    private final By imgLogop = By.cssSelector(".css-1qin9n4");
    private final By ingingresatucurp = By.xpath("//h5[contains(.,'Ingresa tu CURP para localizarte')]");
    private final By iptcuadrocurp = By.id("curp");
    private final By txtheleido = By.cssSelector(".css-jvjn7y");


    public HomePage() {
        this.driver = new DriverProvider().get();
    }


    public void validateHomePage(String page) {
        switch (page) {
            case "inicio":
                waitUntilVisible(imgLogo, 240);
                validateVisibility(imgBienvenidos, btnSolicitaAqui);
                break;

                case "paso1":
                waitUntilVisible(imgLogop, 240);
                validateVisibility(ingingresatucurp, iptcuadrocurp, txtheleido);
                break;

            default:
                throw new IllegalArgumentException(
                        "Página no reconocida: " + page
                );
        }
    }

    public void clickElementsHomePage(String element) throws InterruptedException {
        Map<String, By> elementMap = new HashMap<>();
        elementMap.put("solicitalo aqui", btnSolicitaA);

        By locator = elementMap.get(element);
        tapElementBase(locator);
    }

    public void abrirPagina() {
        driver.get(ConfigReader.get("url"));
    }
}
