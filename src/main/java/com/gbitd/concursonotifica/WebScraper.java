package com.gbitd.concursonotifica;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


// Atualmente só conecta à página e lista as entradas.
// Fazer lógica para comparar entradas atuais com o registro anterior. Caso haja nova entrada, disparar o e-mail.
// Transformar em classe comum e criar outra Main
public class WebScraper {
    public static void main(String[] args) {
        String url = "https://prh.uem.br/res/concurso-publico/edital-n-o-175-2025-prh-concurso-publico-analista-de-informatica-contador-assistente-social-engenheiro-civil-e-engenheiro-eletricista";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements entries = doc.select(".entry");
            System.out.println(entries.size());
            for (Element entry : entries){
                String entryTitle = entry.select(".contenttype-file").text();
                if (entryTitle.length()> 4)
                    System.out.println(entryTitle);

            }

        } catch (IOException e) {
            System.out.println("Error IOException" + e.getMessage());
            e.printStackTrace();
        }

    }
}
