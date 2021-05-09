package app.controller.vindima;

import app.error.msg;
import app.util.Util;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Pane;

import java.sql.*;
import java.time.LocalDate;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class addVindimaController {
    public Pane vindimasPane;
    public TextField numPlant;
    public Button btnConfirmAddVindima;
    public Button btnCancelAddFunc;
    public DatePicker dtIniVindima;
    public TextField idFUnc;

    public void iniciar() throws SQLException {
        System.out.println("Está na area de adicionar Vindimas!");

        Pattern patternInt = Pattern.compile("^[0-9]*$");
        TextFormatter formatterPlant = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return patternInt.matcher(change.getControlNewText()).matches() ? change : null;
        });
        TextFormatter formatterFunc = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return patternInt.matcher(change.getControlNewText()).matches() ? change : null;
        });

        numPlant.setTextFormatter(formatterPlant);
        idFUnc.setTextFormatter(formatterFunc);


    }

    public void btnAddVindimaClic(ActionEvent actionEvent) throws SQLException {

        LocalDate dataIni = dtIniVindima.getValue();
        String idDoFunc = idFUnc.getText();
        String idPlant = numPlant.getText();

        if (idDoFunc.isEmpty() || idPlant.isEmpty()) {
            System.out.println("Não podem ficar campos vazios!");
            msg.alertaAviso("Não podem ficar campos vazios!", "Aviso!", "Campos vazios!");

        } else {

            int idFuncInt = Integer.parseInt(idDoFunc);
            int idPlanInt = Integer.parseInt(idPlant);

            Connection c1 = Util.criarConexao();

            PreparedStatement p5 = c1.prepareStatement("INSERT INTO VINDIMA(DATA_INICIO_VINDIMA) VALUES (?)");
            p5.setDate(1, Date.valueOf(dataIni));

            ResultSet s = p5.executeQuery();

            PreparedStatement p9 = c1.prepareStatement("SELECT * FROM FUNCIONARIO WHERE ID_FUNCIONARIO = ?");
            p9.setInt(1, idFuncInt);

            ResultSet s3 = p9.executeQuery();

            PreparedStatement p8 = c1.prepareStatement("SELECT * FROM PLANTACAO WHERE ID_PLANTACAO = ?");
            p8.setInt(1, idPlanInt);

            ResultSet s4 = p8.executeQuery();

            if (s.next()) {
                if (s3.next()) {
                    if (s4.next()) {
                        PreparedStatement p6 = c1.prepareStatement("SELECT * FROM VINDIMA WHERE DATA_INICIO_VINDIMA = ?");
                        p6.setDate(1, Date.valueOf(dataIni));

                        ResultSet s1 = p6.executeQuery();
                        if (s1.next()) {
                            int idDataIni = s1.getInt("ID_VINDIMA");

                            PreparedStatement p7 = c1.prepareStatement("INSERT INTO PLANTACAO_VINDIMA(ID_VINDIMA, ID_PLANTACAO, ID_FUNCIONARIO, DATA_FIM_VINDIMA) VALUES (?,?,?,?)");
                            p7.setInt(1, idDataIni);
                            p7.setInt(2, idPlanInt);
                            p7.setInt(3, idFuncInt);
                            p7.setDate(4, Date.valueOf(LocalDate.of(2021, 10, 10)));

                            p7.executeQuery();

                            System.out.println("Vindima adicionada com sucesso!");
                            msg.alertaInfo("Vindima adicionada com sucesso!", "Info!", "Sucesso!");

                            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

                            idFUnc.setText("");
                            numPlant.setText("");
                            dtIniVindima.getEditor().clear();
                        }
                    } else {
                        msg.alertaErro("Plantação não encontrada!", "Erro!", "Plantação inexistente!");
                    }
                } else {
                    msg.alertaErro("Funcionário não encontrado!", "Erro!", "Funcionário inexistente!");
                }
            } else {
                System.out.println("Não foi encontrada relação de ID-VINDIMA , DATA INICIO na parte de adicionar vindima!");
            }
        }
    }

    public void btnAddVindimaCancelarClic(ActionEvent actionEvent) {

        idFUnc.setText("");
        numPlant.setText("");
        dtIniVindima.getEditor().clear();

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
