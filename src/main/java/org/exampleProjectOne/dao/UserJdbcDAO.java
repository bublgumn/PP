package org.exampleProjectOne.dao;

import org.exampleProjectOne.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDao {

    private Connection connection;

    public UserJdbcDAO(Connection connection) {
        this.connection = connection;
    }

    public List<User> getAllUser() throws SQLException {
        List<User> result = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                User bankClient = new User();
                bankClient.setEmail(resultSet.getString(2));
                bankClient.setPassword(resultSet.getString(3));
                bankClient.setAge(resultSet.getLong(4));
                bankClient.setId(resultSet.getLong(1));
                result.add(bankClient);
            }
        }
        return result;
    }

    public List<User> getUserByName(String email) throws SQLException {
        String search = "select * from users where name = ?;";
        List<User> userList = new ArrayList<>();
        User result = null;
        try (PreparedStatement stmt = connection.prepareStatement(search)
        ) {
            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                result = new User();
                result.setEmail(resultSet.getString(2));
                result.setPassword(resultSet.getString(3));
                result.setAge(resultSet.getLong(4));
                result.setId(resultSet.getLong(1));
            }
        }
        if (result != null) {
            userList.add(result);
        }
        return userList;
    }

    public void deleteUser(User user) throws SQLException {
        String sql = "DELETE FROM users WHERE name = ? and password = ? and age = ?";
        try (PreparedStatement result = connection.prepareStatement(sql)) {
            result.setString(1, user.getEmail());
            result.setString(2, user.getPassword());
            result.setLong(3, user.getAge());
            result.executeUpdate();
        }
    }

    public void updateClient(Long id, String email, String password, Long age) throws SQLException {
        if (validateClient(email, password) && id != null && age != null) {
            String update = "update users set name = ?, password = ?, age = ? where id = ?";
            try (PreparedStatement result = connection.prepareStatement(update)) {
                result.setString(1, email);
                result.setString(2, password);
                result.setLong(3, age);
                result.setLong(4, id);
                result.executeUpdate();
            }
        }
    }

    private boolean validateClient(String name, String password) {
        return !name.isEmpty() && !password.isEmpty() && !(name == null) && !(password == null);
    }

    public boolean searchClientDao(User user) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("select * from users where name = ?")) {
            stmt.setString(1, user.getEmail());
            ResultSet resultSet = stmt.executeQuery();
            if (!resultSet.next()) {
                return false;
            }
        }
        return true;
    }

    public void addUser(User user) throws SQLException {
        if (validateClient(user.getEmail(), user.getPassword()) && !searchClientDao(user)) {
            String update = "insert into users (name, password, age) values (?, ?, ?)";
            try (PreparedStatement result = connection.prepareStatement(update);) {
                result.setString(1, user.getEmail());
                result.setString(2, user.getPassword());
                result.setLong(3, user.getAge());
                result.executeUpdate();
            }
        }
    }
}
