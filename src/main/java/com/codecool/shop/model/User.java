package com.codecool.shop.model;

public class User {
    private final int id;
    private final String name;
    private final String email;
    private final String hashedPassword;
    private final byte[] salt;

    public User(int id, String name, String email, String hashedPassword, byte[] salt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.salt = salt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return hashedPassword;
    }

    public byte[] getSalt() {
        return salt;
    }
}
