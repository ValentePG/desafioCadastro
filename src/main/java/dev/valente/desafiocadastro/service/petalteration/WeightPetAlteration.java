package dev.valente.desafiocadastro.service.petalteration;

import java.util.Scanner;

public class WeightPetAlteration implements IAlterationPetOptions {

    @Override
    public String alterarInfoInFile() {
        Scanner input = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        float weight;
        String userInfoInput;
        String out = "n";
        do{
            try{
                System.out.println("Altere o peso do pet: ");
                userInfoInput = input.nextLine();
                assertMatchesWithRegex(userInfoInput);
                userInfoInput = userInfoInput.replace(",", ".");
                weight = Float.parseFloat(userInfoInput);
                assertWeightIsValid(weight);
                sb.append(weight).append("kg");
                System.out.println("Deseja salvar esta informação?(S/N): " + weight);
                out = input.nextLine();
            }catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
        }while (!out.equalsIgnoreCase("S"));
        return sb.toString().trim();
    }

    private void assertMatchesWithRegex(String input) {
        String regex = "^[0-9,.]*$";
        if(!input.matches(regex)) throw new RuntimeException("Por favor digite apenas numeros aqui!");
    }

    private void assertWeightIsValid(float weight) {
        if(weight > 60.0 || weight < 0.5) throw new RuntimeException("Peso inválido");
    }
}
