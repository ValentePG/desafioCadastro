package dev.valente.desafiocadastro.service;

import dev.valente.desafiocadastro.repository.PetsRepository;
import dev.valente.desafiocadastro.service.petalteration.PetServiceAlteration;
import dev.valente.desafiocadastro.util.ScannerUtils;

import java.io.*;
import java.nio.file.Files;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PetsService {

    private final PetsRepository petsRepository;

    public PetsService(PetsRepository petsRepository) {
        this.petsRepository = petsRepository;
    }

    public void showAllPets(){
        var files = getAllPets();
        if(files.isEmpty()){
            throw new RuntimeException("Não a pets registrados");
        }
        showPetsFromFile(files);
    }

    public void showPetsFromFile(List<File> files){
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (File file : files) {
            count++;
            sb.append(count).append(". ");
            formatOutPut(file, sb);
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }

    public void showPetFromFile(File file){
        StringBuilder sb = new StringBuilder();
        formatOutPut(file, sb);
        System.out.println(sb);
    }

    private void formatOutPut(File file, StringBuilder sb){
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            br.lines().forEach(line -> {
                sb.append(line, 4, line.length()).append(" - ");
            });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        sb.deleteCharAt(sb.length() - 2);
    }

    public List<File> getAllPets(){
        return petsRepository.getFilesOfRegisteredPets();
    }

    public void deletePet(File fileToDelete) throws IOException {
        var allPets = getAllPets();
        Scanner scanner = new Scanner(System.in);
        String inputUserString = "n";
        do {
            System.out.println("Tem certeza que deseja excluir este pet (S/N): ");
            inputUserString = scanner.nextLine();
            if (inputUserString.equalsIgnoreCase("s")) {
                allPets.remove(fileToDelete);
                Files.deleteIfExists(fileToDelete.toPath());
                System.out.println("Deletado com sucesso!");
            }
        } while (!inputUserString.equalsIgnoreCase("s"));
    }

    public List<File> searchPets(){
        SearchPetService searchPetService = new SearchPetService(petsRepository);
        return searchPetService.searchPets();
    }

    public File alteratePet(File fileToAlterate){
        return PetServiceAlteration.changeInfoFromPet(fileToAlterate);
    }

    public File selectPet(){
        var allPets = getAllPets();
        File fileToReturn = null;
        Scanner scanner = new Scanner(System.in);
        int inputUserInt = 0;
        while (fileToReturn == null){
            try{
                System.out.println("Escolha um pet: ");
                inputUserInt = scanner.nextInt();
                ScannerUtils.cleanBuffer(scanner);
                inputUserInt -= 1;
                assertIfInIsGreaterThanSizeOfList(inputUserInt, allPets);
                fileToReturn = allPets.get(inputUserInt);
            } catch (InputMismatchException ex){
                ScannerUtils.cleanBuffer(scanner);
                System.out.println(ex.getMessage());
            }
        }
        return fileToReturn;
    }

    private void assertIfInIsGreaterThanSizeOfList(int input, List<File> fileToCheck) {
        if (input > fileToCheck.size()) throw new RuntimeException("Digite um valor dos que foram mostrados na tela");
    }

}
