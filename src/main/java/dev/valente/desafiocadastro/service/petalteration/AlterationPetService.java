package dev.valente.desafiocadastro.service.petalteration;

import dev.valente.desafiocadastro.factory.PetAlterationOptionsFactory;
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
            } catch (RuntimeException | FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            }

        } while (!input.equalsIgnoreCase("s"));

    }

    private void assertIfInIsGreaterThanSizeOfList(int input, List<File> fileToCheck) {
        if (input > fileToCheck.size()) throw new RuntimeException("Digite um valor dos que foram mostrados na tela");
    }

    private void changingPet(File fileToAlterate) throws FileNotFoundException {
        String line;
        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        int in = 0;
        int count = 0;

        try (BufferedReader rd = new BufferedReader(new FileReader(fileToAlterate))) {
            while ((line = rd.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException | InputMismatchException e) {
            System.out.println(e.getMessage());
        }

        for (String linhas : list) {
            count++;
            System.out.println(count + " - " + linhas.substring(4));
        }
        var tamanhoLista = list.size();
        System.out.println("Qual informação você quer alterar?");
        in = scanner.nextInt();
        ScannerUtils.cleanBuffer(scanner);
        assertIfOptionExist(in, tamanhoLista);
        var alterarFunction = PetAlterationOptionsFactory.createAlterarInfoIT(in);
        var info = alterarFunction.alterarInfoInFile();
        in -= 1;
        count = 0;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileToAlterate))) {
            while (count <= tamanhoLista - 1) {
                if (count == in) {
                    if (count == 0) {
                        bw.write(count + 1 + " - " + info + "\n");
                    } else {
                        bw.write(count + " - " + info + "\n");
                    }
                } else {
                    bw.write(list.get(count) + "\n");
                }
                count++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    private void assertIfOptionExist(int input, int tamanhoLista) {
        if (input > tamanhoLista || input <= 0) throw new RuntimeException("Erro");
    }
}
