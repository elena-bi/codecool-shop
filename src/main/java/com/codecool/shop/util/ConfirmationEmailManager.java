package com.codecool.shop.util;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.UserDetailsDao;
import com.codecool.shop.dao.implementation.CartDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.UserDetailsDaoMem;
import com.codecool.shop.model.Product;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Properties;

public class ConfirmationEmailManager {
    public static void sendConfirmationEmail() {
        CartDao cart = CartDaoMem.getInstance();
        UserDetailsDao userDetails = UserDetailsDaoMem.getInstance();
        ProductDao productList = ProductDaoMem.getInstance();
        String fromName = "fillertext@gmail.com"; // Change to valid email to use
        String fromPassword = "password";         // Change to valid password to use

        String to = userDetails.getUserEmailAddress();
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromName, fromPassword);
                    }
                });

        StringBuilder sb = new StringBuilder("Buyer: ");
        sb.append(userDetails.getUserName())
                .append("\n")
                .append("Address: ")
                .append(userDetails.getUserDeliveryAddress())
                .append("\n")
                .append("Phnoe number: ")
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
            message.setFrom(new InternetAddress(fromName));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Purchase confirmation");
            message.setText(sb.toString());
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
