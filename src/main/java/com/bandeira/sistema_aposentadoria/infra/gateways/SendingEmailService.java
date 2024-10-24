package com.bandeira.sistema_aposentadoria.infra.gateways;

import com.bandeira.sistema_aposentadoria.application.gateways.SendingEmailsGateway;
import com.bandeira.sistema_aposentadoria.domain.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

public class SendingEmailService implements SendingEmailsGateway{

    private final JavaMailSender emailSender;

    public SendingEmailService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public String setFrom = "pedro.amp002@gmail.com";
    public String senderName = "Escola Hipotética";


    @Override
    public void sendRequestConfirmationEmail(User user) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(setFrom, senderName);
        helper.setTo(user.getEmail());
        helper.setSubject(user.getName() + "Seu beneficio...");
        helper.setText("Seu benefico está sendo analisado...");


        emailSender.send(message);
    }
}
