package dev.valente.desafiocadastro.service;

import dev.valente.desafiocadastro.repository.PetsRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class PetsService {

    private final PetsRepository petsRepository;

    public PetsService(PetsRepository petsRepository) {
        this.petsRepository = petsRepository;
    }

    public void removePet(){

    }

    public void showAllPets(){
        var files = getAllPets();
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

    public void showAllPets(List<File> files){
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

    public List<File> getAllPets(){
        return petsRepository.getFilesOfRegisteredPets();
    }
}
