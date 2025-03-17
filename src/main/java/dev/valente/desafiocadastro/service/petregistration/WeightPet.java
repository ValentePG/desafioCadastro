package dev.valente.desafiocadastro.service.petregistration;

import dev.valente.desafiocadastro.entidade.Pet;
import dev.valente.desafiocadastro.util.ScannerUtils;

import java.util.Scanner;

public record WeightPet(String question) implements PetRegistrationOptions {

    @Override
    public void registerPetInfo(Pet pet, Scanner input) {
        float c;
        c = input.nextFloat();
        ScannerUtils.cleanBuffer(input);
        pet.setWeight(c);
        System.out.println("Informação salva: " + pet.getWeight());

    }

    @Override
    public String getQuestion() {
        return this.question;
    }
}
