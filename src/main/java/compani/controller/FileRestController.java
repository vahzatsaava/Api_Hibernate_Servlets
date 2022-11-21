package compani.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import compani.model.File;
import compani.service.FileService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet("/Files")
public class FileRestController extends HttpServlet {
    private FileService fileService;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        fileService = new FileService();
        mapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id == null) {
            getAll(req, resp);
        }else {
            getByID(Integer.parseInt(id),req,resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        File file = new Gson().fromJson(json, File.class);
        fileService.add(file);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        String json = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        File file = new Gson().fromJson(json, File.class);
        fileService.update(file);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        fileService.delete(id);
    }

    private void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        for (File file : fileService.getFiles()) {
            writer.println(mapper.writeValueAsString(file));
        }
        writer.close();
    }

    private void getByID(int id, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        writer.println(mapper.writeValueAsString(fileService.find(id)));
        writer.close();
    }
}
