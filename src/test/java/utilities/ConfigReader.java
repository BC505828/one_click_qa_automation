package utilities;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {

            if (input == null) {
                throw new RuntimeException("No se encontró config.properties");
            }

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Error al cargar config.properties", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}