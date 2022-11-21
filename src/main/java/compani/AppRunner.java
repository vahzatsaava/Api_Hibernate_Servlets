package compani;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import compani.model.Event;
import compani.model.File;
import compani.model.User;
import compani.repository.hibernate.HibernateEventRepository;
import compani.repository.hibernate.HibernateFileRepositoryImpl;
import compani.repository.hibernate.HibernateUserRepositoryImp;

import java.io.DataInput;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Date;


public class AppRunner {
    public static void main(String[] args) throws IOException {
        HibernateUserRepositoryImp hibernateUserRepositoryImp = new HibernateUserRepositoryImp();
        HibernateFileRepositoryImpl hibernateFileRepository = new HibernateFileRepositoryImpl();
        HibernateEventRepository hibernateEventRepository = new HibernateEventRepository();
        //hibernateEventRepository.update(new Event(1,new Date(200),new File("charly","hhht"),new User("tomas","munz")));
        //hibernateEventRepository.save(new Event(new Date(System.currentTimeMillis()),new File("garry","charly"),new User("gggg","fdgdf")));
        System.out.println(hibernateUserRepositoryImp.findById(2));
        Date date = new Date(System.currentTimeMillis());
        System.out.println(date);

    }
}
