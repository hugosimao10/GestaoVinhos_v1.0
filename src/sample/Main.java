package sample;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
	// write your code here


        Funcionario funcionario1 = new Funcionario("NovoFunc");

        funcionario1.setRua("Rua NovaIntellIJ");

        try{
            funcionario1.create();
        }
        catch(SQLException ex){
            System.out.println("Erro: " + ex.getMessage());
        }

        Funcionario funcionario2 = new Funcionario();

        try{
            funcionario2.read(1);
        }
        catch(SQLException ex){
            System.out.println("ERRO: " + ex.getMessage());
        }
        System.out.println("Nome: " + funcionario2.getNome());
        System.out.println("Rua: " + funcionario2.getRua());


       /* List<Funcionario> lista = new ArrayList<>();
        try{
            lista = Funcionario.readAll("Ana");
        }catch(SQLException ex){
            System.out.println("ERRO: " + ex.getMessage());
        }

        */
    }

}
