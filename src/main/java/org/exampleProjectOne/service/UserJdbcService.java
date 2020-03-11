package org.exampleProjectOne.service;

import org.exampleProjectOne.dao.UserDao;
import org.exampleProjectOne.dao.UserJdbcDAO;
import org.exampleProjectOne.model.User;
import org.exampleProjectOne.util.DBHelper;
import sun.security.pkcs11.Secmod;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcService implements Service {

    private static UserJdbcService userJdbcService;

    private UserJdbcService() throws Exception {
        DBHelper.initializeMysql();
    }

    public static UserJdbcService getInstance() throws Exception {
        if (userJdbcService == null) {
            userJdbcService = new UserJdbcService();
            return userJdbcService;
        }
        return userJdbcService;
    }

    public List<User> getAllUser() {
        try (Connection connection = DBHelper.getInstance().getConnection()) {
            UserDao userDao = new UserJdbcDAO(connection);
            return userDao.getAllUser();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public boolean addUser(User user) {
        if (user != null) {
            try (Connection connection = DBHelper.getInstance().getConnection()) {
                UserDao userDao = new UserJdbcDAO(connection);
                userDao.addUser(user);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public void updateClient(Long id, String email, String password, Long age) {
        try (Connection connection = DBHelper.getInstance().getConnection()) {
            UserDao userDao = new UserJdbcDAO(connection);
            userDao.updateClient(id, email, password, age);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getUserByName(String email) {
        try (Connection connection = DBHelper.getInstance().getConnection()) {
            UserDao userDao = new UserJdbcDAO(connection);
            List<User> result = userDao.getUserByName(email);
            return result;
        } catch (Exception e) {
            return new ArrayList<>();
        }

    }

    public void deleteUser(User user) {
        try (Connection connection = DBHelper.getInstance().getConnection()) {
            UserDao userDao = new UserJdbcDAO(connection);
            userDao.deleteUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
