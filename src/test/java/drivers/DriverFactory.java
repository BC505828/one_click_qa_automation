package drivers;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import utilities.Logs;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverFactory{

    private static final boolean runServer = System.getenv("JOB_NAME") != null;

    //Empezar el driver
    public static void buildDriver() {
        if (runServer) {
            buildRemoteDriver();
        } else {
            buildLocalDriver();
        }
    }

    //Cerrar el driver
    public void quitDriver() {
        AndroidDriver driver = new DriverProvider().get();
        DriverProvider provider = new DriverProvider();

        if (driver != null) {
            Logs.info("Finalizado driver");
            driver.quit();
            provider.remove();
        }
    }

    private static void buildLocalDriver() {
        try {
            final var appiumUrl = "http://127.0.0.1:4723/";
            final var desiredCapabilities = getDesiredLocalCapabilitiies();

            Logs.debug("Inicializando el driver");

            final var driver = new AndroidDriver(
                    new URL(appiumUrl),
                    desiredCapabilities
            );

            Logs.debug("Asignando el driver al driver provider");

            new DriverProvider().set(driver);

        } catch (MalformedURLException e) {
            Logs.error(
                    "Error al iniciar el driver: %s",
                    e.getLocalizedMessage()
            );

            throw new RuntimeException(e);
        }
    }

    private static void buildRemoteDriver() {

    }

    private static DesiredCapabilities getDesiredLocalCapabilitiies() {

        DesiredCapabilities cap = new DesiredCapabilities();

        cap.setCapability("appium:platformName", "Android");
        cap.setCapability("appium:deviceName", "R52MC01SM8R");
        cap.setCapability("appium:noReset", true);
        cap.setCapability("appium:automationName", "UiAutomator2");
        cap.setBrowserName("Chrome");

        return cap;
    }
}