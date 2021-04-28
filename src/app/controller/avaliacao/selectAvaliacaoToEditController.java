package app.controller.avaliacao;

import app.controller.controlo.editControloController;
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

public class selectAvaliacaoToEditController {
    public Pane avaliacaoSelectEditPane;
    public TextField numEditAvaliacao;
    public Button btnConfirmEditAvaliacao;
    public Button btnCancelEditAvaliacao;
    public CheckBox checkEditAvaliacao;
    public void iniciar() {
        System.out.println("Está na area de selecionar avaliacao a editar!");


    }

    public void butConfirmAvaliacaoEditClic(ActionEvent actionEvent) throws IOException, SQLException {

        Connection conn = Util.criarConexao();

        String id1 = numEditAvaliacao.getText();
        int id = Integer.parseInt(id1);
        int conf = userID.getId();

        PreparedStatement pst = conn.prepareStatement("SELECT c.*, f.*, a.* FROM CONTROLO c, FUNCIONARIO f, AVALIACAO a WHERE a.ID_AVALIACAO = ? AND c.ID_AVALIACAO = a.ID_AVALIACAO AND c.ID_FUNCIONARIO = f.ID_FUNCIONARIO AND f.ID_EMPRESA = ?");
        pst.setInt(1, id);
        pst.setInt(2, conf);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {

            if (checkEditAvaliacao.isSelected()) {

                int idEdit = rs.getInt("ID_AVALIACAO");
                int qtdProd = rs.getInt("QTD_PRODUZIDA");
                int idContr = rs.getInt("ID_CONTROLO");
                String qualVinho = rs.getString("QUALIDADE_VINHO");



                FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/avaliacao/editAvaliacaoPane.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Editar Avaliação");
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();
                editAvaliacaoController edit = loader.getController();
                edit.iniciar(idEdit, idContr, qtdProd, qualVinho);

                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            } else {
                System.out.println("Selecione a checbox para confirmar!");
                msg.alertaAviso("Selecione a checkbox para confirmar!", "Aviso!", "Selecione a checkbox!");

            }

        }
        else{
            System.out.println("Avaliação nao encontrada!");
            msg.alertaErro("Avaliação nao encontrada!", "Erro!", "ID não existe!");
        }



    }

    public void butVoltarEditClic(ActionEvent actionEvent) {
        numEditAvaliacao.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
