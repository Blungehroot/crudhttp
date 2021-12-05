package com.crudhttp.app.repository.impl;

import com.crudhttp.app.model.User;
import com.crudhttp.app.repository.UserRepository;
import com.crudhttp.app.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private static SessionFactory sessionFactory;
    private static Session session;

    public UserRepositoryImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    @Transactional
    public User getById(Integer id) {
        session = sessionFactory.openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    @Override
    @Transactional
    public List<User> getAll() {
        session = sessionFactory.openSession();
        List<User> users = session.createQuery("from User").getResultList();
        session.close();
        return users;
    }

    @Override
    @Transactional
    public User save(User user) {
        session = sessionFactory.openSession();
        session.save(user);
        session.close();
        return user;
    }

    @Override
    @Transactional
    public User update(User user) {
        session = sessionFactory.openSession();
        session.update(user);
        session.close();
        return user;
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        user.setEvents(null);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }
}
