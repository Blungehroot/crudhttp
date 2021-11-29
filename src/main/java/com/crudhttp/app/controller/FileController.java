package com.crudhttp.app.controller;

import com.crudhttp.app.model.Media;
import com.crudhttp.app.service.MediaService;
import com.crudhttp.app.service.impl.MediaServiceImpl;
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
import java.util.Iterator;
import java.util.List;

@WebServlet(urlPatterns = {"/file", "/file/get", "/file/get-all"}, name="FileController")
@MultipartConfig
public class FileController extends HttpServlet {
    private final MediaServiceImpl mediaService;
    private static ObjectMapper om = new ObjectMapper();

    public FileController() {
        mediaService = new MediaServiceImpl();
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
        File file = FileUtil.saveFile(request);
        request.getParameter("file");
        Media media = new Media();
        media.setFileLink(file.getAbsolutePath());
        media.setFileName(file.getName());
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
            case "/file/get":
                getById(req, resp);
                break;
            case "/file/get-all":
                getAll(req, resp);
                break;
        }
    }
}
