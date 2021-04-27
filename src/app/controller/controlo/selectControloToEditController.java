package app.controller.controlo;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.sql.SQLException;

public class selectControloToEditController {
    public Pane controloSelectEditPane;
    public TextField numEditControlo;
    public Button btnConfirmEditControlo;
    public Button btnCancelEditControlo;
    public CheckBox checkEditControlo;

    public void iniciar() throws SQLException {
        System.out.println("Está na area de selecionar Controlo a editar!");


    }

    public void butConfirmControloEditClic(ActionEvent actionEvent) {
    }

    public void butVoltarEditClic(ActionEvent actionEvent) {

        numEditControlo.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }


}
