package dev.valente.desafiocadastro.entidade;

public class Pet {

    private String nomeCompleto;
    private Type tipo;
    private Gender genero;
    private Address endereco;
    private float idade;
    private float peso;
    private String raca;

    public String getFirstName() {
        String[] names = nomeCompleto.split(" ");
        return names[0];
    }

    public String getLastName() {
        String[] names = nomeCompleto.split(" ");
        return names[1];
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public Gender getGenero() {
        return genero;
    }

    public void setGenero(int sexoInt) {
        this.genero = Gender.convertToGender(sexoInt);
    }

    public Type getTipo() {
        return tipo;
    }

    public void setTipo(int tipoInt) {
        this.tipo = Type.convertToType(tipoInt);
    }

    public Address getEndereco() {
        return endereco;
    }

    public void setEndereco(Address endereco) {
        this.endereco = endereco;
    }

    public float getIdade() {
        return idade;
    }

    public void setIdade(float idade) {
        this.idade = idade;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "nomeCompleto='" + nomeCompleto + '\'' +
                ", tipo=" + tipo +
                ", genero=" + genero +
                ", endereco=" + endereco +
                ", idade=" + idade +
                ", peso=" + peso +
                ", raca='" + raca + '\'' +
                '}';
    }
}
