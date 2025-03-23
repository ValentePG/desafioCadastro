package dev.valente.desafiocadastro.domain;

public enum Gender {

    MASCULINO(1),
    FEMININO(2);

    Gender(int genderInt) {
    }

    public static Gender convertToGender(int genderInt) {
        return switch (genderInt) {
            case 1 -> MASCULINO;
            case 2 -> FEMININO;
            default -> throw new IllegalStateException("Por favor digite 1 para masculino ou 2 para feminino");
        };
    }
}
