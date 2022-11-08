package compani.repository.hibernate;

import compani.model.Event;
import compani.repository.EventRepository;
import compani.utils.HibernateUtils;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.List;

public class HibernateEventRepository implements EventRepository {
    @Override
    public Event save(Event event) {
        try (Session session = HibernateUtils.getSession()) {
            session.save(event);
        }
        return event;
    }

    @Override
    public Event update(Event event) {
        try (Session session = HibernateUtils.getSession()) {
            session.getTransaction().begin();
            session.merge(event);
            session.getTransaction().commit();
        }
        return event;
    }

    @Override
    public Event findById(int id) {
        try (Session session = HibernateUtils.getSession()) {
            Query query = session.createQuery("from Event as P left join fetch P.file left join fetch P.user where P.id = :id");
            query.setParameter("id",id);
            return (Event) query.getSingleResult();
        }
    }

    @Override
    public List<Event> getAll() {
        try (Session session = HibernateUtils.getSession()) {
            Query query = session.createQuery("from Event as P left join fetch P.user left join fetch P.file ");
            return query.getResultList();
        }
    }

    @Override
    public void deleteById(int id) {
        try (Session session = HibernateUtils.getSession()) {
            session.getTransaction().begin();
            Query query = session.createQuery("delete Event as U where U.id = :id");
            query.setParameter("id",id);
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }
}
