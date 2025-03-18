package dev.valente.desafiocadastro.service.petregistration;

import dev.valente.desafiocadastro.entidade.Pet;

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
                pet.setWeight(weight);
                System.out.println("Deseja salvar esta informação?(S/N): " + pet.getWeight());
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
}
