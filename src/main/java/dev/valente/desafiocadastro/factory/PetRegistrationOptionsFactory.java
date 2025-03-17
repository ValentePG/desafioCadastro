package dev.valente.desafiocadastro.factory;

import dev.valente.desafiocadastro.service.petregistration.PetRegistrationOptions;
import dev.valente.desafiocadastro.service.petregistration.*;

public class PetRegistrationOptionsFactory {

    public static PetRegistrationOptions createPetRegistrationOptions(int choose, String question) {
        return switch (choose) {
            case 1 -> new CompleteNamePet(question);
            case 2 -> new TypePet(question);
            case 3 -> new GenderPet(question);
            case 4 -> new AddressPet(question);
            case 5 -> new AgePet(question);
            case 6 -> new WeightPet(question);
            case 7 -> new RacePet(question);
            default -> throw new RuntimeException("Nenhuma opção valida foi escolhida!");
        };
    }
}
