package com.crudhttp.app.controller;

import com.crudhttp.app.model.User;
import com.crudhttp.app.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/users", "/users/get", "/users/get-all"}, name = "UserController")
public class UserController extends HttpServlet {
    private final UserServiceImpl userService;
    private static ObjectMapper om = new ObjectMapper();

    public UserController() {
        userService = new UserServiceImpl();
    }

    private void getById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("id"));
        resp.setContentType("application/json");
        resp.getWriter().write(om.writeValueAsString(userService.getById(userId)));
    }

    private void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.getWriter().write(om.writeValueAsString(userService.getAll()));
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("name");
        User user = new User();
        user.setName(userName);
        userService.save(user);
        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.setContentType("application/json");
        resp.getWriter().write(om.writeValueAsString(user));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        userService.deleteById(userService.getById(id).getId());
    }

    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = Integer.parseInt(req.getParameter("id"));
        String userName = req.getParameter("name");
        User user = new User();
        user.setId(userId);
        user.setName(userName);
        userService.update(user);
        resp.setContentType("application/json");
        resp.getWriter().write(om.writeValueAsString(user));
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/users/get":
                getById(req, resp);
                break;
            case "/users/get-all":
                getAll(req, resp);
                break;
        }
    }
}
