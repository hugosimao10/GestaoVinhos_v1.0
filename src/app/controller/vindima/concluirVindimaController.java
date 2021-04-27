package app.controller.vindima;

import app.entities.userID;
import app.error.msg;
import app.util.Util;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class concluirVindimaController {
    public Pane concluirVindimaPane;
    public TextField numVindima;
    public Button btnConfirmConcluirVindima;
    public Button btnCancelConcluirVindima;
    public CheckBox checkConcluirVindima;
    public TextField qtdVindimada;

    public void iniciar() throws SQLException {
        System.out.println("Está na area de concluir Vindimas!");


    }

    public void btnConcluirVindimaClic(ActionEvent actionEvent) throws SQLException {

        String numVind =  numVindima.getText();
        int numVindInt = Integer.parseInt(numVind);
        String QtdVindimada =  qtdVindimada.getText();
        int qtdVinInt = Integer.parseInt(QtdVindimada);


        // VER SE O CAMPO ESTA VAZIO

        if(numVind.isEmpty() || QtdVindimada.isEmpty()){
            System.out.println("Não pode haver campos vazios!");
            msg.alertaAviso("Não pode haver campos vazios!", "Aviso!", "Campo não pode ficar vazio!");
        }
        else{

            // VER SE A CHECKBOX ESTÁ SELECIONADA

            if(checkConcluirVindima.isSelected()){

                int a = userID.getId();

                Connection conn = Util.criarConexao();

                PreparedStatement pst = conn.prepareStatement("SELECT p.*, f.* FROM PLANTACAO_VINDIMA p, FUNCIONARIO f WHERE p.ID_PLANT_VINDIMA = ? AND f.ID_FUNCIONARIO = p.ID_FUNCIONARIO AND f.ID_EMPRESA = ?");

                pst.setInt(1, numVindInt);
                pst.setInt(2, a);

                ResultSet rs = pst.executeQuery();

                // VER SE FOI ENCONTRADO O ID, E SE FOI ENCONTRADO, ENTAO PASSA O CAMPO ATIVO A 0 (QUINTA NÃO FAZ MAIS PARTE DA EMPRESA)

                if(rs.next()){

                    PreparedStatement pst1 = conn.prepareStatement("UPDATE PLANTACAO_VINDIMA SET QTD_VINDIMADA = ? WHERE ID_PLANT_VINDIMA = ?");
                    pst1.setInt(1,qtdVinInt);
                    pst1.setInt(2,numVindInt);
                    pst1.executeQuery();
                    System.out.println("A vindima foi finalizada com sucesso!");
                    msg.alertaInfo("A vindima foi finalizada com sucesso!", "Sucesso!", "Vindima finalizada!");
                    numVindima.setText("");
                    qtdVindimada.setText("");

                }
                else{
                    System.out.println("A vindima nao foi encontrada!");
                    msg.alertaErro("A vindima não foi encontrado!", "Erro!", "Vindima não existe!");
                }

            }
            else{
                System.out.println("Por favor, selecione a checkbox para confirmar a remoção da vindima!");
                msg.alertaAviso("Por favor, selecione a checkbox para confirmar a remoção da vindima!)", "Aviso!", "Confirme a checkbox!");
            }



        }


    }

    public void btnConcluirVindimaCancelarClic(ActionEvent actionEvent) {

        numVindima.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
