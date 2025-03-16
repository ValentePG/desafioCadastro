package dev.valente.desafiocadastro;

public enum Tipo {

    CACHORRO(1),
    GATO(2);

    Tipo(int tipo) {
    }

    public static Tipo convertToTipo(int tipoInt) {
        return switch (tipoInt) {
            case 1 -> CACHORRO;
            case 2 -> GATO;
            default -> throw new IllegalStateException("Unexpected value");
        };
    }
}
