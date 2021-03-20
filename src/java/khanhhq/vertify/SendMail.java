/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khanhhq.vertify;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.sql.SQLException;
import java.util.Random;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.naming.NamingException;
import khanhhq.tbllogin.TblLoginDTO;

/**
 *
 * @author Administrator
 */
public class SendMail {

    public String getCodeRandom() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }

    public String getCodeRandomToDiscount() {
        String alphabet = "1395802467NOPQRSTUVWXYZ";

        StringBuilder sb = new StringBuilder();

        Random random = new Random();

        int length = 10;

        for (int i = 0; i < length; i++) {

            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);

            sb.append(randomChar);
        }

        String randomString = sb.toString();
        return randomString;
    }

    public boolean sendVerify(TblLoginDTO user) throws SQLException, NamingException {
        boolean test = false;
        String toEmail = user.getUserID();
        String fromEmail = "khanhhoang25112000@gmail.com";
        String password = "khanhvip12345";
        String promotion = getCodeRandomToDiscount();

        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

//          
            Message mess = new MimeMessage(session);

            mess.setFrom(new InternetAddress(fromEmail));
            mess.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

            mess.setSubject("User Email vertify");
            mess.setText("Registered successfully. Please vertify your account using this code: " + user.getCode() + promotion);
            Transport.send(mess);
            test = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }
}
