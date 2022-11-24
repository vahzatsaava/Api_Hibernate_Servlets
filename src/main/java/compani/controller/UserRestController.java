package compani.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import compani.model.Event;
import compani.model.User;
import compani.service.EventService;
import compani.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet("/Users")
public class UserRestController extends HttpServlet {
    private UserService userService;
    private ObjectMapper mapper;
    private EventService eventService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
        mapper = new ObjectMapper();
        eventService = new EventService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        resp.setContentType("application/json");
        if (id == null) {
            getAll(req, resp);
        } else {
            getById(Integer.parseInt(id), req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        User user = new Gson().fromJson(json, User.class);
        if (user != null) {
            userService.add(user);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        int id = Integer.parseInt(req.getParameter("id_del"));
        resp.setContentType("application/json");
        userService.delete(id);
        writer.println("the object was deleted");
        writer.close();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        User user = new Gson().fromJson(json, User.class);
        userService.update(user);
    }

    private void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        for (User user : userService.getUsers()) {
            out.println(mapper.writeValueAsString(user));
        }
        out.close();

    }

    private void getById(Integer id, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Event event = eventService.getEvents().stream().filter(i -> i.getUser().getId() == id).findFirst().orElse(null);
        String userJson = mapper.writeValueAsString(userService.find(id));
        String eventJson = mapper.writeValueAsString(event);
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        writer.println(userJson + eventJson);
        writer.close();
    }

}
