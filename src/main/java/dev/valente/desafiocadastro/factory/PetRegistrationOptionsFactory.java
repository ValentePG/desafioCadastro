package dev.valente.desafiocadastro.factory;

import dev.valente.desafiocadastro.service.petregistration.PetRegistrationOptions;
import dev.valente.desafiocadastro.service.petregistration.*;

public class PetRegistrationOptionsFactory {

    public static PetRegistrationOptions createPetRegistrationOptions(int choose, String question) {
        return switch (choose) {
            case 1 -> new CompleteNamePetRegistration(question);
            case 2 -> new TypePetRegistration(question);
            case 3 -> new GenderPetRegistration(question);
            case 4 -> new AddressPetRegistration(question);
            case 5 -> new AgePetRegistration(question);
            case 6 -> new WeightPetRegistration(question);
            case 7 -> new RacePetRegistration(question);
            default -> throw new RuntimeException("Nenhuma opção valida foi escolhida!");
        };
    }
}
