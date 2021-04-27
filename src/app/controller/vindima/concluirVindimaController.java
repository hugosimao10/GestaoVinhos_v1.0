package app.controller.vindima;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.sql.SQLException;

public class concluirVindimaController {
    public Pane concluirVindimaPane;
    public TextField numVindima;
    public Button btnConfirmConcluirVindima;
    public Button btnCancelConcluirVindima;
    public CheckBox checkConcluirVindima;
    public TextField qtdVindimada;

    public void iniciar() throws SQLException {
        System.out.println("Está na area de concluir Vindimas!");


    }

    public void btnConcluirVindimaClic(ActionEvent actionEvent) {
    }

    public void btnConcluirVindimaCancelarClic(ActionEvent actionEvent) {

        numVindima.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
