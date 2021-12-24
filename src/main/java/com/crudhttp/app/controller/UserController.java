package com.crudhttp.app.controller;

import com.crudhttp.app.model.Event;
import com.crudhttp.app.model.User;
import com.crudhttp.app.service.impl.EventServiceImpl;
import com.crudhttp.app.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"https://crudhttp.herokuapp.com/api/v1/users"}, name = "UserController")
public class UserController extends HttpServlet {
    private final UserServiceImpl userService;
    private final EventServiceImpl eventService;
    private static ObjectMapper om = new ObjectMapper();

    public UserController() {
        userService = new UserServiceImpl();
        eventService = new EventServiceImpl();
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

    private List<Event> linkEvents(String ids) {
        String paramsIds = ids.replace(",", "");
        String[] idList = paramsIds.split(" ");
        Event event;
        List<Event> events = new ArrayList<>();
        for (int i = 0; i < idList.length; i++) {
            event = eventService.getById(Integer.parseInt(idList[i]));
            events.add(event);
        }
        return events;
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User();
        String userName = req.getParameter("name");
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
        User user = new User();
        int userId = Integer.parseInt(req.getParameter("id"));
        String userName = req.getParameter("name");
        if (req.getParameter("eventids").equals("null")) {
            user.setEvents(null);
        } else {
            String eventIds = req.getParameter("eventids");
            user.setEvents(linkEvents(eventIds));
        }
        user.setId(userId);
        user.setName(userName);
        userService.update(user);
        resp.setContentType("application/json");
        resp.getWriter().write(om.writeValueAsString(user));
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getQueryString() == null) {
            getAll(req, resp);
        } else {
            getById(req, resp);
        }
    }
}
