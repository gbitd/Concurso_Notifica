package com.gbitd.concursonotifica;

/**
 * App
 */
public class App {

	public static void main(String[] args) {

        String userSmtp = System.getenv("SMTP_EMAIL_USER");
        String passwordSmtp = System.getenv("SMTP_EMAIL_PASSWORD");
        String hostSmtp = System.getenv("SMTP_HOST");
        Integer portSmtp = Integer.valueOf(System.getenv("SMTP_PORT"));
        String sender = userSmtp;
        String receiver = userSmtp;

        String url = "https://prh.uem.br/res/concurso-publico/edital-n-o-175-2025-prh-concurso-publico-analista-de-informatica-contador-assistente-social-engenheiro-civil-e-engenheiro-eletricista";
        WebScraperRepository repository = new WebScraperRepository("data.txt");
        WebScraper webScraper = new WebScraper(url, repository);

        if (webScraper.checkIfUpdated() == true){
            EmailSender emailSender = new EmailSender(userSmtp, passwordSmtp, hostSmtp, portSmtp, sender, receiver);
            emailSender.sendEmail();
        }
        else
            System.out.println("Não houveram atualizações. Não enviar nada");


    }
}
