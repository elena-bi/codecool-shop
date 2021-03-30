package com.codecool.shop.service;

import com.codecool.shop.dao.implementation.UserDaoMem;

public class RegistrationService {
    private UserDaoMem userDaoMem = UserDaoMem.getInstance();
    private static RegistrationService instance = null;

    private RegistrationService() {}

    public RegistrationService getInstance() {
        if (instance == null) {
            instance = new RegistrationService();
        }
        return instance;
    }
}
