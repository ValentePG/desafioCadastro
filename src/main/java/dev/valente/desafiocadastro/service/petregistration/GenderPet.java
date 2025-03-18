package dev.valente.desafiocadastro.service.petregistration;

import dev.valente.desafiocadastro.entidade.Pet;
import dev.valente.desafiocadastro.util.ScannerUtils;

import java.util.InputMismatchException;
import java.util.Scanner;

public record GenderPet(String question) implements PetRegistrationOptions {

    @Override
    public void registerPetInfo(Pet pet, Scanner input) {
        int infoUserInput;
        String out = "n";
        do{
            try{
                System.out.println(getQuestion());
                infoUserInput = input.nextInt();
                ScannerUtils.cleanBuffer(input);
                pet.setGender(infoUserInput);
                System.out.println("Deseja salvar esta informação?(S/N): " + pet.getGender());
                out = input.nextLine();
            } catch (IllegalStateException ex){
                System.out.println(ex.getMessage());
            }
        }while (!out.equalsIgnoreCase("S"));
    }

    @Override
    public String getQuestion() {
        return this.question;
    }
}
