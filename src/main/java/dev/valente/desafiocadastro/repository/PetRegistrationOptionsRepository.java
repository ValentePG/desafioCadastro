package dev.valente.desafiocadastro.repository;

import dev.valente.desafiocadastro.factory.PetRegistrationOptionsFactory;
import dev.valente.desafiocadastro.service.petregistration.PetRegistrationOptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PetRegistrationOptionsRepository {

    private final Map<Integer, PetRegistrationOptions> petRegistrationOptions;

    public PetRegistrationOptionsRepository() throws IOException {
        this.petRegistrationOptions = new HashMap<>();
        loadForm();
    }

    public Map<Integer, PetRegistrationOptions> getPetRegistrationOptions() {
        return petRegistrationOptions;
    }

    private void loadForm() throws IOException {
        File file = new File("src/main/resources/formulario.txt");
        int count = 0;
        BufferedReader br = new BufferedReader(new FileReader(file));

        String lines;

        while ((lines = br.readLine()) != null) {
            if (!lines.isEmpty()) {
                count++;
                PetRegistrationOptions options = PetRegistrationOptionsFactory
                        .createPetRegistrationOptions(count, lines);
                getPetRegistrationOptions().put(count, options);
            }
        }

        br.close();
    }
}
