package compani.controller;

import compani.model.Event;
import compani.model.File;
import compani.model.User;
import compani.service.EventService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.Printable;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
@WebServlet("/Event")
public class EventController extends HttpServlet {
    private EventService eventService;


    @Override
    public void init() throws ServletException {
        eventService = new EventService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html");
        for (Event event: eventService.getEvents()) {
            writer.println(event);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date date = Date.valueOf(req.getParameter("date"));
        String fileName = req.getParameter("file_name");
        String location = req.getParameter("location");
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        eventService.add(new Event(date,new File(fileName,location),new User(firstName,lastName)));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Date date = Date.valueOf(req.getParameter("date"));
        String fileName = req.getParameter("file_name");
        String location = req.getParameter("location");
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        eventService.update(new Event(id,date,new File(fileName,location),new User(firstName,lastName)));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        eventService.delete(id);
    }
}
