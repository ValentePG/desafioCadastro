package dev.valente.desafiocadastro.entidade;

public enum Gender {

    MASCULINO(1),
    FEMININO(2);

    Gender(int genderInt) {
    }

    public static Gender convertToGender(int genderInt) {
        return switch (genderInt) {
            case 1 -> MASCULINO;
            case 2 -> FEMININO;
            default -> throw new IllegalStateException("Unexpected value");
        };
    }
}
