package dev.valente.desafiocadastro.service.petalteration;

import java.util.Scanner;

import static dev.valente.desafiocadastro.util.Constants.NAO_INFORMADO;

public class AlterarRace implements AlterarInfoIT{

    @Override
    public String alterarInfoInFile() {
        Scanner input = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        String userInfoInput;
        String out = "n";
        do{
            System.out.println("Alterar raça: ");
            userInfoInput = input.nextLine();
            if(userInfoInput.isEmpty()){
                sb.append(NAO_INFORMADO);
                System.out.println("Raça salvo como: " + NAO_INFORMADO);
                return sb.toString().trim();
            }
            try{
                assertIfMatchesRegex(userInfoInput);
                sb.append(userInfoInput);
                System.out.println("Deseja salvar informação?(S/N): " + userInfoInput);
                out = input.nextLine();
            }catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
        }while (!out.equalsIgnoreCase("S"));

        return sb.toString().trim();
    }

    private void assertIfMatchesRegex(String input) {
        String regex = "^[a-zA-ZáéíóúÁÉÍÓÚçÇ ]*$";
        if (!input.matches(regex)) throw new RuntimeException("Apenas letras são válidas aqui!");
    }
}
