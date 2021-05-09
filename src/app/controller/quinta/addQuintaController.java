package app.controller.quinta;

import app.controller.dashboard.DashboardController;
import app.entities.userID;
import app.error.msg;
import app.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class addQuintaController {
    private static final URL location = null;
    private static final ResourceBundle resourceBundle = null;
    public TextField areaQuinta;
    public Pane addQuintaPane;
    public TextField localizacaoQuinta;
    public Button btnConfirmAddQuinta;
    public Button btnCancelAddQuinta;

    public void iniciar() throws SQLException {
        System.out.println("Está na area de adicionar quintas!");

        Pattern pattern = Pattern.compile("\\d*|\\d+\\.\\d*");
        TextFormatter formatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });

        areaQuinta.setTextFormatter(formatter);
    }

    public void btnAddQuintaAddClic(ActionEvent actionEvent) throws SQLException, IOException, ParseException {

        int EmprLog = userID.getId();
        String areaQ = areaQuinta.getText();
        String local = localizacaoQuinta.getText();
        int ativa = 1;


        if (areaQ.isEmpty() || local.isEmpty()) {

            System.out.println("Não podem ficar campos em branco");
            msg.alertaAviso("Não podem ficar campos vazios!", "Aviso!", "Campos vazios!");

        } else if (Double.parseDouble(areaQ) > 0) {

            Connection c1 = Util.criarConexao();

            PreparedStatement p4 = c1.prepareStatement("INSERT INTO QUINTA(AREA_QUINTA, LOCALIZACAO, ID_EMPRESA, ATIVA)" +
                    "VALUES (?,?,?,?)");
            p4.setString(1, areaQ);
            p4.setString(2, local);
            p4.setInt(3, EmprLog);
            p4.setInt(4, ativa);

            p4.executeQuery();

            System.out.println("Quinta adicionada com sucesso");
            msg.alertaInfo("Quinta adicionada com sucesso!", "Info!", "Sucesso!");

            areaQuinta.setText("");
            localizacaoQuinta.setText("");


            FXMLLoader loader = new FXMLLoader();/*new FXMLLoader(getClass().getResource("/app/ui/dashboard/dashboard.fxml"));*/
            loader.setLocation(getClass().getResource("/app/ui/quinta/quintasPane.fxml"));
            quintaController controller = new quintaController();
            loader.setController(controller);
            controller.iniciar();

            //((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            closeAddQuinta(btnConfirmAddQuinta);

        } else {
            msg.alertaErro("Área deve ser superior a 0!", "Erro!", "Área menor ou igual a 0!");
        }


    }

    public void closeAddQuinta(Button btn) throws FileNotFoundException, ParseException
    {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    public void btnAddQuintaCancelarClic(ActionEvent actionEvent) {

        areaQuinta.setText("");
        localizacaoQuinta.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

    }

    @FXML
    public void buttonPressed(KeyEvent e) {
        if (e.getCode().toString().equals("ENTER")) {
            btnConfirmAddQuinta.fire();
        }
    }
}
