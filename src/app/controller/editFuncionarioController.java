package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class editFuncionarioController {
    public ComboBox dropdownEditFunc;
    public TextField usernameEditFunc;
    public PasswordField pwdEditFunc;
    public TextField nomeEditFunc;
    public TextField emailEditFunc;
    public TextField tlmEditFunc;
    public TextField ruaEditFunc;
    public TextField portaEditFunc;
    public TextField cpEditFunc;
    public Button btnConfirmEditFunc;
    public Button btnCancelEditFunc;
    public Pane funcionariosEditPane;

    public void iniciar() {
        System.out.println("Está na area de editar funcionários!");


    }

    // BOTAO DE EDITAR FUNCIONARIO
    @FXML
    public void btnEditFuncEditClic(ActionEvent actionEvent)throws IOException {



    }

    // BOTAO DE CANCELAR DO PANE DE EDITAR FUNCIONARIO
    @FXML
    public void btnEditFuncCancelarClic(ActionEvent actionEvent) throws IOException{

        dropdownEditFunc.cancelEdit();
        usernameEditFunc.setText("");
        pwdEditFunc.setText("");
        nomeEditFunc.setText("");
        emailEditFunc.setText("");
        tlmEditFunc.setText("");
        ruaEditFunc.setText("");
        portaEditFunc.setText("");
        cpEditFunc.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
