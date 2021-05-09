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
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class selectPlantToEditController {
    public TextField numEditPlant;
    public Button btnConfirmEditPlant;
    public Button btnCancelEditCasta;
    public CheckBox checkEditPlant;
    public Pane plantSelectEditPane;

    public void iniciar() {
        System.out.println("Está na area de inserir plantação para editar!");

        Pattern patternInt = Pattern.compile("^[0-9]*$");
        TextFormatter formatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return patternInt.matcher(change.getControlNewText()).matches() ? change : null;
        });

        numEditPlant.setTextFormatter(formatter);
    }

    public void butConfirmPlantEditClic(ActionEvent actionEvent) throws SQLException, IOException {

        Connection conn = Util.criarConexao();

        String p = numEditPlant.getText();
        int conf = userID.getId();

        if (p.isEmpty()) {
            msg.alertaAviso("Número de plantação não pode estar vazio!!", "Aviso!", "Insira um número!");
        } else {
            int proc = Integer.parseInt(p);

            PreparedStatement pst = conn.prepareStatement("SELECT p.*, q.ID_EMPRESA FROM PLANTACAO p, QUINTA q WHERE p.ID_PLANTACAO = ? AND q.ID_EMPRESA = ?");

            pst.setInt(1, proc);
            pst.setInt(2, conf);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                int idEdit = rs.getInt("ID_PLANTACAO");
                String area_casta = rs.getString("AREA_CASTA");
                String num_func = rs.getString("ID_FUNCIONARIO");
                int nQui = rs.getInt("ID_QUINTA");
                String idcasta = rs.getString("ID_CASTA");

                PreparedStatement pst2 = conn.prepareStatement("SELECT USERNAME FROM FUNCIONARIO WHERE ID_FUNCIONARIO = ?");
                pst2.setString(1, num_func);

                ResultSet rs2 = pst2.executeQuery();

                if (rs2.next()) {

                    String username = rs2.getString("USERNAME");

                    if (checkEditPlant.isSelected()) {


                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/plantacao/editPlantacaoPane.fxml"));
                        Parent root = loader.load();
                        Stage stage = new Stage();
                        stage.setTitle("Editar Plantação");
                        stage.setScene(new Scene(root));
                        stage.setResizable(false);
                        stage.show();
                        stage.getIcons().add(new Image("/img/logo.png"));
                        editPlantacaoController edit = loader.getController();
                        edit.iniciar(idEdit, area_casta, username, nQui, idcasta);

                        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
                    } else {
                        System.out.println("Selecione a checbox para confirmar!");
                        msg.alertaAviso("Selecione a checkbox para confirmar!", "Aviso!", "Selecione a checkbox!");
                    }
                } else {
                    System.out.println("FUNCIONÁRIO NÃO ENCONTRADO!");
                }


            } else {
                System.out.println("A plantacao a procurar não foi encontrada!");
                msg.alertaErro("A plantacao a procurar não foi encontrada!", "Erro!", "A plantação a procurar não foi encontrada!");

            }
        }

    }

    public void butVoltarEditClic(ActionEvent actionEvent) {

        numEditPlant.setText("");
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
