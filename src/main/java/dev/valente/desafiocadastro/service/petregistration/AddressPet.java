package dev.valente.desafiocadastro.service.petregistration;

import dev.valente.desafiocadastro.entidade.Address;
import dev.valente.desafiocadastro.entidade.Pet;
import dev.valente.desafiocadastro.util.ScannerUtils;

import java.util.InputMismatchException;
import java.util.Scanner;

public record AddressPet(String question) implements PetRegistrationOptions {

    @Override
    public void registerPetInfo(Pet pet, Scanner input) {
        String c;
        int n = 0;
        Address address = new Address();
        do{
            do{
                try{
                    System.out.println("Numero da casa: ");
                    n = input.nextInt();
                    ScannerUtils.cleanBuffer(input);
                    address.setNumber(n);
                } catch (InputMismatchException e){
                    ScannerUtils.cleanBuffer(input);
                    System.out.println("Por favor digite o número da casa corretamente");
                }
            }while (n == 0);
            System.out.println("Cidade: ");
            c = input.nextLine();
            address.setCity(c);
            System.out.println("Rua: ");
            c = input.nextLine();
            address.setStreet(c);
            pet.setAddress(address);
            System.out.println("Deseja salvar esta informação?(S/N): " + pet.getAddress());
            c = input.nextLine();
        }while (!c.equalsIgnoreCase("S"));

    }

    @Override
    public String getQuestion() {
        return this.question;
    }
}
