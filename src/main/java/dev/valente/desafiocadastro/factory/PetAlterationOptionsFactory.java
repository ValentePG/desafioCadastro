package dev.valente.desafiocadastro.factory;

import dev.valente.desafiocadastro.service.petalteration.*;

public class PetAlterationOptionsFactory {

    public static AlterarInfoIT createAlterarInfoIT(int choose) {
        return switch (choose) {
            case 1 -> new AlterarCompleteName();
            case 4 -> new AlterarAddress();
            case 5 -> new AlterarAge();
            case 6 -> new AlterarWeight();
            case 7 -> new AlterarRace();
            default -> throw new IllegalArgumentException("Invalid choose");
        };
    }
}
