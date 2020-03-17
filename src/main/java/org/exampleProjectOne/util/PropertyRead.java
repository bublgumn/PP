package org.exampleProjectOne.util;

import org.exampleProjectOne.factory.UserDaoFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyRead {

    private static final Properties prs = new Properties();

    public static String readProperty(String nameProperty) {
        ClassLoader loader = UserDaoFactory.class.getClassLoader();
        try {
            try (InputStream io = loader.getResourceAsStream("DAO.properties")) {
                prs.load(io);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prs.getProperty(nameProperty);
    }
}
