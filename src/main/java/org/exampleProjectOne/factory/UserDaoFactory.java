package org.exampleProjectOne.factory;

import org.exampleProjectOne.dao.UserDao;
import org.exampleProjectOne.dao.UserHibernateDAO;
import org.exampleProjectOne.dao.UserJdbcDAO;
import org.exampleProjectOne.service.Service;
import org.exampleProjectOne.service.UserService;
import org.exampleProjectOne.util.DBHelper;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory implements Factory{

    private static String db;
    private static boolean initializationDAO = false;
    private static SessionFactory sessionFactory;

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

    private static SessionFactory getSessionFactory () {
        Configuration configuration = DBHelper.getInstance().getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public UserDao getDao() {
        if (db.equals("jdbc")){
            try {
                if (!initializationDAO){
                    DBHelper.initializeMysql();
                    initializationDAO = true;
                }
                return new UserJdbcDAO(DBHelper.getInstance().getConnection());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (db.equals("hibernate")) {
            if (!initializationDAO) {
                sessionFactory = getSessionFactory();
                DBHelper.initHibernate(sessionFactory);
                initializationDAO = true;
            }
            return new UserHibernateDAO(sessionFactory.openSession());
        }
        return null;
    }
}
