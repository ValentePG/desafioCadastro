package dev.valente.desafiocadastro.service.petregistration;

import dev.valente.desafiocadastro.entidade.Pet;

import java.util.Scanner;

import static dev.valente.desafiocadastro.util.Constants.NAO_INFORMADO;

public record RacePet(String question) implements PetRegistrationOptions {

    @Override
    public void registerPetInfo(Pet pet, Scanner input) {
        String userInfoInput;
        String out = "n";
        do{
            System.out.println(getQuestion());
            userInfoInput = input.nextLine();
            if(userInfoInput.isEmpty()){
                pet.setRace(NAO_INFORMADO);
                System.out.println("Raça salvo como: " + NAO_INFORMADO);
                return;
            }
            try{
                userInfoInput = input.nextLine();
                assertIfMatchesRegex(userInfoInput);
                pet.setRace(userInfoInput);
                System.out.println("Deseja salvar informação?(S/N): " + pet.getRace());
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

    private void assertIfMatchesRegex(String input) {
        String regex = "^[a-zA-ZáéíóúÁÉÍÓÚçÇ ]*$";
        if (!input.matches(regex)) throw new RuntimeException("Apenas letras são válidas aqui!");
    }
}
