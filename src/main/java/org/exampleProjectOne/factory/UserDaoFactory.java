package org.exampleProjectOne.factory;

import org.exampleProjectOne.dao.UserDao;
import org.exampleProjectOne.dao.UserHibernateDAO;
import org.exampleProjectOne.dao.UserJdbcDAO;
import org.exampleProjectOne.service.Service;
import org.exampleProjectOne.service.UserService;
import org.exampleProjectOne.util.DBHelper;
import org.exampleProjectOne.util.PropertyRead;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory  {

    private String db = PropertyRead.readProperty("db.host");
    private static UserDaoFactory userDaoFactory;

    public static UserDaoFactory getInstance() {
        if (userDaoFactory == null) {
            userDaoFactory = new UserDaoFactory();
        }
        return userDaoFactory;
    }

    private UserDaoFactory() {
        DBHelper.initDataResources();
    }

    public UserDao getDao() {
        if (db.equals("jdbc")) {
            return new UserJdbcDAO(DBHelper.getInstance().getConnection());
        } else if (db.equals("hibernate")) {
            return new UserHibernateDAO(UserHibernateDAO.getSessionFactory().openSession());
        }
        return null;
    }
}
