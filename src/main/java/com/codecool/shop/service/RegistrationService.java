package com.codecool.shop.service;

import com.codecool.shop.dao.implementation.UserDaoMem;
import com.codecool.shop.model.User;

import java.util.Optional;

public class RegistrationService {
    private UserDaoMem userDaoMem = UserDaoMem.getInstance();
    private HashService hashService = HashService.getInstance();
    private static RegistrationService instance = null;

    private RegistrationService() {}

    public static RegistrationService getInstance() {
        if (instance == null) {
            instance = new RegistrationService();
        }
        return instance;
    }

    public boolean register(String username, String email, String password) {
        if (userDaoMem.isNameUsed(username) || userDaoMem.isEmailUsed(email) || userDaoMem.isPasswordUsed(password)) return false;
        byte[] salt = hashService.getNewSalt();
        hashService.hashPassword(password, salt);
        User user = new User(userDaoMem.getCurrentId(), username, password, email, salt);
        userDaoMem.incrementCurrentId();
        userDaoMem.addUser(user);
        return true;
    }
}
