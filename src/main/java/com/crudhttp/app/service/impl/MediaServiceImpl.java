package com.crudhttp.app.service.impl;

import com.crudhttp.app.model.Media;
import com.crudhttp.app.repository.impl.MediaRepositoryImpl;
import com.crudhttp.app.service.MediaService;

import java.util.List;

public class MediaServiceImpl implements MediaService {
    private final MediaRepositoryImpl mediaRepository;

    public MediaServiceImpl() {
        mediaRepository = new MediaRepositoryImpl();
    }

    @Override
    public Media getById(int id) {
        return mediaRepository.getById(id);
    }

    @Override
    public List<Media> getAll() {
        return mediaRepository.getAll();
    }

    @Override
    public Media save(Media media) {
        return mediaRepository.save(media);
    }

    @Override
    public Media update(Media media) {
        return mediaRepository.update(media);
    }

    @Override
    public void deleteById(int id) {
        mediaRepository.deleteById(id);
    }
}
