package com.crudhttp.app.service.impl;

import com.crudhttp.app.model.User;
import com.crudhttp.app.repository.impl.UserRepositoryImpl;
import com.crudhttp.app.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepositoryImpl userRepository;

    public UserServiceImpl() {
        userRepository = new UserRepositoryImpl();
    }

    public UserServiceImpl(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getById(int id) {
        return userRepository.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.update(user);
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
}
