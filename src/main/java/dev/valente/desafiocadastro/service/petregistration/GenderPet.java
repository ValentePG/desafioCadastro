package dev.valente.desafiocadastro.service.petregistration;

import dev.valente.desafiocadastro.entidade.Pet;
import dev.valente.desafiocadastro.util.ScannerUtils;

import java.util.Scanner;

public record GenderPet(String question) implements PetRegistrationOptions {

    @Override
    public void registerPetInfo(Pet pet, Scanner input) {
        int c;
        c = input.nextInt();
        ScannerUtils.cleanBuffer(input);
        pet.setGender(c);
        System.out.println("Informação salva: " + pet.getGender());
    }

    @Override
    public String getQuestion() {
        return this.question;
    }
}
