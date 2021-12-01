package com.crudhttp.app.controller;

import com.crudhttp.app.model.Event;
import com.crudhttp.app.model.Media;
import com.crudhttp.app.model.User;
import com.crudhttp.app.service.MediaService;
import com.crudhttp.app.service.impl.EventServiceImpl;
import com.crudhttp.app.service.impl.MediaServiceImpl;
import com.crudhttp.app.service.impl.UserServiceImpl;
import com.crudhttp.app.utils.FileUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@WebServlet(urlPatterns = {"/api/v1/media", "/api/v1/media/get", "/api/v1/media/get-all"}, name="MediaController")
@MultipartConfig
public class MediaController extends HttpServlet {
    private final MediaServiceImpl mediaService;
    private final EventServiceImpl eventService;
    private final UserServiceImpl userService;
    private static ObjectMapper om = new ObjectMapper();

    public MediaController() {
        mediaService = new MediaServiceImpl();
        eventService = new EventServiceImpl();
        userService = new UserServiceImpl();
    }

    private void getById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int mediaId = Integer.parseInt(req.getParameter("id"));
        resp.setContentType("application/json");
        resp.getWriter().write(om.writeValueAsString(mediaService.getById(mediaId)));
    }

    private void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.getWriter().write(om.writeValueAsString(mediaService.getAll()));
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Media media = new Media();
        Event event = new Event();
        File file = FileUtil.saveFile(request);
        request.getParameter("file");
        String userId = request.getHeader("user_id");
        User user = userService.getById(Integer.parseInt(userId));
        event.setEventName("Upload");
        event.setMedia(media);
        event.setUser(user);
        media.setFileLink(file.getAbsolutePath());
        media.setFileName(file.getName());
        media.setEvent(event);
        eventService.save(event);
        mediaService.save(media);
        response.setStatus(HttpServletResponse.SC_CREATED);
        response.setContentType("application/json");
        response.getWriter().write(om.writeValueAsString(media));
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        mediaService.deleteById(mediaService.getById(id).getId());
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        File file = FileUtil.saveFile(request);
        request.getParameter("file");
        request.getParameter("id");
        Media media = new Media();
        media.setId(id);
        media.setFileLink(file.getAbsolutePath());
        media.setFileName(file.getName());
        mediaService.update(media);
        response.setContentType("application/json");
        response.getWriter().write(om.writeValueAsString(media));
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/api/v1/media/get":
                getById(req, resp);
                break;
            case "/api/v1/media/get-all":
                getAll(req, resp);
                break;
        }
    }
}
