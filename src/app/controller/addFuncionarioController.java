package app.controller;

import app.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class addFuncionarioController {
    public ComboBox dropdownTipoFunc;
    public TextField usernameFunc;
    public PasswordField pwdFunc;
    public TextField nomeFunc;
    public TextField emailFunc;
    public TextField tlmFunc;
    public TextField ruaFunc;
    public TextField portaFunc;
    public TextField cpFunc;
    public Button btnConfirmAddFunc;
    public Button btnCancelAddFunc;
    public Pane funcionariosPane;

    public void iniciar() {
        System.out.println("Está na area de adicionar funcionários!");

    }

    // BOTAO PARA ADICONAR FUNCIONARIO
    @FXML
    public void btnAddFuncionarioAddClic(ActionEvent actionEvent) throws IOException, SQLException {


        Connection conn = Util.criarConexao();

        String tipo_func = dropdownTipoFunc.getValue().toString();
        String pass = this.usernameFunc.getText();
        String user = this.pwdFunc.getText();
        String nome = nomeFunc.getText();
        String email = emailFunc.getText();
        String tlm = tlmFunc.getText();
        String nporta = portaFunc.getText();
        String rua = ruaFunc.getText();
        String codpostal = cpFunc.getText();

        int estado = 1;
        int nportaInt=Integer.parseInt(nporta);
        int codpostalInt = Integer.parseInt(codpostal);

        // FALTA CODIGO DE VER A EMPRESA

        PreparedStatement pst = conn.prepareStatement("INSERT INTO FUNCIONARIO(TIPO_FUNCIONARIO, NOME, EMAIL, TLM, NPORTA," +
                "RUA, COD_POSTAL, ID_EMPRESA, PW, USERNAME, ESTADO) VALUES (?,?,?,?,?,?,?,?,?,?,?)");

        pst.setString(1, tipo_func);
        pst.setString(2, nome);
        pst.setString(3, email);
        pst.setString(4, tlm);
        pst.setInt(5, nportaInt);
        pst.setString(6, rua);
        pst.setInt(7, codpostalInt);
        pst.setInt(8, 1);  // EMPRESA
        pst.setString(9, pass);
        pst.setString(10, user);
        pst.setInt(11, estado);


        pst.executeQuery();


    }

    // BOTAO PARA CANCELAR
    @FXML
    public void btnAddFuncCancelarClic(ActionEvent actionEvent)throws IOException {

        dropdownTipoFunc.cancelEdit();
        usernameFunc.setText("");
        pwdFunc.setText("");
        nomeFunc.setText("");
        emailFunc.setText("");
        tlmFunc.setText("");
        ruaFunc.setText("");
        portaFunc.setText("");
        cpFunc.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

    }
}
