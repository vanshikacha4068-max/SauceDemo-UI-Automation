
package Utilities;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class Propertiesmanager {

    private static Properties xpathProperties = new Properties();
    private static Properties otherProperties = new Properties();

    static {
        try {
            InputStream xpathFile =
                    Propertiesmanager.class.getClassLoader()
                    .getResourceAsStream("xpath.properties");

            InputStream otherFile =
                    Propertiesmanager.class.getClassLoader()
                    .getResourceAsStream("otherComp.properties");

            xpathProperties.load(xpathFile);
            otherProperties.load(otherFile);

            xpathFile.close();
            otherFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getXpath(String key) {
        return xpathProperties.getProperty(key);
    }

    public static String getOther(String key) {
        return otherProperties.getProperty(key);
    }
}