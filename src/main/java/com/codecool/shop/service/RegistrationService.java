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
        Optional<String> hashedPassword = hashService.hashPassword(password, salt);
        if (hashedPassword.isEmpty()) return false;
        User user = new User(userDaoMem.getCurrentId(), username, email, hashedPassword.get(), salt);
        userDaoMem.incrementCurrentId();
        userDaoMem.addUser(user);
        ConfirmationEmailService.getInstance().sendRegistrationConfirmationEmail(email);
        return true;
    }
}
