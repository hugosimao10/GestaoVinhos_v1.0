package app.controller.vindima;

import app.controller.plantacao.editPlantacaoController;
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
import java.sql.*;
import java.time.LocalDate;

public class selectVindimaToEditController {
    public Pane plantSelectEditVindima;
    public TextField numEditVindima;
    public Button btnCancelEditVindima;
    public Button btnConfirmEditVindima;
    public CheckBox checkEditVindima;

    public void iniciar() throws SQLException {
        System.out.println("Está na area de selecionar Vindima a editar!");


    }

    public void butConfirmVindimaEditClic(ActionEvent actionEvent) throws SQLException, IOException {

        Connection conn = Util.criarConexao();

        String p = numEditVindima.getText();
        int proc = Integer.parseInt(p);

        int conf = userID.getId();

        PreparedStatement pst = conn.prepareStatement("SELECT pv.*, f.ID_EMPRESA, v.* FROM PLANTACAO_VINDIMA pv, FUNCIONARIO f, VINDIMA v WHERE pv.ID_FUNCIONARIO = f.ID_FUNCIONARIO AND f.ID_EMPRESA = ? AND pv.ID_PLANT_VINDIMA = ? AND pv.ID_VINDIMA = v.ID_VINDIMA");
        pst.setInt(1, conf);
        pst.setInt(2, proc);

        ResultSet rs = pst.executeQuery();

        if(rs.next()) {

            if(checkEditVindima.isSelected()) {


                int idEdit = rs.getInt("ID_PLANT_VINDIMA");
                Date dataInic = rs.getDate("DATA_INICIO_VINDIMA");
                Date dataFim = rs.getDate("DATA_FIM_VINDIMA");
                int num_func = rs.getInt("ID_FUNCIONARIO");
                int nPla = rs.getInt("ID_PLANTACAO");
                int qtdV = rs.getInt("QTD_VINDIMADA");


                FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/vindima/editVindimaPane.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Editar Vindima");
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();
                editVindimaController edit = loader.getController();
                edit.iniciar(idEdit, dataInic, dataFim,num_func, nPla, qtdV);

                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            }
            else{

                System.out.println("Por favor, selecione a checkbox!");
                msg.alertaAviso("Selecione a checkbox para confirmar!", "Aviso!", "Selecione a checkbox!");

            }


        }
        else{
            System.out.println("QUERY nao tem resultado, selectVindimaEditController!");
        }


    }

    public void butVoltarEditClic(ActionEvent actionEvent) {

        numEditVindima.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }


}
