package utilities;

import drivers.DriverProvider;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

public abstract class BasePage {
    private final static int defalutTimeout = 5;
    //protected final SoftAssert softAssert;
    private final int timeout;

    private static final PointerInput finger =
            new PointerInput(PointerInput.Kind.TOUCH, "finger");

    public BasePage(int timeOut) {
        //softAssert = new SoftAssert();
        this.timeout = timeOut;
    }

    public BasePage() {
        this(defalutTimeout);
    }

    protected static AndroidDriver getDriver() {
        return new DriverProvider().get();
    }

    protected WebElement waitForDisplayed(By locator, int time) {
        final var wait = new WebDriverWait(getDriver(), Duration.ofSeconds(time));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitForDisplayed(By locator) {
        return waitForDisplayed(locator, 100);
    }

    protected void waitPage(By locator, String pageName) {
        Logs.info("Esperando que la página cargue: %s", pageName);
        waitForDisplayed(locator, 100);
        Logs.info("%s se ha cargado exitosamente", pageName);
    }

    protected WebElement find(By locator) {
        return getDriver().findElement(locator);
    }

    protected WebElement findAll(By locator) {
        return getDriver().findElement(locator);
    }

    public void pressBack() {
        Logs.info("Presionando atras en el movil");
        getDriver().pressKey(new KeyEvent(AndroidKey.BACK));
    }

    public void sleep(int timeMs) {
        try {
            long seconds = timeMs / 10; // conversión a segundos
            Thread.sleep(seconds * 10000); // dormir usando segundos convertidos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            Logs.error("InterruptedException: %s", e.getMessage());
        }
    }


    public void inputText(By element, String text) {
        try {
            Logs.info("Ingresando el texto en los campos");
            Thread.sleep(300);
            getDriver().findElement(element).sendKeys(text);
        } catch (InterruptedException interruptedException) {
            Logs.error("InterruptedException: %s", interruptedException);
        }
    }

    public void waitUntilVisible(By locator, int timeoutSegundos) {
        try {
            new WebDriverWait(getDriver(), Duration.ofSeconds(timeoutSegundos))
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
            Logs.info("Elemento visible: " + locator.toString());
        } catch (TimeoutException e) {
            Logs.error("Timeout esperando visibilidad de: " + locator.toString());
            throw e;
        }
    }

    public String tapElementBase(By locator) {
        if (locator != null) {
            waitUntilVisible(locator, 180);
            getDriver().findElement(locator).click();
            //find(locator).click();
        } else {
            return "Elemento no reconocido para dar click: " + locator;
        }
        return null;
    }


    public static void validateTextInElement(By locator, String expectedText, int timeout) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(timeout));

        // Espera hasta que el texto esté presente
        wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, expectedText));

        // Obtiene el texto actual del elemento
        WebElement elemento = getDriver().findElement(locator);
        String actualText = elemento.getText();

    }

    public void validateVisibility(By... elementos) {
        Assertions.assertAll(
                Arrays.stream(elementos)
                        .map(locator -> (Executable) () -> {
                            boolean visible = find(locator).isDisplayed();
                            if (!visible) {
                                System.out.println("Elemento NO visible: " + locator);
                            }
                            Assertions.assertTrue(visible, locator + " no visible");
                        })
                        .toArray(Executable[]::new)
        );
    }


    public void setDate(By locator, Integer month, String day) {
        try {
            Logs.info("Fijando el fecha de seleccion");
            for (int i = 1; i < month; i++) {
                tapElementBase(locator);
            }
            getDriver().findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"" + day + "\")")).click();
        } catch (TimeoutException e) {
            Logs.error("Timeout esperando visibilidad de: " + locator.toString());
            throw e;
        }
    }

    public void backKeyGeneral() {
        try {
            getDriver().navigate().back();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void scrollDown(String text) {
        try {
            getDriver().findElement(AppiumBy.androidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().text(\"" + text + "\"))"
            ));
            Logs.info("Elemento visible para el scroll");
        } catch (TimeoutException e) {
            Logs.error("Timeout esperando sea visible el elemento ");
            throw e;
        }
    }

    public void scrollUp(Integer alto) {
        Map<String, Object> params = new HashMap<>();
        params.put("left", 500);     // coordenada X inicial
        params.put("top", 500);     // coordenada Y inicial
        params.put("width", 100);    // ancho del área de swipe
        params.put("height", alto);   // alto del área de swipe
        params.put("direction", "up"); // opciones: up, down, left, right
        params.put("percent", 1.0); // porcentaje de la pantalla a recorrer

        getDriver().executeScript("mobile: swipeGesture", params);
    }

    public void isCheckEnable(By locator) {

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(240)); // tiempo máximo de espera

        try {
            WebElement elemento = wait.until(driver -> {
                WebElement el = driver.findElement(locator);
                if (el.isEnabled()) {
                    return el;
                } else {
                    //System.out.println("El elemento aún no está habilitado, reintentando...");
                    return null; // sigue esperando
                }
            });

            elemento.click();
            //System.out.println("Se hizo clic");

        } catch (TimeoutException e) {
            System.out.println("El elemento no se habilitó dentro del tiempo de espera.");
        }
    }


    public void validateTextById(String id, String textoEsperado) {
        try {
            var elemento = getDriver().findElement(By.id(id));
            var impre = elemento.getText();
            //Cambiar la declarion de las variables
            //Assert.assertTrue(elemento.getText() == textoEsperado);
            Assert.assertEquals(elemento.getText(), textoEsperado);
            Logs.info("El texto conincide con el esperado");

        } catch (TimeoutException e) {
            System.err.println("[ERROR] El elemento con id " + id + " no fue visible en el tiempo esperado.");
            Assert.fail("Timeout esperando el elemento con id: " + id);
        }


    }

    public void swipeGeneralPuntos(Point origen, Point destino) {
        Logs.debug("Haciendo swipe desde el punto %s hasta el punto %s", origen, destino);
        final var sequence = new Sequence(finger, 1);

        Logs.debug("Movemos el dedo hacia la posicion inicial");
        sequence.addAction(
                finger.createPointerMove(
                        Duration.ZERO,
                        PointerInput.Origin.viewport(),
                        origen)
        );
        Logs.debug("Tocamos la pantalla en el punto de origen");
        sequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

        Logs.debug("Agregamos una breve pausa");
        sequence.addAction(new Pause(finger, Duration.ofMillis(1000)));

        Logs.debug("Movemos el dedo hacia la posicion final");
        sequence.addAction(
                finger.createPointerMove(
                        Duration.ofMillis(1000),
                        PointerInput.Origin.viewport(),
                        destino
                )
        );

        Logs.debug("Dejamos de tocar la pantalla");
        sequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        getDriver().perform(List.of(sequence));
    }

    private void swipeGeneral(
            double porcentajeXInicial,
            double porcentajeYInicial,
            double porcentajeXFinal,
            double porcentajeYFinal,
            WebElement element) {
        final var puntoInical =
                getElementPointUsingPorce(porcentajeXInicial, porcentajeYInicial, element);
        final var puntoFinal =
                getElementPointUsingPorce(porcentajeXFinal, porcentajeYFinal, element);
        swipeGeneralPuntos(puntoInical, puntoFinal);
    }

    public void swipeHorizontal(
            double porcentajeY,
            double porcentajeXInicial,
            double porcentajeXFinal,
            WebElement element
    ) {
        swipeGeneral(porcentajeXInicial, porcentajeY, porcentajeXFinal, porcentajeY, element);
    }


    private static Point getElementPointUsingPorce(double percentageX, double percentageY, WebElement element) {
        final var ubicacion = element.getLocation();
        final var tamano = element.getSize();

        final var xDelta = (percentageX / 100) * tamano.getWidth();
        final var yDelta = (percentageY / 100) * tamano.getHeight();

        final var x = (int) (ubicacion.getX() + xDelta);
        final var y = (int) (ubicacion.getY() + yDelta);

        return new Point(x, y);
    }

    public boolean isElementChecked(String locator) {
        WebElement element = find(By.id(locator));
        return Boolean.parseBoolean(element.getAttribute("checked"));
    }

}