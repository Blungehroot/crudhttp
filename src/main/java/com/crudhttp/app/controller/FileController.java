package com.crudhttp.app.controller;

import com.crudhttp.app.model.File;

import java.util.List;

public interface FileController {
    File save(File file);

    File update(File file);

    void delete(int id);

    File getById(int id);

    List<File> getAll();
}
