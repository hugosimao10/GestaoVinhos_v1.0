package app.controller.vindima;

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
import java.time.LocalDate;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class selectVindimaToEditController {
    public Pane plantSelectEditVindima;
    public TextField numEditVindima;
    public Button btnCancelEditVindima;
    public Button btnConfirmEditVindima;
    public CheckBox checkEditVindima;

    public void iniciar() throws SQLException {
        System.out.println("Está na area de selecionar Vindima a editar!");

        Pattern pattern = Pattern.compile("^[0-9]*$");
        TextFormatter formatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });

        numEditVindima.setTextFormatter(formatter);

    }

    public void butConfirmVindimaEditClic(ActionEvent actionEvent) throws SQLException, IOException {

        Connection conn = Util.criarConexao();

        String p = numEditVindima.getText();

        if (p.isEmpty()) {
            msg.alertaAviso("Insira um número de vindima para continuar!", "Aviso!", "Insira número de vindima!");
        } else {
            int proc = Integer.parseInt(p);
            int conf = userID.getId();
            LocalDate dataFim = null;

            PreparedStatement pst = conn.prepareStatement("SELECT pv.*, f.ID_EMPRESA, v.* FROM PLANTACAO_VINDIMA pv, FUNCIONARIO f, VINDIMA v WHERE pv.ID_FUNCIONARIO = f.ID_FUNCIONARIO AND f.ID_EMPRESA = ? AND pv.ID_PLANT_VINDIMA = ? AND pv.ID_VINDIMA = v.ID_VINDIMA");
            pst.setInt(1, conf);
            pst.setInt(2, proc);

            ResultSet rs = pst.executeQuery();

            if (checkEditVindima.isSelected()) {
                if (rs.next()) {


                    int idEdit = rs.getInt("ID_PLANT_VINDIMA");
                    LocalDate dataInic = rs.getDate("DATA_INICIO_VINDIMA").toLocalDate();
                    if (rs.getDate("DATA_FIM_VINDIMA") != null) {
                        dataFim = rs.getDate("DATA_FIM_VINDIMA").toLocalDate();
                    }
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
                    stage.getIcons().add(new Image("/img/logo.png"));
                    editVindimaController edit = loader.getController();
                    edit.iniciar(idEdit, dataInic, dataFim, num_func, nPla, qtdV);

                    ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
                } else {
                    System.out.println("QUERY nao tem resultado, selectVindimaEditController!");
                    msg.alertaAviso("A vindima selecionada não existe!", "Aviso!", "Insira um número válido!");
                }
            } else {

                System.out.println("Por favor, selecione a checkbox!");
                msg.alertaAviso("Selecione a checkbox para confirmar!", "Aviso!", "Selecione a checkbox!");

            }

        }
    }

    public void butVoltarEditClic(ActionEvent actionEvent) {
        numEditVindima.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }


}
