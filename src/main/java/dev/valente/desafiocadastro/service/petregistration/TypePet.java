package dev.valente.desafiocadastro.service.petregistration;

import dev.valente.desafiocadastro.entidade.Pet;
import dev.valente.desafiocadastro.util.ScannerUtils;

import java.util.InputMismatchException;
import java.util.Scanner;

public record TypePet(String question) implements PetRegistrationOptions {

    @Override
    public void registerPetInfo(Pet pet, Scanner input) {
        int userInfoInput;
        String out = "n";
        do{
            try{
                System.out.println(getQuestion());
                System.out.println("Digite 1 para cachorro e 2 para gato");
                userInfoInput = input.nextInt();
                ScannerUtils.cleanBuffer(input);
                pet.setType(userInfoInput);
                System.out.println("Deseja salvar esta informação?(S/N): " + pet.getType());
                out = input.nextLine();
            } catch (IllegalStateException e){
                System.out.println(e.getMessage());
            } catch (InputMismatchException e){
                System.out.println("Por favor digite apenas 1 ou 2");
                ScannerUtils.cleanBuffer(input);
            }

        } while (!out.equalsIgnoreCase("S"));

    }

    @Override
    public String getQuestion() {
        return this.question;
    }
}
