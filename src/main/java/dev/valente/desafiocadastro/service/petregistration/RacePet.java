package dev.valente.desafiocadastro.service.petregistration;

import dev.valente.desafiocadastro.entidade.Pet;

import java.util.Scanner;

public record RacePet(String question) implements PetRegistrationOptions {

    @Override
    public void registerPetInfo(Pet pet, Scanner input) {
        String c;
        c = input.nextLine();
        pet.setRace(c);
        System.out.println("Informação salva: " + pet.getRace());
    }

    @Override
    public String getQuestion() {
        return this.question;
    }
}
