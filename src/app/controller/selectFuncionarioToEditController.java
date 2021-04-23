package app.controller;

import app.error.msg;
import app.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class selectFuncionarioToEditController {
    public Pane funcionariosSelectEditPane;
    public TextField usernameEditFunc;
    public Button btnConfirmEditFunc;
    public Button btnCancelEditFunc;
    public CheckBox checkEditFunc;

    public void iniciar() {
        System.out.println("Está na area de inserir nome de funcionario a editar!");

    }


    public void butConfirmFuncEditClic(ActionEvent actionEvent) throws SQLException {

        Connection conn = Util.criarConexao();

        String userProcurar = usernameEditFunc.getText();

        PreparedStatement pst = conn.prepareStatement("SELECT * FROM FUNCIONARIO WHERE USERNAME LIKE ?");

        pst.setString(1, userProcurar);

        ResultSet rs = pst.executeQuery();

        if(rs.next()){



        }
        else {
            System.out.println("O funcionário a procurar não foi encontrado!");
            msg.alertaErro("O funcionário a procurar não foi encontrado!", "Erro!", "O user a procurar não foi encontrado!");

        }

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

    }

    public void butVoltarEditClic(ActionEvent actionEvent) {


        usernameEditFunc.setText("");
        checkEditFunc.setSelected(false);

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
