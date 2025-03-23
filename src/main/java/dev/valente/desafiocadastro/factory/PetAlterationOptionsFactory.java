package dev.valente.desafiocadastro.factory;

import dev.valente.desafiocadastro.service.petalteration.*;

public class PetAlterationOptionsFactory {

    public static IAlterationPetOptions createAlterarInfoIT(int choose) {
        return switch (choose) {
            case 1 -> new CompleteNameAlteration();
            case 4 -> new AddressPetAlteration();
            case 5 -> new AgePetAlteration();
            case 6 -> new WeightPetAlteration();
            case 7 -> new RacePetAlteration();
            default -> throw new IllegalArgumentException("Invalid choose");
        };
    }
}
