package app.controller;

import app.error.msg;
import app.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class removeFuncionarioController {
    public Pane funcionariosRemovePane;
    public TextField usernameRemoveFunc;
    public Button btnConfirmRemoveFunc;
    public Button btnCancelRemoveFunc;
    public CheckBox checkRemoveFunc;

    public void iniciar() {
        System.out.println("Está na area de remover funcionários!");


    }

    // BOTAO DE REMOVER FUNCIONARIO NA PANE DE REMOVER FUNCIONARIO
    @FXML
    public void btnRemoverFuncRemoveClic(ActionEvent actionEvent) throws IOException, SQLException {

        String user =  usernameRemoveFunc.getText();


        Connection conn = Util.criarConexao();

        PreparedStatement pst = conn.prepareStatement("SELECT * FROM FUNCIONARIO WHERE USERNAME LIKE ?");

        pst.setString(1, user);

        ResultSet rs = pst.executeQuery();

        if(user.isEmpty()){
            System.out.println("Por favor, preencha o username!");
            msg.alertaAviso("Por favor insira o username que pretende remover!", "Aviso!", "Username não pode estar vazio!");
        }
        else{

            if(checkRemoveFunc.isSelected()){


            if(rs.next()){

                PreparedStatement pst1 = conn.prepareStatement("DELETE FROM FUNCIONARIO WHERE USERNAME LIKE ?");
                pst1.setString(1,user);
                pst1.executeQuery();
                System.out.println("O user foi removido com sucesso!");
                msg.alertaInfo("O funcionário foi removido com sucesso!", "Sucesso!", "Funcionário encontrado!");
                usernameRemoveFunc.setText("");

            }
            else{
                System.out.println("O user nao foi encontrado!");
                msg.alertaErro("O username não foi encontrado!", "Erro!", "Username não existe!");
            }

            }
            else{
                System.out.println("Por favor, selecione a checkbox para confirmar que pretende remover!");
                msg.alertaAviso("Por favor selecione a checkbox!", "Aviso!", "Confirme que pretende remover!");
            }



        }



    }

    // BOTAO DE CANCELAR NA PANE DE REMOVER FUNCIONARIO
    @FXML
    public void btnRemoveFuncCancelarClic(ActionEvent actionEvent)throws IOException {

        usernameRemoveFunc.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
