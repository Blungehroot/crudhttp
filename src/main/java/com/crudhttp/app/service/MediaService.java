package com.crudhttp.app.service;

import com.crudhttp.app.model.Media;

import java.util.List;

public interface MediaService {
    Media getById(int id);

    List<Media> getAll();

    Media save(Media media);

    Media update(Media media);

    void deleteById(int id);
}
