package dev.valente.desafiocadastro.entidade;

public class Pet {

    private String fullName;
    private Type type;
    private Gender gender;
    private Address address;
    private float age;
    private float weight;
    private String race;

    public String getFirstName() {
        String[] names = fullName.split(" ");
        return names[0];
    }

    public String getLastName() {
        String[] names = fullName.split(" ");
        return names[1];
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(int sexoInt) {
        this.gender = Gender.convertToGender(sexoInt);
    }

    public Type getType() {
        return type;
    }

    public void setType(int tipoInt) {
        this.type = Type.convertToType(tipoInt);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "fullName='" + fullName + '\'' +
                ", type=" + type +
                ", gender=" + gender +
                ", address=" + address +
                ", age=" + age +
                ", weight=" + weight +
                ", race='" + race + '\'' +
                '}';
    }
}
