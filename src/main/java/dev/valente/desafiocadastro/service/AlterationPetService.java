package dev.valente.desafiocadastro.service;

import dev.valente.desafiocadastro.util.ScannerUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AlterationPetService {

    public void changePet(List<File> fileToAlterate) {
        Scanner scanner = new Scanner(System.in);
        String input = "n";
        File inputFile;
        int in = 0;
        do {
            try {
                System.out.println("Qual pet você quer alterar?");
                in = scanner.nextInt();
                ScannerUtils.cleanBuffer(scanner);
                in -= 1;
                assertIfInIsGreaterThanSizeOfList(in, fileToAlterate);
                inputFile = fileToAlterate.get(in);
                changingPet(inputFile);
                System.out.println("Confirmar Alterações?(S/N): ");
                input = scanner.nextLine();
            } catch (InputMismatchException ex) {
                System.out.println("Por favor digite um número válido e de 1 a " + fileToAlterate.size());
                ScannerUtils.cleanBuffer(scanner);
            } catch (RuntimeException | IOException ex) {
                System.out.println(ex.getMessage());
            }

        } while (!input.equalsIgnoreCase("s"));

    }

    private void assertIfInIsGreaterThanSizeOfList(int input, List<File> fileToCheck) {
        if (input > fileToCheck.size()) throw new RuntimeException("Digite um valor dos que foram mostrados na tela");
    }

    private void changingPet(File fileToAlterate) throws IOException {
        BufferedReader rd = new BufferedReader(new FileReader(fileToAlterate));
        String line;
        List<String> list = new ArrayList<>();
        while ((line = rd.readLine()) != null) {
            System.out.println(line.substring(4));
            list.add(line);
        }
    }
}
