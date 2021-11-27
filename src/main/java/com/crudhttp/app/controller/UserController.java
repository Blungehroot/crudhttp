package com.crudhttp.app.controller;

import com.crudhttp.app.model.User;
import com.crudhttp.app.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user")
public class UserController extends HttpServlet {
    private final UserServiceImpl userService;

    public UserController() {
        userService = new UserServiceImpl();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setName(request.getParameter("name"));
        userService.save(user);
    }
}
