package pages;

import drivers.DriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BasePage;
import utilities.ConfigReader;

public class HomePage extends BasePage {

    private WebDriver driver;

    private final By imgLogo = By.cssSelector(".css-3052zt img");
    private final By imgBienvenidos = By.xpath("//h4[contains(.,'¡Bienvenido a Profuturo préstamos!')]");
    private final By btnSolicitaAqui = By.xpath("//p[contains(.,'Solicítalo aquí')]");

    public HomePage() {
        this.driver = new DriverProvider().get();
    }


    public void validateHomePage(String page) {
        switch (page) {
            case "inicio":
                waitUntilVisible(imgLogo, 240);
                validateVisibility(imgBienvenidos, btnSolicitaAqui);
                break;

            default:
                throw new IllegalArgumentException(
                        "Página no reconocida: " + page
                );
        }
    }

    public void abrirPagina(){
        driver.get(ConfigReader.get("url"));
    }
}
