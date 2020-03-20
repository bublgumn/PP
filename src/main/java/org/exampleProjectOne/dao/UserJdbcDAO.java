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

    @Override
    public void close() throws Exception {
        connection.close();
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
                bankClient.setRole(resultSet.getString(5));
                result.add(bankClient);
            }
        }
        return result;
    }

    public User getUserByName(String email) throws SQLException {
        User result = null;
        try (PreparedStatement stmt = connection.prepareStatement("select * from users where email = ?;")
        ) {
            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                result = new User();
                result.setEmail(resultSet.getString(2));
                result.setPassword(resultSet.getString(3));
                result.setAge(resultSet.getLong(4));
                result.setId(resultSet.getLong(1));
                result.setRole(resultSet.getString(5));
            }
        }
        return result;
    }

    @Override
    public User getUserById(Long id) throws SQLException {
        User result = null;
        try (PreparedStatement stmt = connection.prepareStatement("select * from users where id = ?;")
        ) {
            stmt.setLong(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                result = new User();
                result.setEmail(resultSet.getString(2));
                result.setPassword(resultSet.getString(3));
                result.setAge(resultSet.getLong(4));
                result.setId(resultSet.getLong(1));
                result.setRole(resultSet.getString(5));
            }
        }
        return result;
    }

    public void deleteUser(User user) throws SQLException {
        String sql = "DELETE FROM users WHERE email = ? and password = ? and age = ?";
        try (PreparedStatement result = connection.prepareStatement(sql)) {
            result.setString(1, user.getEmail());
            result.setString(2, user.getPassword());
            result.setLong(3, user.getAge());
            result.executeUpdate();
        }
    }

    public void updateClient(User user) throws SQLException {
        if (validateClient(user.getEmail(), user.getPassword()) && user.getId() != null && user.getAge() != null) {
            String update = "update users set email = ?, password = ?, age = ? where id = ?";
            try (PreparedStatement result = connection.prepareStatement(update)) {
                result.setString(1, user.getEmail());
                result.setString(2, user.getPassword());
                result.setLong(3, user.getAge());
                result.setLong(4, user.getId());
                result.executeUpdate();
            }
        }
    }

    private boolean validateClient(String name, String password) {
        return !name.isEmpty() && !password.isEmpty() && !(name == null) && !(password == null);
    }

    public boolean searchClientDao(User user) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement("select * from users where email = ?")) {
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
            String update = "insert into users (email, password, age, role) values (?, ?, ?, ?)";
            try (PreparedStatement result = connection.prepareStatement(update);) {
                result.setString(1, user.getEmail());
                result.setString(2, user.getPassword());
                result.setLong(3, user.getAge());
                result.setString(4, user.getRole());
                result.executeUpdate();
            }
        }
    }
}
