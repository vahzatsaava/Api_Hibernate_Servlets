package compani.repository.hibernate;

import compani.model.User;
import compani.repository.UserRepository;
import compani.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class HibernateUserRepositoryImp implements UserRepository {
    @Override
    public User save(User user) {
        try (Session session = HibernateUtils.getSession()) {
            session.save(user);
        }
        return user;
    }

    @Override
    public User update(User user) {
        try (Session session = HibernateUtils.getSession()) {
            session.beginTransaction();
            session.merge(user);
            session.getTransaction().commit();
        }
        return user;
    }

    @Override
    public User findById(int id) {
        try (Session session = HibernateUtils.getSession()) {
            return session.get(User.class, id);
        }
    }

    @Override
    public List<User> getAll() {
        try (Session session = HibernateUtils.getSession()) {
            Query query = session.createQuery("from User");
            return query.getResultList();
        }
    }

    @Override
    public void deleteById(int id) {
        try (Session session = HibernateUtils.getSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("delete User as U WHERE U.id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
        }
    }
}
