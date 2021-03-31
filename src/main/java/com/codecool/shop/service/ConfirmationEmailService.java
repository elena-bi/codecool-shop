package com.codecool.shop.service;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.UserDetailsDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.UserDetailsDaoMem;
import com.codecool.shop.model.Product;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Properties;

public class ConfirmationEmailService {
    private final String FROM_NAME = "fillertext@gmail.com"; // Change to valid email to use
    private final String FROM_PASSWORD = "password";         // Change to valid password to use

    public ConfirmationEmailService() {}

    private Session setupSession() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        return Session.getInstance(prop,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(FROM_NAME, FROM_PASSWORD);
                    }
                });
    }

    public void sendOrderConfirmationEmail() {
        CartDao cart = CartDaoMem.getInstance();
        UserDetailsDao userDetails = UserDetailsDaoMem.getInstance();
        ProductDao productList = ProductDaoMem.getInstance();
        String to = userDetails.getUserEmailAddress();

        Session session = setupSession();

        StringBuilder sb = new StringBuilder("Buyer: ");
        sb.append(userDetails.getUserName())
                .append("\n")
                .append("Address: ")
                .append(userDetails.getUserDeliveryAddress())
                .append("\n")
                .append("Phone number: ")
                .append(userDetails.getPhoneNumber())
                .append("\n\n")
                .append("Order:")
                .append("\n");
        float priceSum = 0;
        for (Map.Entry<Integer, Integer> entry: cart.getCartMap().entrySet()) {
            int productAmount = entry.getValue();
            Product product = productList.find(entry.getKey());
            sb.append(product.getName())
                    .append(" x")
                    .append(productAmount)
                    .append(" : ")
                    .append(product.getDefaultPrice() * productAmount)
                    .append(product.getDefaultCurrency())
                    .append("\n");
            priceSum += product.getDefaultPrice() * productAmount;
        }
        sb.append("Total: ")
                .append(priceSum)
                .append("$");

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_NAME));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Purchase confirmation");
            message.setText(sb.toString());
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendRegistrationConfirmationEmail(String email) {
        Session session = setupSession();
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_NAME));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Registration confirmed");
            message.setText("Your registration has been confirmed.\nThank you for registering!");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
