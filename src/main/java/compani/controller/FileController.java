package compani.controller;

import compani.model.File;
import compani.service.FileService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/Files")
public class FileController extends HttpServlet {
    private FileService fileService;

    @Override
    public void init() throws ServletException {
        fileService = new FileService();
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

    private void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        for (File file : fileService.getFiles()) {
            resp.setContentType("application/json");
            writer.println(file);
        }
    }

    private void getByID(int id, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        writer.println(fileService.find(id));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("file_name");
        String location = req.getParameter("location");
        fileService.add(new File(fileName, location));

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String fileName = req.getParameter("file_name");
        String location = req.getParameter("location");
        fileService.update(new File(id, fileName, location));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        fileService.delete(id);
    }
}
