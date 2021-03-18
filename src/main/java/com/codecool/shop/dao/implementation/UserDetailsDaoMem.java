package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.UserDetailsDao;

public class UserDetailsDaoMem implements UserDetailsDao {
    private String userName;
    private String userEmailAddress;
    private String userDeliveryAddress;
    private String phoneNumber;
    private static UserDetailsDaoMem instance;

    private UserDetailsDaoMem() {}

    public static UserDetailsDaoMem getInstance() {
        if (instance == null) {
            instance = new UserDetailsDaoMem();
        }
        return instance;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getUserEmailAddress() {
        return userEmailAddress;
    }

    @Override
    public void setUserEmailAddress(String userEmailAddress) {
        this.userEmailAddress = userEmailAddress;
    }

    @Override
    public String getUserDeliveryAddress() {
        return userDeliveryAddress;
    }

    @Override
    public void setUserDeliveryAddress(String userDeliveryAddress) {
        this.userDeliveryAddress = userDeliveryAddress;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
