package com.crudhttp.app.service;

import com.crudhttp.app.model.User;

import java.util.List;

public interface UserService {
    User getById(int id);

    List<User> getAll();

    User save(User user);

    User update(User user);

    void deleteById(int id);
}
