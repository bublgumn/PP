package org.exampleProjectOne.service;

import org.exampleProjectOne.dao.UserDao;
import org.exampleProjectOne.factory.UserDaoFactory;
import org.exampleProjectOne.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService implements Service {

    private static UserService userService;

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
        try (UserDao userDao = new  UserDaoFactory().getDao()) {
            return userDao.getAllUser();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addUser(User user) {
        try (UserDao userDao = new  UserDaoFactory().getDao()) {
            userDao.addUser(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void updateClient(User user) {
        try (UserDao userDao = new  UserDaoFactory().getDao()) {
            userDao.updateClient(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getUserByName(String email) {
        try (UserDao userDao = new  UserDaoFactory().getDao()) {
            return userDao.getUserByName(email);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deleteUser(User user) {
        try (UserDao userDao = new  UserDaoFactory().getDao()) {
            userDao.deleteUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
