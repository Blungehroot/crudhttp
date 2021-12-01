package com.crudhttp.app.controller;

import com.crudhttp.app.model.Event;
import com.crudhttp.app.service.impl.EventServiceImpl;
import com.crudhttp.app.service.impl.MediaServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api/v1/events", "/api/v1/events/get", "/api/v1/events/get-all"}, name = "EventController")
public class EventController extends HttpServlet {
    private final EventServiceImpl eventService;
    private final MediaServiceImpl mediaService;
    private static final ObjectMapper om = new ObjectMapper();

    public EventController() {
        eventService = new EventServiceImpl();
        mediaService = new MediaServiceImpl();
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
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        eventService.deleteById(eventService.getById(id).getId());
    }

    @Override
    public void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Event event = new Event();
        int eventId = Integer.parseInt(req.getParameter("id"));
        String eventName = req.getParameter("eventname");
        if (req.getParameter("mediaid").equals("null")) {
            event.setMedia(null);
        } else {
            int mediaId = Integer.parseInt(req.getParameter("mediaid"));
            event.setMedia(mediaService.getById(mediaId));
        }
        event.setId(eventId);
        event.setEventName(eventName);
        eventService.update(event);
        resp.setContentType("application/json");
        resp.getWriter().write(om.writeValueAsString(event));
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/api/v1/events/get":
                getById(req, resp);
                break;
            case "/api/v1/events/get-all":
                getAll(req, resp);
                break;
        }
    }
}
