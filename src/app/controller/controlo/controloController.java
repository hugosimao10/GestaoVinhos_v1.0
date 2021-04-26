package app.controller.controlo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

import java.sql.SQLException;

public class controloController {
    @FXML
    public TableView table;
    public Pane controlosPane;
    @FXML
    public TableColumn colNum;
    @FXML
    public TableColumn colAcucar;
    @FXML
    public TableColumn colTemperatura;
    @FXML
    public TableColumn colData;
    @FXML
    public TableColumn colVindima;
    @FXML
    public TableColumn colFunc;
    public Button btnAddControlo;
    public Button btnEditControlo;

    public void iniciar() throws SQLException {
        System.out.println("Está na area de listar Controlos!");


    }

    public void btnAddControloClic(ActionEvent actionEvent) {
    }

    public void btnEditControloClic(ActionEvent actionEvent) {
    }
}
