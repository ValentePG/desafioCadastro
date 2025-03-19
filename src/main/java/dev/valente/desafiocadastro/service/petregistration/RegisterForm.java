package dev.valente.desafiocadastro.service.petregistration;

import dev.valente.desafiocadastro.entidade.Pet;
import dev.valente.desafiocadastro.factory.PetRegistrationOptionsFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RegisterForm {

    private final Map<Integer, PetRegistrationOptions> map;
    private int count;

    public RegisterForm() throws IOException {
        this.map = new HashMap<>();
        loadForm();
    }

//    public void showRegisterForm() {
//        getMap().forEach( (key, value) -> {
//            value.getQuestion();
//        });
//    }

    private void loadForm() throws IOException {
        File file = new File("src/main/resources/formulario.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String c;

        while ((c = br.readLine()) != null) {
            if (!c.isEmpty()) {
                count++;
                PetRegistrationOptions answer = PetRegistrationOptionsFactory.createPetRegistrationOptions(count, c);
                getMap().put(getCount(), answer);
            }
        }

        br.close();
    }

//    public void adicionarPergunta(String pergunta) throws IOException {
//        File fw = new File("src/main/resources/formulario.txt");
//        BufferedWriter bw = new BufferedWriter(new FileWriter(fw, true));
//        bw.write("\n" + (count+1) + " - " + pergunta);
//        bw.close();
//    }

    public Pet registerPet(Scanner sc) throws IOException, IllegalAccessException {
        Pet newPet = new Pet();
        getMap().forEach((key, value) -> {
            value.registerPetInfo(newPet, sc);
        });
        registerPetInFile(newPet);
        return newPet;
    }

    private void registerPetInFile(Pet pet) throws IOException, IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        LocalDateTime localDateTime = LocalDateTime.now();
        String now = localDateTime.format(formatter);
        sb.append(now).append("-").append(pet.getFullName().toUpperCase().replace(" ", ""));
        String fileName = "src/main/java/dev/valente/desafiocadastro/petsCadastrados/" + sb + ".txt";
        File newPetFile = new File(fileName);
        var path = Files.createFile(newPetFile.toPath());
        preencherTxt(path, pet);
    }

    private void preencherTxt(Path petFile, Pet pet) throws IOException, IllegalAccessException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(petFile.toFile(), true));
        Field[] fields = pet.getClass().getDeclaredFields();
        int counter = 0;
        for (Field field : fields) {
            counter++;
            field.setAccessible(true);
            var properties = field.get(pet);
            if(properties.getClass().getSimpleName().equalsIgnoreCase("address")){
                Field[] addressFields = properties.getClass().getDeclaredFields();
                StringBuilder addressBuilder = new StringBuilder();
                for (Field addressField : addressFields) {
                    addressField.setAccessible(true);
                    var propertiesAddress = addressField.get(properties);
                    addressBuilder.append(propertiesAddress.toString()).append(", ");
                    addressField.setAccessible(false);
                }
                bw.write(counter + " - " + addressBuilder + "\n");
            } else {
                var propertie = properties.toString();
                bw.write(counter + " - " + propertie + "\n");
            }
            field.setAccessible(false);
        }
        bw.close();
    }

//    public void alterarPet(Scanner sc){
//        String c;
//        int choose;
//        showRegisterForm();
//        System.out.print("Escolha qual informação quer cadastrar: ");
//        choose = sc.nextInt();
//        sc.nextLine();
//        PetRegistrationOptions answer = getMap().get(choose);
//        answer.getQuestion();
//        answer.registerPetInfo(newPet, sc);

//        System.out.println("===================================");
//        System.out.println("Deseja salvar as alterações? (S/N)");
//        c = sc.nextLine();
//        System.out.println("===================================");
//    }

    public void showPet(Pet pet){
        System.out.println("Nome do pet: " + pet.getFullName());
        System.out.println("Idade do pet: " + pet.getAge());
        System.out.println("Endereço do pet: " + pet.getAddress().getStreet()
                + ", " + pet.getAddress().getCity()
                + ", Nº" + pet.getAddress().getNumber());
        System.out.println("Raça do pet: " + pet.getRace());
        System.out.println("Peso do pet: " + pet.getWeight());
        System.out.println("Sexo do pet: " + pet.getGender());
        System.out.println("Type do pet: " + pet.getType());
    }

    public Map<Integer, PetRegistrationOptions> getMap() {
        return map;
    }

    public int getCount() {
        return count;
    }

}