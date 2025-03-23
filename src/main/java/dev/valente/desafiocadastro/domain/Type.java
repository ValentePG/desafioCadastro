package dev.valente.desafiocadastro.domain;

public enum Type {

    CACHORRO(1),
    GATO(2);

    Type(int type) {
    }

    public static Type convertToType(int typeInt) {
        return switch (typeInt) {
            case 1 -> CACHORRO;
            case 2 -> GATO;
            default -> throw new IllegalStateException("Por favor digite apenas 1 ou 2");
        };
    }
}
