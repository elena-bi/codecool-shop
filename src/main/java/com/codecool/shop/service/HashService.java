package com.codecool.shop.service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Optional;

public class HashService {
    private static final int ITERATION = 65536;
    private static final int KEY_LENGTH = 128;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final SecureRandom secureRandom = new SecureRandom();
    private static HashService instance = null;

    private HashService() {}

    public static HashService getInstance() {
        if (instance == null) {
            instance = new HashService();
        }
        return instance;
    }

    /**
     * Generates a hashed password with salt
     * @param password Plain original String
     * @return String of hashed password
     */
    public Optional<String> hashPassword(String password) {
        byte[] salt = getNewSalt();
        return hashWithSalt(password, salt);
    }

    /**
     * Generates a hashed password with salt
     * @param password Plain original String
     * @param salt Used for hashing
     * @return String of hashed password
     */
    public Optional<String> hashPassword(String password, byte[] salt) {
        return hashWithSalt(password, salt);
    }

    public boolean passwordMatchesHash(String password, String hash, byte[] salt) {
        Optional<String> hashedPassword = hashPassword(password);
        return hashedPassword.map(s -> s.equals(hash)).orElse(false);
    }

    private Optional<String> hashWithSalt(String password, byte[] salt) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATION, KEY_LENGTH);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] hashedPassword = factory.generateSecret(spec).getEncoded();
            return Optional.of(Base64.getEncoder().encodeToString(hashedPassword));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            System.err.println("Exception in hashWithSalt()");
            return Optional.empty();
        }
    }

    private byte[] getNewSalt() {
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }
}
