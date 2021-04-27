package app.controller.controlo;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.sql.SQLException;

public class editControloController {
    public TextField qtdAcucar;
    public TextField temperatura;
    public Button btnConfirmEditControlo;
    public Button btnCancelEditControlo;
    public TextField qualidadeAr;
    public DatePicker dtControlo;
    public TextField numVindima;
    public TextField funcionario;
    public Pane editControloPane;
    public Text guardaIdEditControlo;

    public void iniciar() throws SQLException {
        System.out.println("Está na area de editar Controlos!");


    }

    public void btnEditControloAddClic(ActionEvent actionEvent) {
    }

    public void btnEditControloCancelarClic(ActionEvent actionEvent) {

        qtdAcucar.setText("");
        temperatura.setText("");
        qualidadeAr.setText("");
        dtControlo.getEditor().clear();
        numVindima.setText("");
        funcionario.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
