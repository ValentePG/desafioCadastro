package dev.valente.desafiocadastro.service.searchpets;

import dev.valente.desafiocadastro.entidade.Pet;
import dev.valente.desafiocadastro.util.ScannerUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.util.*;

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

    public List<File> getAllPets(){
        return filesOfPets;
    }

    public void showAllPets(List<File> files) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for(File file : files) {
            count++;
            sb.append(count).append(". ");
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                br.lines().forEach(line -> {
                    sb.append(line, 4, line.length()).append(" - ");
                });
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
            sb.deleteCharAt(sb.length() - 2);
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }

    public List<File> searchPets(){
        Scanner scanner = new Scanner(System.in);
        String s;
        int c;
        int count = 0;
        int criteria = 1;
        Map<Integer, String> searchOptions = new HashMap<>();
        Map<Integer, String> searchCriteria = new HashMap<>();
        Pet pet = new Pet();
        Field[] fields = pet.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (!field.getName().equalsIgnoreCase("tipo")) {
                count++;
                searchOptions.put(count, field.getName());
            }
        }

        System.out.println("Seu pet é um cachorro ou gato?");
        s = scanner.nextLine();
        searchCriteria.put(criteria, s);
        do{
            System.out.println("Escolha um critério de busca: ");
            searchOptions.forEach((k, v) -> {
                System.out.println(k + ". " + v);
            });
            try{
                c = scanner.nextInt();
                ScannerUtils.cleanBuffer(scanner);
                System.out.println("Digite a(o) " + searchOptions.get(c) + " do pet que quer buscar");
                s = scanner.nextLine();
                criteria++;
                searchCriteria.put(criteria, s);
                System.out.println("Deseja escolher mais um critério de busca? (S/N)");
                s = scanner.nextLine();
            }catch (InputMismatchException e){
                System.out.println("Digite apenas números");
            }

        } while (s.equalsIgnoreCase("S") || criteria == 3);

        List<File> filesEncountered = new ArrayList<>();

        filesOfPets.forEach(file -> {
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                br.lines().forEach(line -> {
                    searchCriteria.forEach((k, v) -> {
                        if(line.contains(v)){
                            filesEncountered.add(file);
                        }
                    });
                });
                br.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });

        return filesEncountered;
    }
}
