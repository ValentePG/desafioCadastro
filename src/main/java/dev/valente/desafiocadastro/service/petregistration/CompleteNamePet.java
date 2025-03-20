package dev.valente.desafiocadastro.service.petregistration;

import dev.valente.desafiocadastro.entidade.Pet;

import static dev.valente.desafiocadastro.util.Constants.NAO_INFORMADO;

import java.util.Scanner;

public record CompleteNamePet(String question) implements PetRegistrationOptions {

    @Override
    public void registerPetInfo(Pet pet, Scanner input) {

        String infoUserInput;
        String out = "n";
        do{
            System.out.println(getQuestion());
            infoUserInput = input.nextLine();
            if(infoUserInput.isEmpty()){
                pet.setNomeCompleto(NAO_INFORMADO);
                System.out.println("Nome salvo como: " + NAO_INFORMADO);
                return;
            }

            try {
                assertIfMatchesRegex(infoUserInput);
                assertIfLengthIsEqual2(infoUserInput);
                pet.setNomeCompleto(infoUserInput);
                System.out.println("Deseja salvar esta informação?(S/N) " + pet.getNomeCompleto());
                out = input.nextLine();

            } catch (RuntimeException e){
                System.out.println(e.getMessage());
            }

        } while (!out.equalsIgnoreCase("S"));

    }

    @Override
    public String getQuestion() {
        return this.question;
    }

    private void assertIfMatchesRegex(String input) {
        String regex = "^[a-zA-ZáéíóúÁÉÍÓÚçÇ ]*$";
        if (!input.matches(regex)) throw new RuntimeException("Apenas letras são válidas aqui!");
    }

    private void assertIfLengthIsEqual2(String input){
        String[] name = input.split(" ");
        if (!(name.length == 2)) throw new RuntimeException("Por favor digite nome e sobrenome!");
    }
}
