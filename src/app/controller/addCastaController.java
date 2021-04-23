package app.controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class addCastaController {
    public Pane addCastaPane;
    public TextField nomeCasta;
    public Button btnConfirmAddCasta;
    public Button btnCancelAddCasta;

    public void iniciar() {
        System.out.println("Está na area de adicionar castass!");


    }

    public void btnAddCastaClic(ActionEvent actionEvent) {
    }

    public void btnCancelAddCasta(ActionEvent actionEvent) {

        nomeCasta.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
