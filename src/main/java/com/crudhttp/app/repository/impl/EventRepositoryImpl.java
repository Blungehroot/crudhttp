package com.crudhttp.app.repository.impl;

import com.crudhttp.app.model.Event;
import com.crudhttp.app.repository.EventRepository;
import com.crudhttp.app.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class EventRepositoryImpl implements EventRepository {
    private final SessionFactory sessionFactory;
    private static Session session;

    public EventRepositoryImpl() {
        sessionFactory = HibernateUtil.createSessionFactory();
    }
    @Override
    public Event getById(Integer id) {
        session = sessionFactory.openSession();
        return session.get(Event.class, id);
    }

    @Override
    public List<Event> getAll() {
        session = sessionFactory.openSession();
        return session.createQuery("from Event").getResultList();
    }

    @Override
    public Event save(Event event) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(event);
        session.getTransaction().commit();
        session.close();
        return event;
    }

    @Override
    public Event update(Event event) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(event);
        session.getTransaction().commit();
        session.close();
        return event;
    }

    @Override
    public void deleteById(Integer id) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        Event event = session.get(Event.class, id);
        event.setMedia(null);
        session.delete(event);
        session.getTransaction().commit();
    }
}
