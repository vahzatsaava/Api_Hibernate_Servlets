package compani.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import compani.model.Event;
import compani.service.EventService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.stream.Collectors;

@WebServlet("/Events")
public class EventRestController extends HttpServlet {
    private EventService eventService;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        eventService = new EventService();
        mapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            getAll(req, resp);
        } else {
            getByID(Integer.parseInt(id), req, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(format);
        System.out.println(json);
        Event event = mapper.readerFor(Event.class).readValue(json);
        eventService.add(event);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        mapper.setDateFormat(format);
        Event event = mapper.readerFor(Event.class).readValue(json);
        eventService.update(event);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        int id = Integer.parseInt(req.getParameter("id"));
        eventService.delete(id);
    }

    private void getByID(int id, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.println(mapper.writeValueAsString(eventService.find(id)));
        writer.close();
    }

    private void getAll(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        for (Event event : eventService.getEvents()) {
            writer.println(mapper.writeValueAsString(event));
        }
        writer.close();
    }
}
