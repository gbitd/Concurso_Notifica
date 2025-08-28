package com.gbitd.concursonotifica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * WebScraperRepository
 */
public class WebScraperRepository {

    private int numberOfItems;
    private File dataFile;
    private boolean isEmpty;


     public WebScraperRepository(String dataFilePath) {
        this.dataFile = new File(dataFilePath);
        this.isEmpty = true; // Assume vazio até ler com sucesso
        this.numberOfItems = 0;

        // Se o arquivo não existe, cria vazio
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                System.err.println("Erro ao criar arquivo: " + e.getMessage());
                return;
            }
        }

        try (Scanner leitor = new Scanner(dataFile)) {
            if (leitor.hasNextInt()) {
                this.numberOfItems = leitor.nextInt();
                this.isEmpty = false;
            }
            else
                System.out.println("Arquivo vazio. Atualizar informações");
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }


    public void updateRepository(int newNumberOfItems) {
        this.numberOfItems = newNumberOfItems;
        try (FileWriter writer = new FileWriter(dataFile)) {
            writer.write(String.valueOf(newNumberOfItems));
            this.isEmpty = false;
            writer.close();

        } catch (IOException e) {
            System.out.println("Erro ao escrever o arquivo ao atualizar o repository " + e.getMessage());
            e.printStackTrace();
        }

    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public boolean isEmpty() {
       return this.isEmpty;
    }


}
