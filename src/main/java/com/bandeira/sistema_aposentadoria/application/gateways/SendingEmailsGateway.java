package com.bandeira.sistema_aposentadoria.application.gateways;

import com.bandeira.sistema_aposentadoria.domain.User;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface SendingEmailsGateway {


    void sendRequestConfirmationEmail(User user) throws MessagingException, UnsupportedEncodingException;
}
