package compani.controller;

import compani.model.Event;
import compani.service.EventService;

import java.util.List;

public class EventController {
    private EventService eventService = new EventService();

    public Event add(Event event) {
        return eventService.add(event);
    }

    public Event update(Event event) {
        return eventService.update(event);
    }

    public Event find(int id) {
        return eventService.find(id);
    }

    public List<Event> getEvents() {
        return eventService.getEvents();
    }

    public void delete(int id) {
        eventService.delete(id);
    }
}
