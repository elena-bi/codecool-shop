package com.codecool.shop.dao;

import com.codecool.shop.model.User;

import java.util.Optional;

public interface UserDao {
    void addUser(User user);

    Optional<User> getUser(int id);

    void removeUser(int id);
}
