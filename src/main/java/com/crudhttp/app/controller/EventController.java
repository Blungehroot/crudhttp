package com.crudhttp.app.controller;

import com.crudhttp.app.service.impl.EventServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"https://crudhttp.herokuapp.com/api/v1/events"}, name = "EventController")
public class EventController extends HttpServlet {
    private final EventServiceImpl eventService;
    private static final ObjectMapper om = new ObjectMapper();

    public EventController() {
        eventService = new EventServiceImpl();
    }

    private void getById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int eventId = Integer.parseInt(req.getParameter("id"));
        resp.setContentType("application/json");
        resp.getWriter().write(om.writeValueAsString(eventService.getById(eventId)));
    }

    private void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.getWriter().write(om.writeValueAsString(eventService.getAll()));
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
