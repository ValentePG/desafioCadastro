package dev.valente.desafiocadastro.service.petregistration;

import dev.valente.desafiocadastro.entidade.Pet;

import java.util.Scanner;

public record AgePet(String question) implements PetRegistrationOptions {

    @Override
    public void registerPetInfo(Pet pet, Scanner input) {
        float age;
        String infoUserInput;
        String out = "n";
        do{
            try{
                System.out.println(getQuestion());
                System.out.println("Caso a idade seja menor que 1 ano digite 0 seguido pelos meses do seu Pet");
                infoUserInput = input.nextLine();
                assertMatchesWithRegex(infoUserInput);
                infoUserInput = infoUserInput.replace(",", ".");
                age = Float.parseFloat(infoUserInput);
                assertAgeLessThan20(age);
                pet.setAge(age);
                System.out.println("Deseja salvar esta informação?(S/N): " + pet.getAge());
                out = input.nextLine();
            }catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
        } while (!out.equalsIgnoreCase("S"));

    }

    @Override
    public String getQuestion() {
        return this.question;
    }
}
