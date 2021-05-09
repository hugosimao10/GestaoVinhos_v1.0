package app.controller.embalamento;

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

public class selectEmbalamentoToEditController {
    public Pane embalSelectEditPane;
    public TextField numEmbalamento;
    public Button btnConfirmEditEmbalamento;
    public Button btnCancelEditEmbalamento;
    public CheckBox checkEditEmbalamento;

    public void iniciar() throws SQLException {
        System.out.println("Está na area de selecionar embalamento para editar!");


    }

    public void butConfirmEmbalamentoEditClic(ActionEvent actionEvent) throws SQLException, IOException {


        Connection conn = Util.criarConexao();

        String id1 = numEmbalamento.getText();
        int id = Integer.parseInt(id1);
        int conf = userID.getId();

        PreparedStatement pst = conn.prepareStatement("SELECT c.*, f.*, a.*, e.* FROM CONTROLO c, FUNCIONARIO f, AVALIACAO a, PRODUTOFINAL e WHERE e.ID_PRODUTO_FINAL = ? AND c.ID_AVALIACAO = a.ID_AVALIACAO AND c.ID_FUNCIONARIO = f.ID_FUNCIONARIO AND f.ID_EMPRESA = ? AND a.ID_PRODUTO_FINAL = e.ID_PRODUTO_FINAL");
        pst.setInt(1, id);
        pst.setInt(2, conf);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {

            if (checkEditEmbalamento.isSelected()) {

                int idEdit = rs.getInt("ID_PRODUTO_FINAL");
                int qtdCaixasEdit = rs.getInt("QTD_CAIXAS");
                int idAva = rs.getInt("ID_CONTROLO");
                String tipoVinhoEdit = rs.getString("TIPO_VINHO");
                LocalDate data1 = rs.getDate("DATA_EMB").toLocalDate();


                FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/embalamento/editEmbalamentoPane.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Editar Embalamento");
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();
                editEmbalamentoController edit = loader.getController();
                edit.iniciar(idEdit, qtdCaixasEdit, idAva, tipoVinhoEdit, data1);

                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            } else {
                System.out.println("Selecione a checbox para confirmar!");
                msg.alertaAviso("Selecione a checkbox para confirmar!", "Aviso!", "Selecione a checkbox!");

            }

        } else {
            System.out.println("Embalamento nao encontrado!");
            msg.alertaErro("Embalamento nao encontrado!", "Erro!", "ID não existe!");
        }


    }

    public void btnVoltarEditEmbalamento(ActionEvent actionEvent) {
        numEmbalamento.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
