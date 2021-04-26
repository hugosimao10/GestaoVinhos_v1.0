package app.controller.quinta;

import app.entities.userID;
import app.error.msg;
import app.util.Util;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import org.w3c.dom.ls.LSOutput;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class addQuintaController {
    public TextField areaQuinta;
    public Pane addQuintaPane;
    public TextField localizacaoQuinta;
    public Button btnConfirmAddQuinta;
    public Button btnCancelAddQuinta;

    public void iniciar() throws SQLException {
        System.out.println("Está na area de adicionar quintas!");

    }

    public void btnAddQuintaAddClic(ActionEvent actionEvent) throws SQLException {

        int EmprLog = userID.getId();
        String areaQ = areaQuinta.getText();
        String local = localizacaoQuinta.getText();
        int ativa = 1;


        if(areaQ.isEmpty() || local.isEmpty()){

            System.out.println("Não podem ficar campos em branco");
            msg.alertaAviso("Não podem ficar campos vazios!", "Aviso!", "Campos vazios!");

        }
        else{

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
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

        }





    }

    public void btnAddQuintaCancelarClic(ActionEvent actionEvent) {

        areaQuinta.setText("");
        localizacaoQuinta.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

    }
}
