package org.exampleProjectOne.dao;

import org.exampleProjectOne.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserHibernateDAO implements UserDao {
    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }

    private boolean validateClient(String name, String password) {
        return !name.isEmpty() && !password.isEmpty() && !(name == null) && !(password == null);
    }

    @Override
    public List<User> getAllUser() throws SQLException {
        String sql = "From " + User.class.getSimpleName();
        List<User> result = session.createQuery(sql).list();
        return result;
    }

    @Override
    public List<User> getUserByName(String email) throws SQLException {
        String sql = "From " + User.class.getSimpleName() + " where email = :nowEmail ";
        Query query = session.createQuery(sql);
        query.setParameter("nowEmail", email);
        User result = (User) query.uniqueResult();
        List<User> resultList = new ArrayList<>();
        if (result != null) {
            resultList.add(result);
        }
        return resultList;
    }

    @Override
    public void deleteUser(User user) throws SQLException {
        session.delete(user);
        Transaction transaction = session.beginTransaction();
        session.flush();
        transaction.commit();
    }

    @Override
    public void updateClient(Long id, String email, String password, Long age) throws SQLException {
        User user =  session.get(User.class, id);
        user.setEmail(email);
        user.setPassword(password);
        user.setAge(age);
        session.update(user);
        Transaction transaction = session.beginTransaction();
        session.flush();
        transaction.commit();
    }

    @Override
    public boolean searchClientDao(User user) throws SQLException {
        String sql = "From " + User.class.getSimpleName() + " where email = :nameOne ";
        Query query = session.createQuery(sql);
        query.setParameter("nameOne", user.getEmail());
        User result = (User) query.uniqueResult();
        return result != null;
    }

    @Override
    public void addUser(User user) throws SQLException {
        if (validateClient(user.getEmail(), user.getPassword()) && !searchClientDao(user)) {
            session.save(user);
            Transaction transaction = session.beginTransaction();
            session.flush();
            transaction.commit();
        }
    }
}


