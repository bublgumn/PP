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

    public Connection getConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("mysql?").          //db name
                    append("user=root&").          //login
                    append("password=admin65");       //password

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/mysql");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "admin65");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create");
        return configuration;
    }

    public static void initHibernate(SessionFactory sessionFactory) {
        User userOne = new User("Admin", "admin", 1L);
        User userTwo = new User("AdminTwo", "adminTwo", 2L);
        try (UserHibernateDAO userHibernateDAO = new UserHibernateDAO(sessionFactory.openSession());) {
            userHibernateDAO.addUser(userOne);
            userHibernateDAO.addUser(userTwo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void initializeMysql() throws Exception {
        DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());
        StringBuilder url = new StringBuilder();

        url.
                append("jdbc:mysql://").        //db type
                append("localhost:").           //host name
                append("3306/").                //port
                append("mysql?").          //db name
                append("user=root&").          //login
                append("password=admin65");       //password

        Connection connection = DriverManager.getConnection(url.toString());
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS users");
        stmt.execute("create table if not exists users (id bigint auto_increment, name varchar(256), password varchar(256), age bigint, primary key (id))");
        stmt.close();
        connection.close();

        User userOne = new User("Admin", "admin", 1L);
        User userTwo = new User("AdminTwo", "adminTwo", 2L);

        try (Connection connectionTry = DriverManager.getConnection(url.toString())) {
            UserJdbcDAO userJdbcDAO = new UserJdbcDAO(connectionTry);
            userJdbcDAO.addUser(userOne);
            userJdbcDAO.addUser(userTwo);
        }
    }

}
