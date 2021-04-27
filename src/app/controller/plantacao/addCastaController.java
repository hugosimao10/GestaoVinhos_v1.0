package app.controller.plantacao;

import app.error.msg;
import app.util.Util;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class addCastaController {
    public Pane addCastaPane;
    public TextField nomeCasta;
    public Button btnConfirmAddCasta;
    public Button btnCancelAddCasta;

    public void iniciar() {
        System.out.println("Está na area de adicionar castas!");


    }

    public void btnAddCastaClic(ActionEvent actionEvent) throws SQLException {

        String castaNova = nomeCasta.getText();

        if(castaNova.isEmpty()){
            System.out.println("Não podem ficar campos vazios!");
            msg.alertaAviso("Não podem ficar campos vazios!", "Aviso!", "Campos vazios!");

        }
        else {

            Connection con = Util.criarConexao();

            PreparedStatement p1 = con.prepareStatement("SELECT * FROM CASTA WHERE TIPO_CASTA LIKE ?");
            p1.setString(1, castaNova);

            ResultSet rs = p1.executeQuery();

            if (rs.next()) {

                System.out.println("A casta já existe!");
                msg.alertaErro("A casta já existe!", "Erro!", "Casta existente!");
                nomeCasta.setText("");

            } else {
                PreparedStatement p = con.prepareStatement("INSERT INTO CASTA(TIPO_CASTA) VALUES (?)");
                p.setString(1, castaNova);
                p.executeQuery();

                System.out.println("Plantacao adicionado com sucesso!");
                msg.alertaInfo("Plantacao adicionado com sucesso!", "Info!", "Sucesso!");

                nomeCasta.setText("");
            }
        }
    }

    public void btnCancelAddCasta(ActionEvent actionEvent) {

        nomeCasta.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
