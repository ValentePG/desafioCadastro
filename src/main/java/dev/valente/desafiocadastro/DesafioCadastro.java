package dev.valente.desafiocadastro;

import java.io.IOException;
import java.util.Scanner;

public class DesafioCadastro {
    public static void main(String[] args) throws IOException {

        int input = 0;
        while (input != 6){

            Menu menu = new Menu();
            System.out.print("Escolha sua opção: ");
            Scanner sc = new Scanner(System.in);
            input = sc.nextInt();
            System.out.println("======================================");
            if (input == 1) {
                RegisterForm registerForm = new RegisterForm();
                System.out.print("Escolha qual informação quer cadastrar primeiro: ");
                input = sc.nextInt();
                registerForm.cadastrarPet(input);
                System.out.println("===================================");
            }
        }
    }
}