package app.controller.plantacao;

import app.util.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class addPlantacaoController {
    public Pane plantacoesPane;
    public TextField areaCasta;
    public TextField usernameFunc;
    public Button btnConfirmAddPlant;
    public Button btnCancelAddPlant;
    public ComboBox comboBoxCasta;
    public TextField numQuinta;

    @FXML
    private ObservableList<String> tiposCasta;

    public void iniciar() throws SQLException {
        System.out.println("Está na area de adicionar plantações!");

        Connection c = Util.criarConexao();

        PreparedStatement p = c.prepareStatement("SELECT * FROM CASTA");


        ResultSet r = p.executeQuery();

        tiposCasta = FXCollections.observableArrayList();

        while(r.next()){

            String c4 = r.getString("TIPO_CASTA");
            tiposCasta.add(c4);
            comboBoxCasta.setValue(c4);

        }

        comboBoxCasta.setItems(tiposCasta);


    }

    public void btnAddPlantAddClic(ActionEvent actionEvent) {




    }

    public void btnAddPlantCancelarClic(ActionEvent actionEvent) {

        areaCasta.setText("");
        usernameFunc.setText("");
        numQuinta.setText("");
        comboBoxCasta.cancelEdit();

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
