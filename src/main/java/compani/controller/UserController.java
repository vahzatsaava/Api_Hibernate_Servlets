package compani.controller;

import compani.model.User;
import compani.repository.hibernate.HibernateUserRepositoryImp;
import compani.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
@WebServlet("/getUser")
public class UserController extends HttpServlet {
    private UserService service;

    @Override
    public void init() throws ServletException {
        service = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            getAll(req, resp);
        } else {
            getById(Integer.parseInt(id), req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        resp.setContentType("application/json");
        if (firstName != null && lastName != null && !firstName.isEmpty() && !lastName.isEmpty()) {
            service.add(new User(firstName, lastName));
        } else {
            writer.println("Please, enter your name and surname !");
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        int id = Integer.parseInt(req.getParameter("id_del"));
        resp.setContentType("application/json");
        service.delete(id);
        writer.println("the object was deleted");
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        resp.setContentType("application/json");
        service.update(new User(id, firstName, lastName));
    }

    private void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        for (User user : service.getUsers()) {
            writer.println("id<br>" + user.getId() + " : " + user.getFirstName() + ":" + user.getLastName());
        }
    }

    private void getById(Integer id, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        User user = service.find(id);
        if (user != null) {
            writer.println("id" + user.getId() + " : " + user.getFirstName() + ":" + user.getLastName());
        } else {
            writer.println("This object not found by this id");
        }
    }
}
