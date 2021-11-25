package com.crudhttp.app.service.impl;

import com.crudhttp.app.model.File;
import com.crudhttp.app.repository.impl.FileRepositoryImpl;
import com.crudhttp.app.service.FileService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepositoryImpl fileRepository;

    @Override
    public File getById(int id) {
        return fileRepository.getById(id);
    }

    @Override
    public List<File> getAll() {
        return fileRepository.getAll();
    }

    @Override
    public File save(File file) {
        return fileRepository.save(file);
    }

    @Override
    public File update(File file) {
        return fileRepository.update(file);
    }

    @Override
    public void deleteById(int id) {
        fileRepository.deleteById(id);
    }
}
