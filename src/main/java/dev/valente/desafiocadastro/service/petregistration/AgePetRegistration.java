package dev.valente.desafiocadastro.service.petregistration;

import dev.valente.desafiocadastro.entity.Pet;

import java.util.Scanner;

public record AgePetRegistration(String question) implements PetRegistrationOptions {

    @Override
    public void registerPetInfo(Pet pet, Scanner scanner) {
        float age;
        String infoUserInput;
        String out = "n";
        do{
            try{
                System.out.println(getQuestion());
                System.out.println("Caso a idade seja menor que 1 ano digite 0 seguido pelos meses do seu Pet");
                infoUserInput = scanner.nextLine();
                assertMatchesWithRegex(infoUserInput);
                infoUserInput = infoUserInput.replace(",", ".");
                age = Float.parseFloat(infoUserInput);
                assertAgeLessThan20(age);
                pet.setIdade(age);
                System.out.println("Deseja salvar esta informação?(S/N): " + pet.getIdade());
                out = scanner.nextLine();
            }catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
        } while (!out.equalsIgnoreCase("S"));

    }

    @Override
    public String getQuestion() {
        return this.question;
    }

    private void assertMatchesWithRegex(String input) {
        String regex = "^[0-9,.]*$";
        if(!input.matches(regex)) throw new RuntimeException("Por favor digite apenas numeros aqui!");
    }

    private void assertAgeLessThan20(float age) {
        if(age > 20) throw new RuntimeException("Idade inválida");
    }
}
