package org.exampleProjectOne.service;

import org.exampleProjectOne.model.User;

import java.util.List;

public interface Service {
    List<User> getAllUser();

    boolean addUser(User user);

    void updateClient(User user);

    List<User> getUserByName(String email);

    void deleteUser(User user);
}