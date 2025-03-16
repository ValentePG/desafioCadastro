package dev.valente.desafiocadastro;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

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

    public Map<Integer, String> getMap() {
        return map;
    }

    public int getCount() {
        return count;
    }

}