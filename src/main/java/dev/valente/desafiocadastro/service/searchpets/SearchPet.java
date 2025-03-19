package dev.valente.desafiocadastro.service.searchpets;

import dev.valente.desafiocadastro.entidade.Pet;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchPet {

    private final List<File> filesOfPets;

    public SearchPet() throws IOException {
        this.filesOfPets = new ArrayList<>();
        loadFilesOfPets();
    }

    private void loadFilesOfPets() throws IOException {
        File file = new File("src/main/java/dev/valente/desafiocadastro/petsCadastrados");
        try(var filesInDir = Files.list(file.toPath())){
            filesInDir.forEach(path -> {
                var absolutePath = path.toFile().getAbsolutePath();
                File eachFile = new File(absolutePath);
                filesOfPets.add(eachFile);
            });
        }
    }

    public void searchPet(){
        Scanner scanner = new Scanner(System.in);
        String s;
        System.out.println("Faça uma busca pelo pet de acordo com qualquer uma dessas informações: ");
        Pet pet = new Pet();
        Field[] fields = pet.getClass().getDeclaredFields();
        for (Field field : fields) {
            System.out.println(" - " + field.getName());
        }
        s = scanner.nextLine();
        List<File> filesEncountered = new ArrayList<>();

        filesOfPets.forEach(file -> {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                br.lines().forEach(line -> {
                    if(line.contains(s)){
                        filesEncountered.add(file);
                    };
                });
                br.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

        });

        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (File files : filesEncountered) {
            count++;
            sb.append(count).append(". ");
            try {
                BufferedReader br = new BufferedReader(new FileReader(files));
                br.lines().forEach(line -> {
                    sb.append(line, 4, line.length()).append(" - ");
                });
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
            sb.deleteCharAt(sb.length() - 2);
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
