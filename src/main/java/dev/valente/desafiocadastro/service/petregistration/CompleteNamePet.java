package dev.valente.desafiocadastro.service.petregistration;

import dev.valente.desafiocadastro.entidade.Pet;

import static dev.valente.desafiocadastro.util.Constant.NAO_INFORMADO;

import java.util.Scanner;

public record CompleteNamePet(String question) implements PetRegistrationOptions {

    @Override
    public void registerPetInfo(Pet pet, Scanner input) {

        String c;
        c = input.nextLine();
        if(c.isEmpty()){
            pet.setFirstName(NAO_INFORMADO);
            pet.setLastName(NAO_INFORMADO);
            System.out.println(NAO_INFORMADO);
            return;
        }

        assertIfMatchesRegex(c);

        String[] name = c.split(" ");
        assertIfLengthIsEqual2(name);
        pet.setFirstName(name[0]);
        pet.setLastName(name[1]);

        System.out.println("Informação salva: " + pet.getFullName());
    }

    @Override
    public String getQuestion() {
        return this.question;
    }

    private void assertIfMatchesRegex(String input) {
        String regex = "^[a-zA-ZáéíóúÁÉÍÓÚçÇ ]*$";
        if (!input.matches(regex)) throw new RuntimeException("Não digite caracteres especiais, apenas letras");
    }

    private void assertIfLengthIsEqual2(String[] names){
        if (!(names.length == 2)) throw new RuntimeException("Por favor digite nome e sobrenome!");
    }
}
