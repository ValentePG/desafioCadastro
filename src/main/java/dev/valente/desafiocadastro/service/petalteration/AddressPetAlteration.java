package dev.valente.desafiocadastro.service.petalteration;

import dev.valente.desafiocadastro.util.ScannerUtils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AddressPetAlteration implements IAlterationPetOptions {

    @Override
    public String alterarInfoInFile() {
        Scanner input = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        String infoUserInput;
        String out;
        int number = 0;
        do {
            System.out.println("Altere seu endereço");
            do {
                try {
                    System.out.println("Numero da casa: ");
                    number = input.nextInt();
                    sb.append(number).append(", ");
                    ScannerUtils.cleanBuffer(input);
                } catch (InputMismatchException e) {
                    ScannerUtils.cleanBuffer(input);
                    System.out.println("Por favor digite o número da casa corretamente");
                }
            } while (number == 0);
            System.out.println("Cidade: ");
            infoUserInput = input.nextLine();
            sb.append(infoUserInput).append(", ");
            System.out.println("Rua: ");
            infoUserInput = input.nextLine();
            sb.append(infoUserInput).append(", ");
            System.out.println("Deseja salvar esta informação?(S/N): ");
            out = input.nextLine();
        } while (!out.equalsIgnoreCase("S"));

        return sb.toString().trim();
    }
}
