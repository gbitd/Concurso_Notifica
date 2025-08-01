package com.gbitd.concursonotifica;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;

/**
 * EmailSender
 */
public class EmailSender {

    private Email email;
    private Mailer mailer;

    public EmailSender(Email email, Mailer mailer) {
        this.email = email;
        this.mailer = mailer;
    }

    public EmailSender() {
        this.email = EmailBuilder.startingBlank()
            .from("lollypop", "lolly.pop@pretzelfun.com")
            .to("C. Cane", "candycane@candyshop.org")
            .cc("C. Bo <chocobo@candyshop.org>")
            .withSubject("hey")
            .withPlainText("We should meet up! ;)")
            .buildEmail();

        this.mailer = MailerBuilder
            .withSMTPServer("live.smtp.mailtrap.io", 587, "1a2b3c4d5e6f7g", "password")
            .withTransportStrategy(TransportStrategy.SMTP)
            .buildMailer();
    }

    public void sendEmail(){
        mailer.sendMail(email);
    }



/*
    */

}
