package dev.valente.desafiocadastro;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RegisterForm {

    private final Map<Integer, String> map;
    private int count;

    public RegisterForm() throws IOException {
        this.map = new HashMap<>();
        showRegisterForm();
    }

    public void showRegisterForm() throws IOException {
        File file = new File("src/main/resources/formulario.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));

        String c;

        while ((c = br.readLine()) != null) {
            if (!c.isEmpty()) {
                count++;
                getMap().put(getCount(), c);
            }
        }
        getMap().forEach( (key, value) -> {
            System.out.println(value);
        });
        br.close();
    }

//    public void adicionarPergunta(String pergunta) throws IOException {
//        File fw = new File("src/main/resources/formulario.txt");
//        BufferedWriter bw = new BufferedWriter(new FileWriter(fw, true));
//        bw.write("\n" + (count+1) + " - " + pergunta);
//        bw.close();
//    }

    public void cadastrarPet(int choose){
        Scanner sc = new Scanner(System.in);
        Pet newPet = new Pet();
        String c;
        int n;
        if(choose == 1){
            System.out.println(map.get(choose));
            c = sc.nextLine();
            newPet.setNome(c);
        }
        if(choose == 2){
            System.out.println("Digite 1 para cachorro ou 2 para gato: ");
            n = sc.nextInt();
            newPet.setTipo(n);
        }
        System.out.println(newPet);
    }

    public Map<Integer, String> getMap() {
        return map;
    }

    public int getCount() {
        return count;
    }

}