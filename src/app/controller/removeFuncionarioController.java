package app.controller;

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
    public void btnRemoverFuncRemoveClic(ActionEvent actionEvent) throws IOException {


    }

    // BOTAO DE CANCELAR NA PANE DE REMOVER FUNCIONARIO
    @FXML
    public void btnRemoveFuncCancelarClic(ActionEvent actionEvent)throws IOException {

        usernameRemoveFunc.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
