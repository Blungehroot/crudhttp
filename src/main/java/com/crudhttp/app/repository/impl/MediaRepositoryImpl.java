package com.crudhttp.app.repository.impl;

import com.crudhttp.app.model.Media;
import com.crudhttp.app.repository.MediaRepository;
import com.crudhttp.app.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.transaction.Transactional;
import java.util.List;

public class MediaRepositoryImpl implements MediaRepository {
    private static SessionFactory sessionFactory;
    private static Session session;

    public MediaRepositoryImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    @Transactional
    public Media getById(Integer id) {
        session = sessionFactory.openSession();
        Media media = session.get(Media.class, id);
        session.close();
        return media;
    }

    @Override
    @Transactional
    public List<Media> getAll() {
        session = sessionFactory.openSession();
        List<Media> medias = session.createQuery("from Media").getResultList();
        session.close();
        return medias;
    }

    @Override
    @Transactional
    public Media save(Media media) {
        session = sessionFactory.openSession();
        session.save(media);
        session.close();
        return media;
    }

    @Override
    @Transactional
    public Media update(Media media) {
        session = sessionFactory.openSession();
        session.update(media);
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
        session.close();
    }
}
