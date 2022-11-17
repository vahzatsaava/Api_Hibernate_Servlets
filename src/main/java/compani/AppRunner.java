package compani;

import compani.model.Event;
import compani.model.File;
import compani.model.User;
import compani.repository.hibernate.HibernateEventRepository;
import compani.repository.hibernate.HibernateFileRepositoryImpl;
import compani.repository.hibernate.HibernateUserRepositoryImp;

import java.sql.Date;

public class AppRunner {
    public static void main(String[] args) {
        HibernateUserRepositoryImp hibernateUserRepositoryImp = new HibernateUserRepositoryImp();
        HibernateFileRepositoryImpl hibernateFileRepository = new HibernateFileRepositoryImpl();
        HibernateEventRepository hibernateEventRepository = new HibernateEventRepository();
        //hibernateEventRepository.update(new Event(1,new Date(200),new File("charly","hhht"),new User("tomas","munz")));
        hibernateEventRepository.save(new Event(new Date(System.currentTimeMillis()),new File("garry","charly"),new User("gggg","fdgdf")));

    }
}
