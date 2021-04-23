package app.controller;

import app.error.msg;
import app.guardaDados.userID;
import app.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

        //String tipo_func = dropdownTipoFunc.getValue().toString();
        String pass = this.usernameFunc.getText();
        String user = this.pwdFunc.getText();
        String nome = nomeFunc.getText();
        String email = emailFunc.getText();
        String tlm = tlmFunc.getText();
        String rua = ruaFunc.getText();
       ;

        int estado = 1;
        int nportaInt=Integer.parseInt(portaFunc.getText());
        int codpostalInt = Integer.parseInt(cpFunc.getText());
        int idEmpresaLogada = userID.getId();

        PreparedStatement pst = conn.prepareStatement("INSERT INTO FUNCIONARIO(NOME, EMAIL, TLM, NPORTA," +
                "RUA, COD_POSTAL, ID_EMPRESA, PW, USERNAME, ESTADO, TIPO_FUNCIONARIO) VALUES (?,?,?,?,?,?,?,?,?,?,?)");


        pst.setString(1, nome);
        pst.setString(2, email);
        pst.setString(3, tlm);
        pst.setInt(4, nportaInt);
        pst.setString(5, rua);
        pst.setInt(6, codpostalInt);
        pst.setInt(7, idEmpresaLogada);  // EMPRESA
        pst.setString(8, pass);
        pst.setString(9, user);
        pst.setInt(10, estado);
        pst.setInt(11, 1);

        pst.executeQuery();

        if(pst.executeQuery().next()){
            System.out.println("Funcionário adicionado com sucesso!");
            msg.alertaInfo("Funcionário adicionado com sucesso!", "Info!", "Sucesso!");

        }
        else{
            System.out.println("Erro ao adicionar funcionário!");
            msg.alertaErro("Erro ao adicionar funcionário!", "Erro!", "Insucesso!");

        }


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
