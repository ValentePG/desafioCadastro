package dev.valente.desafiocadastro.service.petregistration;

import dev.valente.desafiocadastro.entidade.Address;
import dev.valente.desafiocadastro.entidade.Pet;
import dev.valente.desafiocadastro.util.ScannerUtils;

import java.util.Scanner;

public record AddressPet(String question) implements PetRegistrationOptions {

    @Override
    public void registerPetInfo(Pet pet, Scanner input) {
        String c;
        int n;
        Address endereco = new Address();
        System.out.println("Numero da casa: ");
        n = input.nextInt();
        ScannerUtils.cleanBuffer(input);
        endereco.setNumber(n);
        System.out.println("Cidade: ");
        c = input.nextLine();
        endereco.setCity(c);
        System.out.println("Rua: ");
        c = input.nextLine();
        endereco.setStreet(c);
        pet.setAddress(endereco);
        System.out.println("Informação salva: " + pet.getAddress());
    }

    @Override
    public String getQuestion() {
        return this.question;
    }
}
