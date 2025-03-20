package dev.valente.desafiocadastro.service.petregistration;

import dev.valente.desafiocadastro.entity.Pet;

import java.util.Scanner;

public record WeightPet(String question) implements PetRegistrationOptions {

    @Override
    public void registerPetInfo(Pet pet, Scanner input) {
        float weight;
        String userInfoInput;
        String out = "n";
        do{
            try{
                System.out.println(getQuestion());
                userInfoInput = input.nextLine();
                assertMatchesWithRegex(userInfoInput);
                userInfoInput = userInfoInput.replace(",", ".");
                weight = Float.parseFloat(userInfoInput);
                assertWeightIsValid(weight);
                pet.setPeso(weight);
                System.out.println("Deseja salvar esta informação?(S/N): " + pet.getPeso());
                out = input.nextLine();
            }catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
        }while (!out.equalsIgnoreCase("S"));

    }

    @Override
    public String getQuestion() {
        return this.question;
    }

    private void assertMatchesWithRegex(String input) {
        String regex = "^[0-9,.]*$";
        if(!input.matches(regex)) throw new RuntimeException("Por favor digite apenas numeros aqui!");
    }

    private void assertWeightIsValid(float weight) {
        if(weight > 60.0 || weight < 0.5) throw new RuntimeException("Peso inválido");
    }
}
