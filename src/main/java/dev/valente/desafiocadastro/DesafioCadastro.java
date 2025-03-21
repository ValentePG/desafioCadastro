package dev.valente.desafiocadastro;

import dev.valente.desafiocadastro.entity.Pet;
import dev.valente.desafiocadastro.repository.PetRegistrationOptionsRepository;
import dev.valente.desafiocadastro.repository.PetsRepository;
import dev.valente.desafiocadastro.service.*;
import dev.valente.desafiocadastro.service.petalteration.AlterationPetService;
import dev.valente.desafiocadastro.util.ScannerUtils;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DesafioCadastro {
    public static void main(String[] args) throws IOException, IllegalAccessException {
        Scanner sc = new Scanner(System.in);
        int input = 0;
        do{
            MenuService.showMenu();
            System.out.print("Escolha uma opção: ");
            try{
                input = sc.nextInt();
                ScannerUtils.cleanBuffer(sc);
                if(!(input >= 0)){
                    throw new InputMismatchException();
                }
                System.out.println("======================================");
                if (input == 1) {
                    PetRegistrationOptionsRepository petRegistrationOptionsRepository =
                            new PetRegistrationOptionsRepository();
                    RegistrationPetService registrationPetService =
                            new RegistrationPetService(petRegistrationOptionsRepository);
                    Pet novoPet = registrationPetService.registerPet(sc);
                    registrationPetService.showPet(novoPet);
                } else if(input == 2) {
                    PetsRepository petsRepository = new PetsRepository();
                    SearchPetService searchPetService = new SearchPetService(petsRepository);
                    var pets = searchPetService.getAllPets();
                    searchPetService.showAllPets(pets);
                    AlterationPetService alterationPetService =
                            new AlterationPetService();
                    alterationPetService.changePet(pets);
                } else if(input == 3) {
                    PetsRepository petsRepository = new PetsRepository();
                    SearchPetService searchPetService = new SearchPetService(petsRepository);
                    var pets = searchPetService.getAllPets();
                    searchPetService.showAllPets(pets);
                    DeletePetService deletePetService = new DeletePetService();
                    deletePetService.deletePet(pets);
                } else if(input == 4) {
                    PetsRepository petsRepository = new PetsRepository();
                    SearchPetService petSearch = new SearchPetService(petsRepository);
                    var allPets = petSearch.getAllPets();
                    petSearch.showAllPets(allPets);
                } else if(input == 5) {
                    PetsRepository petsRepository = new PetsRepository();
                    SearchPetService petSearch = new SearchPetService(petsRepository);
                    var petsEncountered = petSearch.searchPets();
                    petSearch.showAllPets(petsEncountered);
                }
            }catch (InputMismatchException ex){
                ScannerUtils.cleanBuffer(sc);
                System.out.println("Por favor insira um número válido!");
            }
        } while (input != 6);

    }
}