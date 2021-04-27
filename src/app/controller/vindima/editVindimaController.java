package app.controller.vindima;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class editVindimaController {
    public Pane editVindimasPane;
    public TextField usernameFunc;
    public TextField numPlant;
    public Button btnConfirmEditVindima;
    public Button btnCancelEditVindima;
    public DatePicker dtIniVindima;
    public DatePicker dtFimVindima;
    public TextField qtdVindimada;

    public void iniciar(int idEdit, Date dataInic, Date dataFim, int num_func, int nPla, int qtdV) throws SQLException {
        System.out.println("Está na area de editar Vindimas!");

        usernameFunc.setText(String.valueOf(num_func));
        numPlant.setText(String.valueOf(nPla));
        qtdVindimada.setText(String.valueOf(qtdV));



    }

    public void btnEditVindimaClic(ActionEvent actionEvent) {
    }

    public void btnEditVindimaCancelarClic(ActionEvent actionEvent) {

        usernameFunc.setText("");
        numPlant.setText("");
        dtFimVindima.getEditor().clear();
        dtIniVindima.getEditor().clear();
        qtdVindimada.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
