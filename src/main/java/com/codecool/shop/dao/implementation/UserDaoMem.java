package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;
import com.codecool.shop.service.HashService;

import java.util.*;

public class UserDaoMem implements UserDao {
    private Set<User> users = new HashSet<>();
    private final HashService hashService = new HashService();
    private int currentId = 1;
    private static UserDaoMem instance = null;

    private UserDaoMem() {}

    public static UserDaoMem getInstance() {
        if (instance == null) {
            instance = new UserDaoMem();
        }
        return instance;
    }

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public Optional<User> getUser(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst();
    }

    @Override
    public void removeUser(int id) {
        getUser(id).ifPresent(user -> users.remove(user));
    }

    public int getCurrentId() {
        return currentId;
    }

    public void incrementCurrentId() {
        this.currentId++;
    }

    public boolean isNameUsed(String name) {
        return users.stream().anyMatch(user -> user.getName().equals(name));
    }

    public boolean isEmailUsed(String email) {
        return users.stream().anyMatch(user -> user.getEmail().equals(email));
    }

    public boolean isPasswordUsed(String password) {
        return users.stream().anyMatch(user -> hashService.passwordMatchesHash(password, user.getPasswordHash(), user.getSalt()));
    }

    public Optional<User> getUserWithNamePassword(String username, String password) {
        return users.stream()
                .filter(user -> username.equals(user.getName()))
                .filter(user -> hashService.passwordMatchesHash(password, user.getPasswordHash(), user.getSalt()))
                .findFirst();
    }
}
