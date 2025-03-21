package dev.valente.desafiocadastro.service.petalteration;

import java.util.Scanner;

public class AlterarAge implements AlterarInfoIT{

    @Override
    public String alterarInfoInFile() {
        Scanner input = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        float age;
        String infoUserInput;
        String out = "n";
        do{
            try{
                System.out.println("Altere a sua idade");
                System.out.println("Caso a idade seja menor que 1 ano digite 0 seguido pelos meses do seu Pet");
                infoUserInput = input.nextLine();
                assertMatchesWithRegex(infoUserInput);
                infoUserInput = infoUserInput.replace(",", ".");
                age = Float.parseFloat(infoUserInput);
                assertAgeLessThan20(age);
                sb.append(age).append(" anos");
                System.out.println("Deseja salvar esta informação?(S/N): " + age);
                out = input.nextLine();
            }catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
        } while (!out.equalsIgnoreCase("S"));
        return sb.toString().trim();
    }

    private void assertMatchesWithRegex(String input) {
        String regex = "^[0-9,.]*$";
        if(!input.matches(regex)) throw new RuntimeException("Por favor digite apenas numeros aqui!");
    }

    private void assertAgeLessThan20(float age) {
        if(age > 20) throw new RuntimeException("Idade inválida");
    }
}
