package com.bandeira.sistema_aposentadoria.application.usecases;

import com.bandeira.sistema_aposentadoria.application.gateways.SendingEmailsGateway;
import com.bandeira.sistema_aposentadoria.domain.User;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public class SendRequestConfirmationEmail {

    private final SendingEmailsGateway sendingEmailsGateway;

    public SendRequestConfirmationEmail(SendingEmailsGateway sendingEmailsGateway) {
        this.sendingEmailsGateway = sendingEmailsGateway;
    }

    public void sendRequestConfirmationEmail(User user) throws MessagingException, UnsupportedEncodingException {
        sendingEmailsGateway.sendRequestConfirmationEmail(user);
    }
}
