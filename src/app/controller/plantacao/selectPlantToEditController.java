package app.controller.plantacao;

import app.entities.userID;
import app.error.msg;
import app.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class selectPlantToEditController {
    public TextField numEditPlant;
    public Button btnConfirmEditPlant;
    public Button btnCancelEditCasta;
    public CheckBox checkEditPlant;
    public Pane plantSelectEditPane;

    public void iniciar() {
        System.out.println("Está na area de inserir plantação para editar!");

    }

    public void butConfirmPlantEditClic(ActionEvent actionEvent) throws SQLException, IOException {


    }

    public void butVoltarEditClic(ActionEvent actionEvent) {

        numEditPlant.setText("");
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
