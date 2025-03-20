package dev.valente.desafiocadastro.service.petregistration;

import dev.valente.desafiocadastro.entidade.Pet;
import dev.valente.desafiocadastro.factory.PetRegistrationOptionsFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

        String lines;

        while ((lines = br.readLine()) != null) {
            if (!lines.isEmpty()) {
                count++;
                PetRegistrationOptions options = PetRegistrationOptionsFactory
                        .createPetRegistrationOptions(count, lines);
                getMap().put(getCount(), options);
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
        sb.append(now).append("-").append(pet.getNomeCompleto().toUpperCase().replace(" ", ""));
        String fileName = "src/main/java/dev/valente/desafiocadastro/petsCadastrados/" + sb + ".txt";
        File newPetFile = new File(fileName);
        var path = Files.createFile(newPetFile.toPath());
        fillTxtWithProperties(path, pet);
    }

    private void fillTxtWithProperties(Path petFile, Pet pet) throws IOException, IllegalAccessException {
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
                addressBuilder.deleteCharAt(addressBuilder.length() - 2);
                bw.write(counter + " - " + addressBuilder + "\n");
            } else {
                var propertie = properties.toString();
                bw.write(counter + " - " + propertie + "\n");
            }
            field.setAccessible(false);
        }
        bw.close();
    }

    public void showPet(Pet pet) throws IllegalAccessException {
        Field[] fields = pet.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            var fieldName = field.getName();
            var fieldObject = field.get(pet);
            var capitalizedFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            if(fieldObject.getClass().getSimpleName().equalsIgnoreCase("address")) {
                Field[] addressFields = fieldObject.getClass().getDeclaredFields();
                StringBuilder addressBuilder = new StringBuilder();
                for (Field addressField : addressFields) {
                    addressField.setAccessible(true);
                    var propertiesAddress = addressField.get(fieldObject);
                    addressBuilder.append(propertiesAddress.toString()).append(", ");
                    addressField.setAccessible(false);
                }
                addressBuilder.deleteCharAt(addressBuilder.length() - 2);
                System.out.println(capitalizedFieldName + " do pet: " + addressBuilder);
            }
            System.out.println(capitalizedFieldName + " do pet: " + fieldObject);
        }
    }

    public Map<Integer, PetRegistrationOptions> getMap() {
        return map;
    }

    public int getCount() {
        return count;
    }

}