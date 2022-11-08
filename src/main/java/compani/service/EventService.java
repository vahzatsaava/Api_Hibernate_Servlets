package compani.service;

import compani.model.Event;
import compani.model.File;
import compani.repository.EventRepository;
import compani.repository.hibernate.HibernateEventRepository;
import compani.repository.hibernate.HibernateFileRepositoryImpl;

import java.util.List;

public class EventService {
    private final EventRepository eventRepository;

    public EventService() {
        this.eventRepository = new HibernateEventRepository();
    }

    public Event add(Event event) {
        return eventRepository.save(event);
    }

    public Event update(Event event) {
        return eventRepository.update(event);
    }

    public Event find(int id) {
        return eventRepository.findById(id);
    }

    public List<Event> getEvents() {
        return eventRepository.getAll();
    }

    public void delete(int id) {
        eventRepository.deleteById(id);
    }
}
