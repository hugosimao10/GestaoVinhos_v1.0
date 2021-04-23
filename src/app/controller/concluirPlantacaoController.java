package app.controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class concluirPlantacaoController {
    public TextField numPlantacao;
    public Button btnConfirmConcluirPlant;
    public Pane conclurPlantacoesPane;
    public Button btnCancelConcluirPlant;
    public CheckBox checkConcluirPlant;

    public void iniciar() {
        System.out.println("Está na area de concluir plantações!");


    }

    public void btnConcluirPlantClic(ActionEvent actionEvent) {
    }

    public void btnConcluirPlantCancelarClic(ActionEvent actionEvent) {

        numPlantacao.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
