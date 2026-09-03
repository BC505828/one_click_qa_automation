package pages;

import drivers.DriverProvider;
import org.openqa.selenium.WebDriver;
import utilities.ConfigReader;

public class HomePage {

    private WebDriver driver;

    public HomePage() {
        this.driver = new DriverProvider().get();
    }


    public void abrirPagina(){
        driver.get(ConfigReader.get("url"));
    }
}
