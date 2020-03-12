package org.exampleProjectOne.dao;

import org.exampleProjectOne.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public interface UserDao extends AutoCloseable {

    List<User> getAllUser() throws SQLException;

    List<User> getUserByName(String email) throws SQLException;

    void deleteUser(User user) throws SQLException;

    void updateClient(User user) throws SQLException;

    boolean searchClientDao(User user) throws SQLException;

    void addUser(User user) throws SQLException;

}
