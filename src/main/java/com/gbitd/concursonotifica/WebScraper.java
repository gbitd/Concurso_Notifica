package com.gbitd.concursonotifica;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


// Atualmente só conecta à página e lista as entradas.
// Fazer lógica para comparar entradas atuais com o registro anterior. Caso haja nova entrada, disparar o e-mail.
// Transformar em classe comum e criar outra Main
//
// Checar se há segunda página, se sim, fazer webscrapping da última página
// Criar arquivo data.dat caso não exista. Armazenar na primeira linha em que página está, e na segunda linha quantos itens (Criar classe repository para isso)
// Apenas checar se há diferenças entre o número de items armazenado é suficiente para assumir que houve mudança e mandar notificação! Facilita a vida
// para o caso de novas páginas
public class WebScraper {
    private String url;
    private WebScraperRepository repository;

    public WebScraper(String url, WebScraperRepository repository) {
        this.url = url;
        this.repository = repository;
    }


    // Retorna true se a página foi atualizada, e false se não foi
    public boolean checkIfUpdated(){
       try {
            Document doc = Jsoup.connect(url).get();

            // Se existir paginador, significa que os novos elementos estão na última página
            Element paginator = doc.selectFirst(".pagination");
            if (paginator == null){
                Elements entries = doc.select(".entry");
                int currentNumberOfItems = entries.size();
                if (currentNumberOfItems != repository.getNumberOfItems()){
                    repository.updateRepository(currentNumberOfItems);
                    return true;
                }
                else
                    return false;
            }
            else{
                // Seleciona o link do next page. Quase sempre serão apenas duas páginas. Se não, mudar esta lógica no futuro.
                Element linkNextPage = doc.selectFirst(".pagination .next a");
                if (linkNextPage != null){
                    String urlNextPage = linkNextPage.attr("href");
                    Document doc2 = Jsoup.connect(urlNextPage).get();
                    Elements entries = doc2.select(".entry");
                    int currentNumberOfItems = entries.size();
                    System.out.println(entries.size());
                    if (currentNumberOfItems != repository.getNumberOfItems()){
                        repository.updateRepository(currentNumberOfItems);
                        return true;
                    }
                    else
                        return false;
                }
                else
                    throw new IllegalStateException("Link para a próxima página não encontrado");
            }

        } catch (IOException e) {
            System.out.println("Error IOException" + e.getMessage());
            e.printStackTrace();
        } catch (IllegalStateException e){
            System.out.println("Error IllegalStateException" + e.getMessage());
            e.printStackTrace();
        }

        return false;

    }

    public void armazenaDados(int numberOfItems){
       repository.updateRepository(numberOfItems);
    }

}
