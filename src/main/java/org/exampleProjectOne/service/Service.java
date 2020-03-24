package org.exampleProjectOne.service;

import org.exampleProjectOne.model.User;

import java.sql.SQLException;
import java.util.List;

public interface Service {
    List<User> getAllUser();

    boolean addUser(User user);

    void updateClient(User user);

    User getUserByName(String email);

    User getUserByNameAndPassword(String email, String password);

    User getUserById(Long id) throws SQLException;

    void deleteUser(User user);
}
