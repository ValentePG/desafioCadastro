package dev.valente.desafiocadastro.service.petalteration;

import dev.valente.desafiocadastro.factory.PetAlterationOptionsFactory;
import dev.valente.desafiocadastro.util.ScannerUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PetServiceAlteration {

    public static File changeInfoFromPet(File fileToAlterate) {
        rewriteFileWithNewInfo(fileToAlterate);
        return fileToAlterate;
    }

    private static void rewriteFileWithNewInfo(File fileToAlterate) {
        var linesList = getListWithLinesFromFile(fileToAlterate);
        var tamanhoLista = linesList.size();
        int count = 0;
        int inputUserInt = getChoosedOption(linesList);
        var infoToChange = getInfoToChangeFromUser(inputUserInt);
        inputUserInt -= 1;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileToAlterate))) {
            while (count <= tamanhoLista - 1) {
                if (count == inputUserInt) {
                    if (count == 0) {
                        bw.write(count + 1 + " - " + infoToChange + "\n");
                    } else {
                        bw.write(count + " - " + infoToChange + "\n");
                    }
                } else {
                    bw.write(linesList.get(count) + "\n");
                }
                count++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static int getChoosedOption(List<String> options) {
        Scanner scanner = new Scanner(System.in);
        int tamanhoLista = options.size();
        int inputUserInt = 0;
        showOptions(options);
        System.out.println("Qual informação você quer alterar?");
        try {
            inputUserInt = scanner.nextInt();
            ScannerUtils.cleanBuffer(scanner);
            assertIfOptionExist(inputUserInt, tamanhoLista);
        } catch (InputMismatchException ex) {
            ScannerUtils.cleanBuffer(scanner);
            System.out.println(ex.getMessage());
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }
        return inputUserInt;
    }

    private static String getInfoToChangeFromUser(int input) {
        var alterarFunction = PetAlterationOptionsFactory.createAlterarInfoIT(input);
        return alterarFunction.alterarInfoInFile();
    }

    private static void showOptions(List<String> options) {
        int count = 0;
        for (String linhas : options) {
            count++;
            System.out.println(count + " - " + linhas.substring(4));
        }
    }

    private static List<String> getListWithLinesFromFile(File fileToAlterate) {
        String line;
        List<String> linesList = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(fileToAlterate))) {
            while ((line = rd.readLine()) != null) {
                linesList.add(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return linesList;
    }

    private static void assertIfOptionExist(int input, int tamanhoLista) {
        if (input > tamanhoLista || input <= 0) throw new RuntimeException("Erro");
    }
}
