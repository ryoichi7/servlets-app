package util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@UtilityClass
public final class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    @SneakyThrows
    private static void loadProperties() {
        var stream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties");
        PROPERTIES.load(stream);
    }
    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
