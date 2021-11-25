package com.crudhttp.app.service;

import com.crudhttp.app.model.File;
import com.crudhttp.app.model.User;

import java.util.List;

public interface FileService {
    File getById(int id);

    List<File> getAll();

    File save(File file);

    File update(File file);

    void deleteById(int id);
}
