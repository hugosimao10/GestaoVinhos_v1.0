package app.controller.embalamento;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.sql.SQLException;

public class selectEmbalamentoToEditController {
    public Pane embalSelectEditPane;
    public TextField numEmbalamento;
    public Button btnConfirmEditEmbalamento;
    public Button btnCancelEditEmbalamento;
    public CheckBox checkEditEmbalamento;

    public void iniciar() throws SQLException {
        System.out.println("Está na area de selecionar embalamento para editar!");


    }

    public void butConfirmEmbalamentoEditClic(ActionEvent actionEvent) {
    }

    public void btnVoltarEditEmbalamento(ActionEvent actionEvent) {
        numEmbalamento.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
