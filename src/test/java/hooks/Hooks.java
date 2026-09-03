package hooks;

import drivers.DriverFactory;
import utilities.Logs;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {

    private final DriverFactory driverFactory = new DriverFactory();

    @Before
    public void iniciarNavegador() {
        Logs.info("Iniciando aplicación");

        driverFactory.buildDriver();
    }

    @After
    public void cerrarNavegador() {
        Logs.info("Cerrando aplicación");

        driverFactory.quitDriver();
    }
}