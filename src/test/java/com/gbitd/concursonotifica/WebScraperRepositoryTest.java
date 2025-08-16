package com.gbitd.concursonotifica;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WebScraperRepositoryTest {
    private static final String TEST_FILE = "datatest.txt";
    private WebScraperRepository repository;

    @BeforeEach
    void setUp() throws IOException {
        // Garante que o arquivo de teste não exista antes de cada teste
        Files.deleteIfExists(Paths.get(TEST_FILE));
        // Cria um novo repositório com o arquivo de teste
        repository = new WebScraperRepository(TEST_FILE);
    }

    @AfterEach
    void tearDown() throws IOException {
        // Limpa o arquivo após cada teste
        Files.deleteIfExists(Paths.get(TEST_FILE));
    }

    @Test
    void testConstructorWithNewFile() {
        assertTrue(repository.isEmpty());
        assertEquals(0, repository.getLastPage());
        assertEquals(0, repository.getNumberOfItems());
    }

    @Test
    void testConstructorWithExistingData() throws IOException {
        // Prepara arquivo com dados de teste
        try (PrintWriter writer = new PrintWriter(TEST_FILE)) {
            writer.println("42");
            writer.println("10");
        }

        // Recria o repositório para ler os dados
        repository = new WebScraperRepository(TEST_FILE);

        assertFalse(repository.isEmpty());
        assertEquals(42, repository.getLastPage());
        assertEquals(10, repository.getNumberOfItems());
    }

    @Test
    void testUpdateRepository() throws IOException {
        // Teste com valores iniciais
        repository.updateRepository(5, 20);
        assertFalse(repository.isEmpty());
        assertEquals(5, repository.getLastPage());
        assertEquals(20, repository.getNumberOfItems());

        // Verifica conteúdo do arquivo
        try (Scanner scanner = new Scanner(new File(TEST_FILE))) {
            assertEquals(5, scanner.nextInt());
            assertEquals(20, scanner.nextInt());
        }
    }

    @Test
    void testMultipleUpdates() throws IOException {
        repository.updateRepository(1, 10);
        assertFalse(repository.isEmpty());
        assertEquals(1, repository.getLastPage());
        assertEquals(10, repository.getNumberOfItems());
        repository.updateRepository(2, 4);
        assertFalse(repository.isEmpty());
        assertEquals(2, repository.getLastPage());
        assertEquals(4, repository.getNumberOfItems());

         try (Scanner scanner = new Scanner(new File(TEST_FILE))) {
            assertEquals(2, scanner.nextInt());
            assertEquals(4, scanner.nextInt());
        }
    }

    @Test
    void testFileCorruptionHandling() throws IOException {
        // Cria arquivo com dados inválidos
        try (PrintWriter writer = new PrintWriter(TEST_FILE)) {
            writer.println("not a number");
            writer.println("123");
        }

        // Deve lidar com erro sem quebrar
        repository = new WebScraperRepository(TEST_FILE);

        assertTrue(repository.isEmpty());
    }
}
