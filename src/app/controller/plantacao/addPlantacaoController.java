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
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Pane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class addPlantacaoController {
    public Pane plantacoesPane;
    public TextField areaCasta;
    public Button btnConfirmAddPlant;
    public Button btnCancelAddPlant;
    public ComboBox comboBoxCasta;
    public TextField numFunc;
    public TextField numQuinta;

    @FXML
    private ObservableList<String> tiposCasta;

    public void iniciar() throws SQLException {

        Pattern pattern = Pattern.compile("\\d*|\\d+\\.\\d*");
        TextFormatter formatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });

        areaCasta.setTextFormatter(formatter);

        Pattern patternInt = Pattern.compile("^[0-9]*$");
        TextFormatter formatterFunc = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return patternInt.matcher(change.getControlNewText()).matches() ? change : null;
        });
        TextFormatter formatterQuinta = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return patternInt.matcher(change.getControlNewText()).matches() ? change : null;
        });

        numFunc.setTextFormatter(formatterFunc);
        numQuinta.setTextFormatter(formatterQuinta);

        System.out.println("Está na area de adicionar plantações!");

        Connection c = Util.criarConexao();

        PreparedStatement p = c.prepareStatement("SELECT * FROM CASTA");


        ResultSet r = p.executeQuery();

        tiposCasta = FXCollections.observableArrayList();

        while (r.next()) {

            String c4 = r.getString("TIPO_CASTA");
            tiposCasta.add(c4);
            comboBoxCasta.setValue(c4);

        }

        comboBoxCasta.setItems(tiposCasta);


    }

    public void btnAddPlantAddClic(ActionEvent actionEvent) throws SQLException {

        int estado = 1;
        String area = areaCasta.getText();
        String a = numQuinta.getText();
        String nFunc = numFunc.getText();
        String castaEscolhida = comboBoxCasta.getValue().toString();

        if (area.isEmpty() || a.isEmpty() || nFunc.isEmpty()) {
            System.out.println("Não podem ficar campos vazios!");
            msg.alertaAviso("Não podem ficar campos vazios!", "Aviso!", "Campos vazios!");

        } else {
            int a1 = Integer.parseInt(a);
            int nFunc1 = Integer.parseInt(nFunc);

            Connection c1 = Util.criarConexao();

            PreparedStatement p5 = c1.prepareStatement("SELECT * FROM CASTA WHERE TIPO_CASTA = ?");
            p5.setString(1, castaEscolhida);

            ResultSet s = p5.executeQuery();

            PreparedStatement p6 = c1.prepareStatement("SELECT * FROM QUINTA WHERE ID_QUINTA = ?");
            p6.setInt(1, a1);

            ResultSet s2 = p6.executeQuery();

            PreparedStatement p7 = c1.prepareStatement("SELECT * FROM FUNCIONARIO WHERE ID_FUNCIONARIO = ?");
            p7.setInt(1, nFunc1);

            ResultSet s3 = p7.executeQuery();

            if (s.next()) {
                if (s3.next()) {
                    if (s2.next()) {


                        int rec = s.getInt("ID_CASTA");

                        PreparedStatement p4 = c1.prepareStatement("INSERT INTO PLANTACAO(AREA_CASTA, ID_FUNCIONARIO, ID_QUINTA, ID_CASTA, ESTADO) VALUES (?,?,?,?,?)");
                        p4.setString(1, area);
                        p4.setInt(2, nFunc1);
                        p4.setInt(3, a1);
                        p4.setInt(4, rec);
                        p4.setInt(5, estado);

                        p4.executeQuery();

                        System.out.println("Plantacao adicionada com sucesso!");
                        msg.alertaInfo("Plantacao adicionada com sucesso!", "Info!", "Sucesso!");

                        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

                        areaCasta.setText("");
                        numFunc.setText("");
                        numQuinta.setText("");
                        comboBoxCasta.cancelEdit();
                    } else {
                        msg.alertaErro("Quinta não encontrada!", "Erro!", "Quinta inexistente!");
                    }
                } else {
                    msg.alertaErro("Funcionário não encontrado!", "Erro!", "Funcionário inexistente!");
                }
            } else {
                System.out.println("O ID da casta relacionada não foi encontrada!");
            }

        }


    }

    public void btnAddPlantCancelarClic(ActionEvent actionEvent) {

        areaCasta.setText("");
        numFunc.setText("");
        numQuinta.setText("");
        comboBoxCasta.cancelEdit();

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
