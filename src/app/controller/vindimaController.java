package app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

import java.sql.SQLException;

public class vindimaController {
    public Pane vindimasPane;
    @FXML
    public TableView table;
    @FXML
    public TableColumn colNum;
    @FXML
    public TableColumn colDtIni;
    @FXML
    public TableColumn colDtFim;
    @FXML
    public TableColumn colFunc;
    @FXML
    public TableColumn colPlant;
    @FXML
    public TableColumn colQtd;
    public Button btnAddVindima;
    public Button btnDeleteFuncionario;

    public void iniciar() throws SQLException {
        System.out.println("Está na area de listar Vindimas!");


    }

    public void btnAddVindimaClic(ActionEvent actionEvent) {
    }

    public void btnConcluirVindimaClic(ActionEvent actionEvent) {
    }
}
