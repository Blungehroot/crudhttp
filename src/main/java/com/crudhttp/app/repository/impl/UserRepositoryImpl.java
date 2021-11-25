package com.crudhttp.app.repository.impl;

import com.crudhttp.app.model.User;
import com.crudhttp.app.repository.UserRepository;
import com.crudhttp.app.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final SessionFactory sessionFactory;
    private static Session session;

    public UserRepositoryImpl() {
        sessionFactory = HibernateUtil.createSessionFactory();
    }

    @Override
    public User getById(Integer id) {
        session = sessionFactory.openSession();
        return session.get(User.class, id);
    }

    @Override
    public List<User> getAll() {
        session = sessionFactory.openSession();
        return session.createQuery("from User").getResultList();
    }

    @Override
    public User save(User user) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Override
    public User update(User user) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Override
    public void deleteById(Integer id) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        user.setEvents(null);
        session.delete(user);
        session.getTransaction().commit();
    }
}
