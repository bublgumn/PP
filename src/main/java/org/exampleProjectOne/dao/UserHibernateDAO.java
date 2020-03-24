package org.exampleProjectOne.dao;

import org.exampleProjectOne.model.User;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAO implements UserDao {
    private static SessionFactory sessionFactory;
    private Session session;

    public static void setSessionFactory (SessionFactory sessionFactoryNew){
        sessionFactory = sessionFactoryNew;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public UserHibernateDAO(Session session) {
        this.session = session;
    }

    private boolean validateClient(String name, String password) {
        return !name.isEmpty() && !password.isEmpty() && !(name == null) && !(password == null);
    }

    @Override
    public void close() throws Exception {
        session.close();
    }

    @Override
    public List<User> getAllUser() throws SQLException {
        return session.createQuery("From User u", User.class).list();
    }

    @Override
    public User getUserByName(String email) throws SQLException {
        Query query = session.createQuery("From User u where u.email = :nowEmail ");
        query.setParameter("nowEmail", email);
        return (User) query.uniqueResult();
    }

    @Override
    public User getUserByNameAndPassword(String email, String password) throws SQLException{
        Query query = session.createQuery("From User u where u.email = :nowEmail and u.password = : nowPassword ");
        query.setParameter("nowEmail", email);
        query.setParameter("nowPassword", password);
        return (User) query.uniqueResult();
    }

    @Override
    public User getUserById(Long id) throws SQLException {
        Query query = session.createQuery("From User u where u.id = :nowId ");
        query.setParameter("nowId", id);
        return (User) query.uniqueResult();
    }

    @Override
    public void deleteUser(User user) throws SQLException {
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        session.flush();
        transaction.commit();
    }

    @Override
    public void updateClient(User user) throws SQLException {
        Transaction transaction = session.beginTransaction();
        session.update(user);
        session.flush();
        transaction.commit();
    }

    @Override
    public boolean searchClientDao(User user) throws SQLException {
        Query query = session.createQuery("From User u where u.email = :nameOne ");
        query.setParameter("nameOne", user.getEmail());
        return query.uniqueResult() != null;
    }

    @Override
    public void addUser(User user) throws SQLException {
        if (validateClient(user.getEmail(), user.getPassword()) && !searchClientDao(user)) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            session.flush();
            transaction.commit();
        }
    }
}


