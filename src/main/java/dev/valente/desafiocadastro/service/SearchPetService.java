package dev.valente.desafiocadastro.service;

import dev.valente.desafiocadastro.entity.Pet;
import dev.valente.desafiocadastro.repository.PetsRepository;
import dev.valente.desafiocadastro.util.ScannerUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public class SearchPetService {

    private final PetsRepository petsRepository;

    private final Map<Integer, String> criteriaOptions;

    public SearchPetService(PetsRepository petsRepository){
        this.petsRepository = petsRepository;
        this.criteriaOptions = new HashMap<>();
        loadCriteriaOptions();
    }

    public Map<Integer, String> getCriteriaOptions() {
        return criteriaOptions;
    }

    public List<File> getAllPets() {
        return petsRepository.getFilesOfPets();
    }

    public void showAllPets(List<File> files) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (File file : files) {
            count++;
            sb.append(count).append(". ");
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                br.lines().forEach(line -> {
                    sb.append(line, 4, line.length()).append(" - ");
                });
                br.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            sb.deleteCharAt(sb.length() - 2);
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }

    public List<File> searchPets() {
        var searchCriteria = getCriteriaFromUser();

        var filesOfPets = petsRepository.getFilesOfPets();

        return searchPetsByCriteria(filesOfPets, searchCriteria);
    }

    private Map<Integer, String> getCriteriaFromUser(){
        Scanner scanner = new Scanner(System.in);
        String inputUserString;
        int InputUserInt;
        int numberOfCriterias = 1;

        Map<Integer, String> searchCriteria = new HashMap<>();

        System.out.println("Seu pet é um cachorro ou gato?");
        inputUserString = scanner.nextLine();
        searchCriteria.put(numberOfCriterias, inputUserString);
        do {
            System.out.println("Escolha um critério de busca: ");
            showAvailableCriterias(criteriaOptions);
            try {
                InputUserInt = scanner.nextInt();
                ScannerUtils.cleanBuffer(scanner);
                System.out.println("Digite a(o) " + criteriaOptions.get(InputUserInt) + " do pet que quer buscar");
                inputUserString = scanner.nextLine().toLowerCase();
                numberOfCriterias++;
                searchCriteria.put(numberOfCriterias, inputUserString);
                System.out.println("Deseja escolher mais um critério de busca? (S/N)");
                inputUserString = scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Digite apenas números");
            }

        } while (inputUserString.equalsIgnoreCase("S") || numberOfCriterias == 3);

        return searchCriteria;
    }

    private List<File> searchPetsByCriteria(List<File> filesOfPets, Map<Integer, String> criterias) {
        List<File> filesEncountered = new ArrayList<>();
        for(File file : filesOfPets) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                br.lines().forEach(line -> {
                    criterias.forEach((k, v) -> {
                        if (line.toLowerCase().contains(v)) {
                            filesEncountered.add(file);
                        }
                    });

                });
                br.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return filesEncountered;
    }

    private void loadCriteriaOptions() {
        int count = 0;
        Pet pet = new Pet();
        Field[] fields = pet.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (!field.getName().equalsIgnoreCase("tipo")) {
                count++;
                getCriteriaOptions().put(count, field.getName());
            }
        }
    }

    private void showAvailableCriterias(Map<Integer, String> criterias){
        criterias.forEach((key, criteria) -> {
            System.out.println(key + ". " + criteria);
        });
    }

}
