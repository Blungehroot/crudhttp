package com.crudhttp.app.controller;

import com.crudhttp.app.model.File;
import com.crudhttp.app.service.impl.FileServiceImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@WebServlet(urlPatterns = "/file")
public class FileController extends HttpServlet {
    private static final int fileMaxSize = 100 * 1024;
    private static final int memMaxSize = 100 * 1024;
    private String filePath = "./src/main/resources/uploads/";

    private java.io.File fileUpload;
    private final FileServiceImpl fileService;

    public FileController() {
        fileService = new FileServiceImpl();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File file = new File();
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        diskFileItemFactory.setRepository(new java.io.File(filePath));
        diskFileItemFactory.setSizeThreshold(memMaxSize);

        ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
        upload.setSizeMax(fileMaxSize);

        try {
            List fileItems = upload.parseRequest(request);

            Iterator iterator = fileItems.iterator();

            while (iterator.hasNext()) {
                FileItem fileItem = (FileItem) iterator.next();
                if (!fileItem.isFormField()) {

                    String fileName = fileItem.getName();
                    if (fileName.lastIndexOf("\\") >= 0) {
                        fileUpload = new java.io.File(filePath +
                                fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        fileUpload = new java.io.File(filePath +
                                fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
                    fileItem.write(fileUpload);
                    file.setFileLink(fileUpload.getAbsolutePath());
                    file.setFileName(fileUpload.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        fileService.save(file);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
