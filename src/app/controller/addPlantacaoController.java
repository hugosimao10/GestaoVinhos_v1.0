package app.controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class addPlantacaoController {
    public Pane plantacoesPane;
    public TextField areaCasta;
    public TextField usernameFunc;
    public Button btnConfirmAddPlant;
    public Button btnCancelAddPlant;
    public ComboBox comboBoxCasta;
    public TextField numQuinta;

    public void iniciar() {
        System.out.println("Está na area de adicionar plantações!");


    }

    public void btnAddPlantAddClic(ActionEvent actionEvent) {


    }

    public void btnAddPlantCancelarClic(ActionEvent actionEvent) {

        areaCasta.setText("");
        usernameFunc.setText("");
        numQuinta.setText("");
        comboBoxCasta.cancelEdit();

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
