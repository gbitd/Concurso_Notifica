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
    private String userSmtp;
    private String passwordSmtp;
    private String hostSmtp;
    private Integer portSmtp;
    private String sender;
    private String receiver;



    public EmailSender(String userSmtp, String passwordSmtp, String hostSmtp, Integer portSmtp, String sender,
            String receiver) {
        this.userSmtp = userSmtp;
        this.passwordSmtp = passwordSmtp;
        this.hostSmtp = hostSmtp;
        this.portSmtp = portSmtp;
        this.sender = sender;
        this.receiver = receiver;

        this.email = EmailBuilder.startingBlank()
            .from("Alerta Concurso", sender)
            .to("Concurseiro", receiver)
            .withSubject("Alerta: Nova atualização sobre o concurso")
            .withPlainText("Nova atualização sobre o concurso. Acessar o site: https://prh.uem.br/res/concurso-publico/edital-n-o-175-2025-prh-concurso-publico-analista-de-informatica-contador-assistente-social-engenheiro-civil-e-engenheiro-eletricista")
            .buildEmail();

        this.mailer = MailerBuilder
            .withSMTPServer(hostSmtp, portSmtp, userSmtp, passwordSmtp)
            .withTransportStrategy(TransportStrategy.SMTP)
            .buildMailer();
    }

    public void sendEmail(){
        mailer.sendMail(email);
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Mailer getMailer() {
        return mailer;
    }

    public void setMailer(Mailer mailer) {
        this.mailer = mailer;
    }

    public String getUserSmtp() {
        return userSmtp;
    }

    public void setUserSmtp(String userSmtp) {
        this.userSmtp = userSmtp;
    }

    public String getPasswordSmtp() {
        return passwordSmtp;
    }

    public void setPasswordSmtp(String passwordSmtp) {
        this.passwordSmtp = passwordSmtp;
    }



/*
    */

}
