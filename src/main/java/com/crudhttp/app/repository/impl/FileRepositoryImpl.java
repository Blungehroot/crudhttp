package com.crudhttp.app.repository.impl;

import com.crudhttp.app.model.File;
import com.crudhttp.app.repository.FileRepository;
import com.crudhttp.app.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class FileRepositoryImpl implements FileRepository {
    private final SessionFactory sessionFactory;
    private static Session session;

    public FileRepositoryImpl() {
        sessionFactory = HibernateUtil.createSessionFactory();
    }

    @Override
    public File getById(Integer id) {
        session = sessionFactory.openSession();
        return session.get(File.class, id);
    }

    @Override
    public List<File> getAll() {
        session = sessionFactory.openSession();
        return session.createQuery("from File").getResultList();
    }

    @Override
    public File save(File file) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(file);
        session.getTransaction().commit();
        session.close();
        return file;
    }

    @Override
    public File update(File file) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(file);
        session.getTransaction().commit();
        session.close();
        return file;
    }

    @Override
    public void deleteById(Integer id) {
        session = sessionFactory.openSession();
        session.beginTransaction();
        File file = session.get(File.class, id);
        session.delete(file);
        session.getTransaction().commit();
    }
}
