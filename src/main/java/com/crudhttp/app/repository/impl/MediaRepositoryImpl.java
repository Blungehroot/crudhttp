package com.crudhttp.app.repository.impl;

import com.crudhttp.app.model.Media;
import com.crudhttp.app.repository.MediaRepository;
import com.crudhttp.app.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class MediaRepositoryImpl implements MediaRepository {
    private final SessionFactory sessionFactory;
    private static Session session;

    public MediaRepositoryImpl() {
        sessionFactory = HibernateUtil.createSessionFactory();
    }

    @Override
    public Media getById(Integer id) {
        session = sessionFactory.openSession();
        return session.get(Media.class, id);
    }

    @Override
    public List<Media> getAll() {
        session = sessionFactory.openSession();
        return session.createQuery("from Media").getResultList();
    }

    @Override
    public Media save(Media media) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(media);
        session.getTransaction().commit();
        session.close();
        return media;
    }

    @Override
    public Media update(Media media) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(media);
        session.getTransaction().commit();
        session.close();
        return media;
    }

    @Override
    public void deleteById(Integer id) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        Media media = session.get(Media.class, id);
        session.delete(media);
        session.getTransaction().commit();
    }
}
