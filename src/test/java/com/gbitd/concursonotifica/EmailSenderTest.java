package com.gbitd.concursonotifica;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

public class EmailSenderTest {

    private EmailSender emailSender;
    private String userSmtp;
    private String passwordSmtp;
    private String hostSmtp;
    private Integer portSmtp;
    private String sender;
    private String receiver;

    @BeforeEach
    void setUp(){
        try {

            this.userSmtp = System.getenv("SMTP_EMAIL_USER");
            this.passwordSmtp = System.getenv("SMTP_EMAIL_PASSWORD");
            this.hostSmtp = System.getenv("SMTP_HOST");
            this.portSmtp = Integer.valueOf(System.getenv("SMTP_PORT"));

        } catch (SecurityException e) {
            System.out.println("Erro, SecurityException, não foi possível recuperar as variáveis de ambiente." + e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    void testSendEmail(){

    }




}
