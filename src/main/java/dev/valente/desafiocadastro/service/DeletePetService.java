package dev.valente.desafiocadastro.service;

import dev.valente.desafiocadastro.util.ScannerUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DeletePetService {

    public void deletePet(List<File> fileToDelete) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String input = "n";
        int in = 0;
        do {
            try {
                System.out.println("Qual pet você quer excluir?");
                in = scanner.nextInt();
                ScannerUtils.cleanBuffer(scanner);
                in -= 1;
                assertIfInIsGreaterThanSizeOfList(in, fileToDelete);
                System.out.println("Tem certeza que deseja excluir (S/N): ");
                input = scanner.nextLine();
                if (input.equalsIgnoreCase("S")) {
                    var file = fileToDelete.remove(in);
                    Files.deleteIfExists(file.toPath());
                    System.out.println("Deletado com sucesso!");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Por favor digite um número válido e de 1 a " + fileToDelete.size());
                ScannerUtils.cleanBuffer(scanner);
            } catch (RuntimeException ex) {
                System.out.println(ex.getMessage());
            }

        } while (!input.equalsIgnoreCase("s"));

    }

    private void assertIfInIsGreaterThanSizeOfList(int input, List<File> fileToCheck) {
        if(input > fileToCheck.size()) throw new RuntimeException("Digite um valor dos que foram mostrados na tela");
    }
}
