package org.exampleProjectOne.service;

import org.exampleProjectOne.dao.UserDao;
import org.exampleProjectOne.factory.UserDaoFactory;
import org.exampleProjectOne.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService implements Service {

    private static UserService userService;
    private UserDaoFactory factory = UserDaoFactory.getInstance();

    private UserService() {
    }

    public static UserService getInstance () {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }


    @Override
    public List<User> getAllUser() {
        try (UserDao userDao = factory.getDao()) {
            return userDao.getAllUser();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addUser(User user) {
        try (UserDao userDao = factory.getDao()) {
            userDao.addUser(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void updateClient(User user) {
        try (UserDao userDao = factory.getDao()) {
            userDao.updateClient(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUserByName(String email) {
        try (UserDao userDao = factory.getDao()) {
            return userDao.getUserByName(email);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public User getUserById(Long id) throws SQLException {
        try (UserDao userDao = factory.getDao()) {
            return userDao.getUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteUser(User user) {
        try (UserDao userDao = factory.getDao()) {
            userDao.deleteUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
