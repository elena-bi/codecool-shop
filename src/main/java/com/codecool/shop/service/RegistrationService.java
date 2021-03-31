package com.codecool.shop.service;

import com.codecool.shop.dao.implementation.UserDaoMem;
import com.codecool.shop.model.User;

import java.util.Optional;

public class RegistrationService {
    private UserDaoMem userDaoMem = UserDaoMem.getInstance();
    private HashService hashService;
    private ConfirmationEmailService ces;

    public RegistrationService(HashService hashService, ConfirmationEmailService ces) {
        this.hashService = hashService;
        this.ces = ces;
    }

    public boolean register(String username, String email, String password) {
        if (userDaoMem.isNameUsed(username) || userDaoMem.isEmailUsed(email) || userDaoMem.isPasswordUsed(password)) return false;
        byte[] salt = hashService.getNewSalt();
        Optional<String> hashedPassword = hashService.hashPassword(password, salt);
        if (hashedPassword.isEmpty()) return false;
        User user = new User(userDaoMem.getCurrentId(), username, email, hashedPassword.get(), salt);
        userDaoMem.incrementCurrentId();
        userDaoMem.addUser(user);
        ces.sendRegistrationConfirmationEmail(email);
        return true;
    }
}
