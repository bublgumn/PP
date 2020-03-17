package org.exampleProjectOne.util;

import org.exampleProjectOne.dao.UserHibernateDAO;
import org.exampleProjectOne.dao.UserJdbcDAO;
import org.exampleProjectOne.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;

public class DBHelper {

    private static DBHelper dbHelper;

    private DBHelper() {
    }

    public static DBHelper getInstance() {
        if (dbHelper == null) {
            dbHelper = new DBHelper();
        }
        return dbHelper;
    }

    private static SessionFactory getSessionFactory () {
        Configuration configuration = DBHelper.getInstance().getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public Connection getConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName(
                    PropertyRead.readProperty("db.driver")).newInstance());
            Connection connection = DriverManager.getConnection(
                    PropertyRead.readProperty("db.url"),
                    PropertyRead.readProperty("db.userName"),
                    PropertyRead.readProperty("db.passwordDB"));
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.dialect", PropertyRead.readProperty("db.dialect"));
        configuration.setProperty("hibernate.connection.driver_class", PropertyRead.readProperty("db.driver"));
        configuration.setProperty("hibernate.connection.url", PropertyRead.readProperty("db.url"));
        configuration.setProperty("hibernate.connection.username", PropertyRead.readProperty("db.userName"));
        configuration.setProperty("hibernate.connection.password", PropertyRead.readProperty("db.passwordDB"));
        configuration.setProperty("hibernate.show_sql", PropertyRead.readProperty("db.show"));
        configuration.setProperty("hibernate.hbm2ddl.auto", PropertyRead.readProperty("db.typeCreate"));
        return configuration;
    }

    public static void initDataResources(){
        User userOne = new User("Admin", "admin", 1L);
        User userTwo = new User("AdminTwo", "adminTwo", 2L);

        if (PropertyRead.readProperty("db.host").equals("hibernate")) {
            UserHibernateDAO.setSessionFactory(getSessionFactory());
            try (UserHibernateDAO userHibernateDAO = new UserHibernateDAO(
                    UserHibernateDAO.getSessionFactory().openSession())) {
                userHibernateDAO.addUser(userOne);
                userHibernateDAO.addUser(userTwo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (PropertyRead.readProperty("db.host").equals("jdbc")) {
            try {
                DriverManager.registerDriver((Driver) Class.forName(
                        PropertyRead.readProperty("db.driver")).newInstance());

                try (Connection connectionTry = DriverManager.getConnection(
                        PropertyRead.readProperty("db.url"),
                        PropertyRead.readProperty("db.userName"),
                        PropertyRead.readProperty("db.passwordDB"))) {
                    Statement stmt = connectionTry.createStatement();
                    stmt.executeUpdate("DROP TABLE IF EXISTS users");
                    stmt.execute("create table if not exists users (id bigint auto_increment, name varchar(256), password varchar(256), age bigint, primary key (id))");
                    stmt.close();
                }

                try (Connection connectionTry = DriverManager.getConnection(
                        PropertyRead.readProperty("db.url"),
                        PropertyRead.readProperty("db.userName"),
                        PropertyRead.readProperty("db.passwordDB"))) {
                    UserJdbcDAO userJdbcDAO = new UserJdbcDAO(connectionTry);
                    userJdbcDAO.addUser(userOne);
                    userJdbcDAO.addUser(userTwo);
                }
            } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                e.printStackTrace();
                throw new IllegalStateException();
            }
        }
    }

}
