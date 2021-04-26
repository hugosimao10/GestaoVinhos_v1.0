package app.controller;

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

public class selectQuintaToEditController {
    public Pane quintaSelectEditPane;
    public TextField numQuinta;
    public Button btnConfirmEditFunc;
    public Button btnCancelEditQuinta;
    public CheckBox checkEditQuinta;

    public void iniciar() throws SQLException {
        System.out.println("Está na area de inserir numero da quinta a editar!");

    }

    public void butConfirmQuintaEditClic(ActionEvent actionEvent) throws SQLException, IOException {

        Connection conn = Util.criarConexao();

        String quintaPro = numQuinta.getText();
        int idQuinta = Integer.getInteger(quintaPro);
        int conf = userID.getId();

        PreparedStatement pst = conn.prepareStatement("SELECT * FROM QUINTA WHERE ID_QUINTA = ? AND ID_EMPRESA = ?");
        pst.setInt(1, idQuinta);
        pst.setInt(2, conf);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {

            if (checkEditQuinta.isSelected()) {

                int idEditQuinta = rs.getInt("ID_QUINTA");
                String area_quinta = rs.getString("AREA_QUINTA");
                String localiz = rs.getString("LOCALIZACAO");


                FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/editQuintaPane.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Editar Plantação");
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();
                editQuintaController edit = loader.getController();
                edit.iniciar(idEditQuinta, area_quinta, localiz);

                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            } else {
                System.out.println("Selecione a checbox para confirmar!");
                msg.alertaAviso("Selecione a checkbox para confirmar!", "Aviso!", "Selecione a checkbox!");

            }

        }
    }

    public void btnVoltarEditQuinta(ActionEvent actionEvent) {

        numQuinta.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();


        }

}
