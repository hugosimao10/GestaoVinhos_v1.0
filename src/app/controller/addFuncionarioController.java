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

public class addFuncionarioController {
    public ComboBox dropdownIdFunc;
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
    public void btnAddFuncionarioAddClic(ActionEvent actionEvent) throws IOException {


    }

    // BOTAO PARA CANCELAR
    @FXML
    public void btnAddFuncCancelarClic(ActionEvent actionEvent)throws IOException {

        dropdownIdFunc.cancelEdit();
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
