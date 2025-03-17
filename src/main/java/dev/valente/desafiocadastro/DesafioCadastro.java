package dev.valente.desafiocadastro;

import dev.valente.desafiocadastro.entidade.Pet;
import dev.valente.desafiocadastro.service.menu.Menu;
import dev.valente.desafiocadastro.service.petregistration.RegisterForm;
import dev.valente.desafiocadastro.util.ScannerUtils;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DesafioCadastro {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int input = 0;
        do{
            Menu.showMenu();
            System.out.print("Escolha uma opção: ");
            try{
                input = sc.nextInt();
                ScannerUtils.cleanBuffer(sc);
                if(!(input >= 0)){
                    throw new InputMismatchException();
                }
                System.out.println("======================================");
                if (input == 1) {
                    RegisterForm registerForm = new RegisterForm();
                    Pet novoPet = registerForm.registerPet(sc);
                    registerForm.showPet(novoPet);
                }
            }catch (InputMismatchException ex){
                ScannerUtils.cleanBuffer(sc);
                System.out.println("Por favor insira um número válido!");
            }
        } while (input != 6);
    }
}