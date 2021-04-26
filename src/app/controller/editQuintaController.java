package app.controller;

import app.error.msg;
import app.util.Util;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class editQuintaController {
    public Pane editQuintaPane;
    public TextField areaQuinta;
    public TextField localizacaoQuinta;
    public Button btnConfirmEditQuinta;
    public Button btnCancelEditQuinta;
    public Text guardaEditQuinta;

    public void iniciar(int idEditQuinta, String area_quinta,String localiz) throws SQLException {

        System.out.println("Está na area de editar Quintas!");

        String a = String.valueOf(idEditQuinta);
        guardaEditQuinta.setVisible(false);
        guardaEditQuinta.setText(a);

        areaQuinta.setText(area_quinta);
        localizacaoQuinta.setText(localiz);


    }

    public void btnEditQuintaAddClic(ActionEvent actionEvent) throws SQLException {

        String areaNova = areaQuinta.getText();
        String localNovo = localizacaoQuinta.getText();

        if(areaNova.isEmpty() || localNovo.isEmpty()){

            System.out.println("Não pode haver campos vazios!");
            msg.alertaAviso("Não podem ficar campos vazios!", "Aviso!", "Campos vazios!");

        }
        else{

            Connection c1 = Util.criarConexao();
            String b = guardaEditQuinta.getText();
            int q = Integer.parseInt(b);

            PreparedStatement pst = c1.prepareStatement("UPDATE QUINTA SET AREA_QUINTA = ?, LOCALIZACAO= ?" +
                    " WHERE ID_QUINTA = ?");
            pst.setString(1, areaNova);
            pst.setString(2, localNovo);
            pst.setInt(3, q);

            pst.executeQuery();

            System.out.println("Quinta alterada com sucesso!");
            msg.alertaInfo("Quinta alterada com sucesso!", "Info!", "Sucesso!");

        }


    }

    public void btnEditQuintaCancelarClic(ActionEvent actionEvent) {

        areaQuinta.setText("");
        localizacaoQuinta.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

    }
}
