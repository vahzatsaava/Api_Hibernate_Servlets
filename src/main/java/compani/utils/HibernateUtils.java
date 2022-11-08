package compani.utils;

import compani.model.Event;
import compani.model.File;
import compani.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    private static SessionFactory sessionFactory;

    private HibernateUtils() {

    }

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Event.class);
                configuration.addAnnotatedClass(File.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println("Exception with session " + e);
            }

        }
        return sessionFactory;
    }

    public static Session getSession() {
        return getSessionFactory().openSession();
    }
}
