package dev.valente.desafiocadastro.service.petregistration;

import dev.valente.desafiocadastro.entidade.Address;
import dev.valente.desafiocadastro.entidade.Pet;
import dev.valente.desafiocadastro.util.ScannerUtils;

import java.util.InputMismatchException;
import java.util.Scanner;

public record AddressPet(String question) implements PetRegistrationOptions {

    @Override
    public void registerPetInfo(Pet pet, Scanner input) {
        String infoUserInput;
        String out;
        int number = 0;
        Address address = new Address();
        do{
            System.out.println(getQuestion());
            do{
                try{
                    System.out.println("Numero da casa: ");
                    number = input.nextInt();
                    ScannerUtils.cleanBuffer(input);
                    address.setNumber(number);
                } catch (InputMismatchException e){
                    ScannerUtils.cleanBuffer(input);
                    System.out.println("Por favor digite o número da casa corretamente");
                }
            }while (number == 0);
            System.out.println("Cidade: ");
            infoUserInput = input.nextLine();
            address.setCity(infoUserInput);
            System.out.println("Rua: ");
            infoUserInput = input.nextLine();
            address.setStreet(infoUserInput);
            pet.setAddress(address);
            System.out.println("Deseja salvar esta informação?(S/N): " + pet.getAddress());
            out = input.nextLine();
        } while (!out.equalsIgnoreCase("S"));
    }

    @Override
    public String getQuestion() {
        return this.question;
    }
}
