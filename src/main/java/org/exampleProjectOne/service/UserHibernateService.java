package org.exampleProjectOne.service;


import org.exampleProjectOne.dao.UserHibernateDAO;
import org.exampleProjectOne.model.User;
import org.exampleProjectOne.servlets.ListUsersServlet;
import org.exampleProjectOne.util.DBHelper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateService implements Service {

    private static UserHibernateService userService;

    private SessionFactory sessionFactory;

    private UserHibernateService() {
        Configuration configuration = DBHelper.getInstance().getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        User userOne = new User("Admin", "admin", 1L);
        User userTwo = new User("AdminTwo", "adminTwo", 2L);
        addUser(userOne);
        addUser(userTwo);
    }

    public static UserHibernateService getInstance() {
        if (userService == null) {
            userService = new UserHibernateService();
        }
        return userService;
    }

    @Override
    public List<User> getAllUser() {
        Session session = sessionFactory.openSession();
        UserHibernateDAO userHibernateDAO = new UserHibernateDAO(session);
        List<User> resultList = null;
        try {
            resultList = userHibernateDAO.getAllUser();
        } catch (SQLException e) {
            session.close();
            return null;
        }
        session.close();
        return resultList;
    }

    @Override
    public boolean addUser(User user) {
        Session session = sessionFactory.openSession();
        UserHibernateDAO userHibernateDAO = new UserHibernateDAO(session);
        try {
            userHibernateDAO.addUser(user);
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
            return false;
        }
    }

    @Override
    public void updateClient(Long id, String email, String password, Long age) {
        Session session = sessionFactory.openSession();
        UserHibernateDAO userHibernateDAO = new UserHibernateDAO(session);
        try {
            userHibernateDAO.updateClient(id, email, password, age);
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
        }
    }

    @Override
    public List<User> getUserByName(String email) {
        Session session = sessionFactory.openSession();
        UserHibernateDAO userHibernateDAO = new UserHibernateDAO(session);
        List<User> resultList = null;
        try {
            resultList = userHibernateDAO.getUserByName(email);
        } catch (SQLException e) {
            session.close();
            return null;
        }
        session.close();
        return resultList;
    }

    @Override
    public void deleteUser(User user) {
        Session session = sessionFactory.openSession();
        UserHibernateDAO userHibernateDAO = new UserHibernateDAO(session);
        try {
            userHibernateDAO.deleteUser(user);
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.close();
        }
    }
}
