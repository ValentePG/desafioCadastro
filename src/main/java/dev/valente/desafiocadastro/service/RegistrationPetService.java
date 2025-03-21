package dev.valente.desafiocadastro.service;

import dev.valente.desafiocadastro.entity.Pet;
import dev.valente.desafiocadastro.repository.PetRegistrationOptionsRepository;
import dev.valente.desafiocadastro.service.petregistration.PetRegistrationOptions;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class RegistrationPetService {

    private final PetRegistrationOptionsRepository petRegistrationOptionsRepository;

    public RegistrationPetService(PetRegistrationOptionsRepository petRegistrationOptionsRepository) {
        this.petRegistrationOptionsRepository = petRegistrationOptionsRepository;
    }

    public Pet registerPet(Scanner sc) throws IOException, IllegalAccessException {
        Pet newPet = new Pet();
        getPetRegistrationOptions().forEach((key, value) -> {
            value.registerPetInfo(newPet, sc);
        });
        formatNameFile(newPet);
        return newPet;
    }

    public void showPet(Pet pet) throws IllegalAccessException {
        Field[] fields = pet.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            var fieldName = field.getName();
            var capitalizedFieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            var sb = formatFields(field, pet);
            System.out.println(capitalizedFieldName + " do pet: " + sb);
            field.setAccessible(false);
        }
    }

    private void formatNameFile(Pet pet) throws IOException, IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        LocalDateTime localDateTime = LocalDateTime.now();
        String now = localDateTime.format(formatter);
        sb.append(now).append("-").append(pet.getNomeCompleto().toUpperCase().replace(" ", ""));
        String fileName = "src/main/java/dev/valente/desafiocadastro/petsCadastrados/" + sb + ".txt";
        File newPetFile = new File(fileName);
        var path = Files.createFile(newPetFile.toPath());
        registerPetInFiles(path, pet);
    }

    private void registerPetInFiles(Path petFile, Pet pet) throws IOException, IllegalAccessException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(petFile.toFile(), true));
        Field[] fields = pet.getClass().getDeclaredFields();
        int counter = 0;
        for (Field field : fields) {
            counter++;
            var sb = formatFields(field, pet);
            bw.write(counter + " - " + sb + "\n");
        }
        bw.close();
    }

    private StringBuilder formatInnerFields(Object field) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        Field[] innerFields = field.getClass().getDeclaredFields();
        for (Field innerField : innerFields) {
            innerField.setAccessible(true);
            var innerFieldObject = innerField.get(field);
            sb.append(innerFieldObject.toString()).append(", ");
            innerField.setAccessible(false);
        }
        sb.delete(sb.length() - 2, sb.length());
        return sb;
    }

    private StringBuilder formatFields(Field field, Pet pet) throws IllegalAccessException {
        StringBuilder sb;
        field.setAccessible(true);
        var fieldObject = field.get(pet);
        var fieldName = field.getName();
        var objectSimpleName = fieldObject.getClass().getSimpleName();
        if(objectSimpleName.equalsIgnoreCase("address")) {
            sb = formatInnerFields(fieldObject);
        } else {
            sb = new StringBuilder();
            if(fieldName.equalsIgnoreCase("idade")){
                sb.append(fieldObject).append(" anos");
            } else if(fieldName.equalsIgnoreCase("peso")){
                sb.append(fieldObject).append("kg");
            } else {
                sb.append(fieldObject);
            }
        }
        field.setAccessible(false);
        return sb;
    }

    public Map<Integer, PetRegistrationOptions> getPetRegistrationOptions() {
        return petRegistrationOptionsRepository.getPetRegistrationOptions();
    }

}