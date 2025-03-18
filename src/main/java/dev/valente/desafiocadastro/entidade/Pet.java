package dev.valente.desafiocadastro.entidade;

public class Pet {

    private String firstName;
    private String lastName;
    private Type type;
    private Gender gender;
    private Address address;
    private float age;
    private float weight;
    private String race;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        if(firstName.equals("NÃO INFORMADO")){
            return "NÃO INFORMADO";
        }
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", type=" + type +
                ", gender=" + gender +
                ", address=" + address +
                ", age=" + age +
                ", weight=" + weight +
                ", race='" + race + '\'' +
                '}';
    }
}
