package app.controller.plantacao;

import app.error.msg;
import app.util.Util;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class editPlantacaoController {
    public Pane editPlantacoesPane;
    public TextField areaCasta;
    public TextField usernameFunc;
    public Button btnConfirmAddPlant;
    public Button btnCancelEditPlant;
    public ComboBox comboBoxCasta;
    public TextField numQuinta;
    public Text guardaIdPlant;

    @FXML
    private ObservableList<String> castas;

    public void iniciar(int idEdit, String area_casta, String num_func, int nQui, String idcasta) throws SQLException {
        System.out.println("Está na area de editar avaliações!");

        guardaIdPlant.setVisible(false);
        String a = String.valueOf(idEdit);
        guardaIdPlant.setText(a);

        int i = Integer.parseInt(idcasta);

        Connection c44 = Util.criarConexao();

        PreparedStatement p33 = c44.prepareStatement("SELECT * FROM CASTA");

        ResultSet set1 = p33.executeQuery();

        castas = FXCollections.observableArrayList();

        while(set1.next()){

            String cargo1 = set1.getString("TIPO_CASTA");
            castas.add(cargo1);
            comboBoxCasta.setValue(cargo1);

        }

        comboBoxCasta.setItems(castas);
        areaCasta.setText(area_casta);
        usernameFunc.setText(num_func);
        String nQuinStr = String.valueOf(nQui);
        numQuinta.setText(nQuinStr);

    }

    public void btnEditPlantAddClic(ActionEvent actionEvent) throws SQLException {

        String r = guardaIdPlant.getText();
        int idEdit = Integer.parseInt(r);

        Connection c1 = Util.criarConexao();

        String novaArea = areaCasta.getText();
        String novoFunc = usernameFunc.getText();
        String novaQui = numQuinta.getText();
        String castaEsc = comboBoxCasta.getValue().toString();

        if(novaArea.isEmpty() || novoFunc.isEmpty() || novaQui.isEmpty()){

            System.out.println("Não podem ficar campos vazios!");
            msg.alertaAviso("Não podem ficar campos vazios!", "Aviso!", "Campos vazios!");
        }
        else {

            PreparedStatement pst1 = c1.prepareStatement("SELECT * FROM CASTA WHERE TIPO_CASTA = ?");
            pst1.setString(1, castaEsc);

            ResultSet rr = pst1.executeQuery();

            if(rr.next()){

                int a = rr.getInt("ID_CASTA");
            int novoF = Integer.parseInt(novoFunc);
            int novaQ = Integer.parseInt(novaQui);



            PreparedStatement pst = c1.prepareStatement("UPDATE PLANTACAO SET AREA_CASTA = ?, ID_FUNCIONARIO= ?, " +
                    "ID_QUINTA= ?, ID_CASTA= ? WHERE ID_PLANTACAO = ?");
            pst.setString(1,novaArea);
            pst.setInt(2,novoF);
            pst.setInt(3,novaQ);
            pst.setInt(4,a);
            pst.setInt(5,idEdit);

            pst.executeQuery();

                System.out.println("Plantação alterada com sucesso!");
                msg.alertaInfo("Plantação alterada com sucesso!", "Info!", "Sucesso!");


            }
            else{
                System.out.println("A casta relacionada não foi encontrada!");
            }

        }


    }

    public void btnEditPlantCancelarClic(ActionEvent actionEvent) {

        areaCasta.setText("");
        usernameFunc.setText("");
        numQuinta.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

    }
}
