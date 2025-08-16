package com.gbitd.concursonotifica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * WebScraperRepository
 */
public class WebScraperRepository {

	private int lastPage;
    private int numberOfItems;
    private File dataFile;
    private boolean isEmpty;





    public WebScraperRepository() {
        this.dataFile = new File("data.txt");
        this.isEmpty = true; // Assume vazio até ler com sucesso

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
                this.lastPage = leitor.nextInt();
                this.numberOfItems = leitor.nextInt();
                this.isEmpty = false;
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }

    // Para testes
     public WebScraperRepository(String dataFilePath) {
        this.dataFile = new File(dataFilePath);
        this.isEmpty = true; // Assume vazio até ler com sucesso

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
                this.lastPage = leitor.nextInt();
                this.numberOfItems = leitor.nextInt();
                this.isEmpty = false;
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }


    public void updateRepository(int newLastPage, int newNumberOfItems) {
        this.lastPage = newLastPage;
        this.numberOfItems = newNumberOfItems;
        try {
            FileWriter writer = new FileWriter(dataFile);
            writer.write(String.valueOf(newLastPage) + "\n");
            writer.write(String.valueOf(newNumberOfItems));
            this.isEmpty = false;
            writer.close();

        } catch (IOException e) {
            System.out.println("Error ao escrever o arquivo ao atualizar o repository " + e.getMessage());
            e.printStackTrace();
        }

    }

    public int getLastPage() {
        return lastPage;
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }

    public boolean isEmpty() {
       return this.isEmpty;
    }


}
