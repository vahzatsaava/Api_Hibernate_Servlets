package compani.repository.hibernate;

import compani.model.File;
import compani.repository.FileRepository;
import compani.utils.HibernateUtils;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

public class HibernateFileRepositoryImpl implements FileRepository {
    @Override
    public File save(File file) {
        try (Session session = HibernateUtils.getSession()){
            session.save(file);
            return file;
        }
    }

    @Override
    public File update(File file) {
        try (Session session = HibernateUtils.getSession()){
            session.getTransaction().begin();
            session.merge(file);
            session.beginTransaction().commit();
            return file;
        }
    }

    @Override
    public File findById(int id) {
        try (Session session = HibernateUtils.getSession()){
            return session.get(File.class,id);
        }
    }

    @Override
    public List<File> getAll() {
        try (Session session = HibernateUtils.getSession()){
            Query query = session.createQuery("from File ");
            return query.getResultList();
        }
    }

    @Override
    public void deleteById(int id) {
        try (Session session = HibernateUtils.getSession()){
            session.getTransaction().begin();
            Query query = session.createQuery("delete File as U where U.id = :id");
            query.setParameter("id",id);
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }
}
