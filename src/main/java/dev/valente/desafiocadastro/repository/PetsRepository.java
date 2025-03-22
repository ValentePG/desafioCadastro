package dev.valente.desafiocadastro.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class PetsRepository {

    private final List<File> filesOfRegisteredPets;

    public PetsRepository() throws IOException {
        this.filesOfRegisteredPets = new ArrayList<>();
        loadFilesOfPets();
    }

    private void loadFilesOfPets() throws IOException {
        File file = new File("src/main/java/dev/valente/desafiocadastro/petsCadastrados");
        try(var filesInDir = Files.list(file.toPath())){
            filesInDir.forEach(path -> {
                var absolutePath = path.toFile().getAbsolutePath();
                File eachFile = new File(absolutePath);
                filesOfRegisteredPets.add(eachFile);
            });
        }
    }

    public List<File> getFilesOfRegisteredPets() {
        return filesOfRegisteredPets;
    }
}
