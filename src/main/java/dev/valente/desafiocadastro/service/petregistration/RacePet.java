package dev.valente.desafiocadastro.service.petregistration;

import dev.valente.desafiocadastro.entidade.Pet;

import java.util.Scanner;

public record RacePet(String question) implements PetRegistrationOptions {

    @Override
    public void registerPetInfo(Pet pet, Scanner input) {
        String c = "n";
        do{
            if(c.isEmpty()){
                pet.setRace(NAO_INFORMADO);
                System.out.println("Raça salva como: " + NAO_INFORMADO);
                return;
            }
            try{
                c = input.nextLine();
                assertIfMatchesRegex(c);
                pet.setRace(c);
                System.out.println("Deseja salvar informação?(S/N): " + pet.getRace());
            }catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
        }while (!c.equalsIgnoreCase("S"));
    }

    @Override
    public String getQuestion() {
        return this.question;
    }

    private void assertIfMatchesRegex(String input) {
        String regex = "^[a-zA-ZáéíóúÁÉÍÓÚçÇ ]*$";
        if (!input.matches(regex)) throw new RuntimeException("Apenas letras são válidas aqui!");
    }
}
