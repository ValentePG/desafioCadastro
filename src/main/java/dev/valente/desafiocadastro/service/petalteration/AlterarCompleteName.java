package dev.valente.desafiocadastro.service.petalteration;

import java.util.Scanner;

import static dev.valente.desafiocadastro.util.Constants.NAO_INFORMADO;

public class AlterarCompleteName implements AlterarInfoIT {

    @Override
    public String alterarInfoInFile() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        String infoUserInput;
        String out;
        do{
            System.out.println("Insira o nome completo do pet");
            infoUserInput = scanner.nextLine();
            sb.append(infoUserInput);
            if(infoUserInput.isEmpty()){
                System.out.println("Nome salvo como: " + NAO_INFORMADO);
                return NAO_INFORMADO;
            }
            assertIfMatchesRegex(infoUserInput);

            assertIfLengthIsEqual2(infoUserInput);

            System.out.println("Deseja salvar esta informação?(S/N) " + infoUserInput);
            out = scanner.nextLine();
        } while (!out.equalsIgnoreCase("S"));
        return sb.toString().trim();
    }

    private void assertIfMatchesRegex(String input) {
        String regex = "^[a-zA-ZáéíóúÁÉÍÓÚçÇ ]*$";
        if (!input.matches(regex)) throw new RuntimeException("Apenas letras são válidas aqui!");
    }

    private void assertIfLengthIsEqual2(String input){
        String[] name = input.split(" ");
        if (!(name.length == 2)) throw new RuntimeException("Por favor digite nome e sobrenome!");
    }
}
