package dev.valente.desafiocadastro.service.petregistration;

import dev.valente.desafiocadastro.entity.Pet;

import java.util.Scanner;

import static dev.valente.desafiocadastro.util.Constants.NAO_INFORMADO;

public record RacePetRegistration(String question) implements PetRegistrationOptions {

    @Override
    public void registerPetInfo(Pet pet, Scanner input) {
        String userInfoInput;
        String out = "n";
        do{
            System.out.println(getQuestion());
            userInfoInput = input.nextLine();
            if(userInfoInput.isEmpty()){
                pet.setRaca(NAO_INFORMADO);
                System.out.println("Raça salvo como: " + NAO_INFORMADO);
                return;
            }
            try{
                assertIfMatchesRegex(userInfoInput);
                pet.setRaca(userInfoInput);
                System.out.println("Deseja salvar informação?(S/N): " + pet.getRaca());
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
