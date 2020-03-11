package org.exampleProjectOne.factory;

import org.exampleProjectOne.service.Service;
import org.exampleProjectOne.service.UserHibernateService;
import org.exampleProjectOne.service.UserJdbcService;
import org.exampleProjectOne.util.DBHelper;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory implements Factory{

    private static String db;

    private final Properties prs = new Properties();

    public UserDaoFactory() {
        if (db == null) {
            ClassLoader loader = UserDaoFactory.class.getClassLoader();
            try {
                try (InputStream io = loader.getResourceAsStream("DAO.properties")) {
                    prs.load(io);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            db = this.prs.getProperty("db.host");
            System.out.println(db);
        }
    }

    private String getDb() {
        return db;
    }

    public Service instanceDao() {
        if (db.equals("jdbc")){
            try {
                return UserJdbcService.getInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (db.equals("hibernate")) {
            return UserHibernateService.getInstance();
        }
        return null;
    }
}
