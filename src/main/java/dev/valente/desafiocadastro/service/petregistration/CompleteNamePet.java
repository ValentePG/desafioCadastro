package dev.valente.desafiocadastro.service.petregistration;

import dev.valente.desafiocadastro.entidade.Pet;

import static dev.valente.desafiocadastro.util.Constants.NAO_INFORMADO;

import java.util.Scanner;

public record CompleteNamePet(String question) implements PetRegistrationOptions {

    @Override
    public void registerPetInfo(Pet pet, Scanner input) {

        String c;
        do{
            c = input.nextLine();
            if(c.isEmpty()){
                pet.setFirstName(NAO_INFORMADO);
                pet.setLastName(NAO_INFORMADO);
                System.out.println("Nome salvo como: " + NAO_INFORMADO);
                return;
            }

            try {
                assertIfMatchesRegex(c);

                String[] name = c.split(" ");

                assertIfLengthIsEqual2(name);

                pet.setFirstName(name[0]);
                pet.setLastName(name[1]);
                System.out.println("Deseja salvar esta informação?(S/N) " + pet.getFullName());
                c = input.nextLine();

            } catch (RuntimeException e){
                System.out.println(e.getMessage());
            }

        } while (!c.equalsIgnoreCase("S"));

    }

    @Override
    public String getQuestion() {
        return this.question;
    }

    private void assertIfMatchesRegex(String input) {
        String regex = "^[a-zA-ZáéíóúÁÉÍÓÚçÇ ]*$";
        if (!input.matches(regex)) throw new RuntimeException("Apenas letras são válidas aqui!");
    }

    private void assertIfLengthIsEqual2(String[] names){
        if (!(names.length == 2)) throw new RuntimeException("Por favor digite nome e sobrenome!");
    }
}
