package dev.valente.desafiocadastro;

import dev.valente.desafiocadastro.domain.Pet;
import dev.valente.desafiocadastro.repository.PetRegistrationOptionsRepository;
import dev.valente.desafiocadastro.repository.PetsRepository;
import dev.valente.desafiocadastro.service.*;
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
            try{
                System.out.print("Escolha uma opção: ");
                input = sc.nextInt();
                ScannerUtils.cleanBuffer(sc);
                System.out.println("======================================");
                switch (input){
                    case 1 -> {
                        PetRegistrationOptionsRepository registrationRepository =
                                new PetRegistrationOptionsRepository();
                        RegistrationPetService registrationPetService =
                                new RegistrationPetService(registrationRepository);
                        Pet novoPet = registrationPetService.registerPet(sc);
                        registrationPetService.showPet(novoPet);
                    }
                    case 2 -> {
                        PetsRepository petsRepository = new PetsRepository();
                        PetsService petsService = new PetsService(petsRepository);
                        petsService.showAllPets();
                        var pet = petsService.selectPet();
                        var fileChanged = petsService.alteratePet(pet);
                        petsService.showPetFromFile(fileChanged);
                    }
                    case 3 -> {
                        PetsRepository petsRepository = new PetsRepository();
                        PetsService petsService = new PetsService(petsRepository);
                        petsService.showAllPets();
                        var pet = petsService.selectPet();
                        petsService.deletePet(pet);
                    }
                    case 4 -> {
                        PetsRepository petsRepository = new PetsRepository();
                        PetsService petsService = new PetsService(petsRepository);
                        petsService.showAllPets();
                    }
                    case 5 -> {
                        PetsRepository petsRepository = new PetsRepository();
                        PetsService petsService = new PetsService(petsRepository);
                        var petsEncountered = petsService.searchPets();
                        petsService.showPetsFromFile(petsEncountered);
                    }
                    default -> System.out.println("Escolha uma opção entre 1 e 6 por favor!");
                }
            }catch (InputMismatchException ex){
                ScannerUtils.cleanBuffer(sc);
                System.out.println("Por favor digite apenas números de 1 a 6");
            } catch (RuntimeException ex){
                System.out.println(ex.getMessage());
            }
        } while (input != 6);

    }
}