package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoMem implements UserDao {
    private List<User> users = new ArrayList<>();
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
}
