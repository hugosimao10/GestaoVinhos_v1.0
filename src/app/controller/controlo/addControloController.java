package app.controller.controlo;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.sql.SQLException;

public class addControloController {
    public Pane addControloPane;
    public TextField qtdAcucar;
    public TextField temperatura;
    public Button btnConfirmAddControlo;
    public Button btnCancelAddControlo;
    public TextField qualidadeAr;
    public DatePicker dtControlo;
    public TextField numVindima;
    public TextField funcionario;

    public void iniciar() throws SQLException {
        System.out.println("Está na area de adicionar Controlos!");


    }

    public void btnAddControloAddClic(ActionEvent actionEvent) {
    }

    public void btnAddControloCancelarClic(ActionEvent actionEvent) {
        qtdAcucar.setText("");
        temperatura.setText("");
        qualidadeAr.setText("");
        dtControlo.getEditor().clear();
        numVindima.setText("");
        funcionario.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
