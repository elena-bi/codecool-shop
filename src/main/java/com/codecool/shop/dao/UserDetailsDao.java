package com.codecool.shop.dao;

public interface UserDetailsDao {
    String getUserName();

    void setUserName(String userName);

    String getUserEmailAddress();

    void setUserEmailAddress(String userEmailAddress);

    String getUserDeliveryAddress();

    void setUserDeliveryAddress(String userDeliveryAddress);

    String getPhoneNumber();

    void setPhoneNumber(String phoneNumber);

    String getBankName();

    void setBankName(String bankName);

    String getUserCardNumber();

    void setUserCardNumber(String userCardNumber);
}
