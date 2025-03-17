package dev.valente.desafiocadastro.service.petregistration;

import dev.valente.desafiocadastro.entidade.Pet;

import java.util.Scanner;

public interface PetRegistrationOptions {

    void registerPetInfo(Pet pet, Scanner input);

    String getQuestion();
}
