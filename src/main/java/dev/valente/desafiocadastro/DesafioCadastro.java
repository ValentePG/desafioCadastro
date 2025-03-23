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

    public final static int NUMBER_OF_OPTIONS = 6;

    public static void main(String[] args) throws IOException, IllegalAccessException {
        Scanner sc = new Scanner(System.in);
        int input = 0;
        do{
            MenuService.showMenu();
            try{
                System.out.print("Escolha uma opção: ");
                input = sc.nextInt();
                ScannerUtils.cleanBuffer(sc);
                if(input <= 0 || input > NUMBER_OF_OPTIONS){
                    throw new RuntimeException();
                }
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
                        var pets = petsService.getAllPets();
                        petsService.showAllPets();
                        AlterationPetService alterationPetService =
                                new AlterationPetService();
                        alterationPetService.changePet(pets);
                    }
                    case 3 -> {
                        PetsRepository petsRepository = new PetsRepository();
                        PetsService petsService = new PetsService(petsRepository);
                        petsService.showAllPets();
                        var pet = petsService.searchPet();
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
                        petsService.showAllPets(petsEncountered);
                    }
                }
            }catch (InputMismatchException ex){
                ScannerUtils.cleanBuffer(sc);
                System.out.println("Por favor digite apenas números de 1 a 6");
            } catch (RuntimeException ex){
                System.out.println("Por favor insira um número válido de 1 a 6");
            }
        } while (input != 6);

    }
}