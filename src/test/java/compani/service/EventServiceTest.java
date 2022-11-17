package compani.service;

import compani.model.Event;
import compani.model.File;
import compani.model.User;
import compani.repository.EventRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@RunWith(MockitoJUnitRunner.class)
public class EventServiceTest {
    @Mock
    private EventRepository repository;

    @InjectMocks
    private EventService service;
    private final Event event = new Event(new Date(System.currentTimeMillis()),new File("garry","charly"),new User("thom","gfdgdf"));
    private final List<Event> events = new ArrayList<>();

    @Test
    public void add_Successful() {
        Mockito.when(repository.save(any(Event.class))).thenReturn(event);
        Assertions.assertEquals("garry",service.add(event).getFile().getFileName());
    }
    @Test
    public void add_unSuccessful() {
        Mockito.when(repository.save(any(Event.class))).thenReturn(event);
        Assertions.assertNotEquals(null,service.add(event).getFile().getFileName());
    }
    @Test
    public void update_Successful(){
        Mockito.when(repository.update(any(Event.class))).thenReturn(event);
        Assertions.assertEquals("garry",service.update(event).getFile().getFileName());
    }
    @Test
    public void update_unSuccessful(){
        Mockito.when(repository.update(any(Event.class))).thenReturn(event);
        Assertions.assertNotEquals(null,service.update(event).getFile().getFileName());
    }
    @Test
    public void getById_Successful(){
        Mockito.when(repository.findById(anyInt())).thenReturn(event);
        Assertions.assertEquals("garry",service.find(1).getFile().getFileName());
    }
    @Test
    public void getById_unSuccessful(){
        Mockito.when(repository.findById(anyInt())).thenReturn(event);
        Assertions.assertNotEquals(null,service.find(1).getFile().getFileName());

    }
    @Test
    public void getEvents_Successful(){
        events.add(event);
        Mockito.when(repository.getAll()).thenReturn(events);
        Assertions.assertEquals("garry",service.getEvents().get(0).getFile().getFileName());
    }
    @Test
    public void getEvents_unSuccessful(){
        events.add(event);
        Mockito.when(repository.getAll()).thenReturn(events);
        Assertions.assertNotEquals(null,service.getEvents().get(0).getFile().getFileName());
    }

}