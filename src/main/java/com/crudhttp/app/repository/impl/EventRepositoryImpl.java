package com.crudhttp.app.repository.impl;

import com.crudhttp.app.model.Event;
import com.crudhttp.app.repository.EventRepository;
import com.crudhttp.app.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import java.util.List;

public class EventRepositoryImpl implements EventRepository {
    private static SessionFactory sessionFactory;
    private static Session session;

    public EventRepositoryImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    @Transactional
    public Event getById(Integer id) {
        session = sessionFactory.openSession();
        Event event = session.get(Event.class, id);
        session.close();
        return event;
    }

    @Override
    @Transactional
    public List<Event> getAll() {
        session = sessionFactory.openSession();
        List<Event> events = session.createQuery("from Event").getResultList();
        session.close();
        return events;
    }

    @Override
    @Transactional
    public Event save(Event event) {
        session = sessionFactory.openSession();
        session.save(event);
        session.close();
        return event;
    }

    @Override
    @Transactional
    public Event update(Event event) {
        session = sessionFactory.openSession();
        session.update(event);
        session.close();
        return event;
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        Event event = session.get(Event.class, id);
        event.setMedia(null);
        session.delete(event);
        session.getTransaction().commit();
        session.close();
    }
}
