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

@WebServlet(urlPatterns = {"/file"}, name="FileController")
@MultipartConfig
public class FileController extends HttpServlet {
    private final MediaServiceImpl mediaService;
    private static ObjectMapper om = new ObjectMapper();

    public FileController() {
        mediaService = new MediaServiceImpl();
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File file = FileUtil.saveFile(request);
        request.getParameter("file");
        Media media = new Media();
        media.setFileLink(file.getAbsolutePath());
        media.setFileName(file.getName());
        mediaService.save(media);
        response.setContentType("application/json");
        response.getWriter().write(om.writeValueAsString(media));
    }
}
