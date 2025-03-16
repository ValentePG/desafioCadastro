package dev.valente.desafiocadastro;

public class Pet {

    private String nome;
    private Tipo tipo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(int tipoInt) {
        this.tipo = Tipo.convertToTipo(tipoInt);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "nome='" + nome + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}
