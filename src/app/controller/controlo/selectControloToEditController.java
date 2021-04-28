package app.controller.controlo;

import app.controller.quinta.editQuintaController;
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
import java.time.LocalDate;

public class selectControloToEditController {
    public Pane controloSelectEditPane;
    public TextField numEditControlo;
    public Button btnConfirmEditControlo;
    public Button btnCancelEditControlo;
    public CheckBox checkEditControlo;

    public void iniciar() throws SQLException {
        System.out.println("Está na area de selecionar Controlo a editar!");


    }

    public void butConfirmControloEditClic(ActionEvent actionEvent) throws IOException, SQLException {

        Connection conn = Util.criarConexao();

        String id1 = numEditControlo.getText();
        int id = Integer.parseInt(id1);
        int conf = userID.getId();

        PreparedStatement pst = conn.prepareStatement("SELECT c.*, f.* FROM CONTROLO c, FUNCIONARIO f WHERE ID_CONTROLO = ? AND f.ID_FUNCIONARIO = c.ID_FUNCIONARIO AND ID_EMPRESA = ?");
        pst.setInt(1, id);
        pst.setInt(2, conf);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {

            if (checkEditControlo.isSelected()) {

                int idEdit = rs.getInt("ID_CONTROLO");
                int qtdAc = rs.getInt("QTD_ACUCAR");
                int temp = rs.getInt("TEMPERATURA");
                int ar = rs.getInt("QUALIDADE_AR");
                LocalDate data = rs.getDate("DATA_HORA").toLocalDate();
                int numVin = rs.getInt("ID_PLANT_VINDIMA");
                int numFunc = rs.getInt("ID_FUNCIONARIO");


                FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/controlo/editControloPane.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Editar Controlo");
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();
                editControloController edit = loader.getController();
                edit.iniciar(idEdit, qtdAc, temp, ar, data, numVin, numFunc);

                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            } else {
                System.out.println("Selecione a checbox para confirmar!");
                msg.alertaAviso("Selecione a checkbox para confirmar!", "Aviso!", "Selecione a checkbox!");

            }

        }



    }

    public void butVoltarEditClic(ActionEvent actionEvent) {

        numEditControlo.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }


}
