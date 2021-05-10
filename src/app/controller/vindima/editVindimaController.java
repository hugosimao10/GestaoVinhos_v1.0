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

public class editVindimaController {
    public Pane editVindimasPane;
    public TextField usernameFunc;
    public TextField numPlant;
    public Button btnConfirmEditVindima;
    public Button btnCancelEditVindima;
    public DatePicker dtIniVindima;
    public DatePicker dtFimVindima;
    public TextField qtdVindimada;
    public int idEditar;

    public void iniciar(int idEdit, LocalDate dataInic, LocalDate dataFim, int num_func, int nPla, int qtdV) throws SQLException {
        System.out.println("Está na area de editar Vindimas!");

        usernameFunc.setText(String.valueOf(num_func));
        numPlant.setText(String.valueOf(nPla));
        qtdVindimada.setText(String.valueOf(qtdV));
        dtIniVindima.setValue(dataInic);
        dtFimVindima.setValue(dataFim);
        dtIniVindima.getEditor().setEditable(false);
        dtFimVindima.getEditor().setEditable(false);

        Pattern pattern = Pattern.compile("^[0-9]*$");
        TextFormatter formatterUsername = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });
        TextFormatter formatterQuinta = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });

        usernameFunc.setTextFormatter(formatterUsername);
        numPlant.setTextFormatter(formatterQuinta);

        Pattern patternQtd = Pattern.compile("\\d*|\\d+\\.\\d*");
        TextFormatter formatterQtdVindimada = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return patternQtd.matcher(change.getControlNewText()).matches() ? change : null;
        });

        qtdVindimada.setTextFormatter(formatterQtdVindimada);

        idEditar = idEdit;

    }

    public void btnEditVindimaClic(ActionEvent actionEvent) throws SQLException {

        String a = usernameFunc.getText();
        String b = numPlant.getText();
        String c = qtdVindimada.getText();
        LocalDate dataI = dtIniVindima.getValue();
        LocalDate dataF;

        if (dataI == null || a.isEmpty() || b.isEmpty() || c.isEmpty()) {
            System.out.println("Não pode haver campos vazios!");
            msg.alertaAviso("Não podem ficar campos vazios!", "Aviso!", "Campos vazios!");

        } else {
            int func = Integer.parseInt(a);
            int numPla = Integer.parseInt(b);
            int qtdVi = Integer.parseInt(c);

            if (dtFimVindima.getValue() != null) {
                dataF = dtFimVindima.getValue();

                if (dataF.isBefore(dataI)) {
                    msg.alertaErro("A data de fim deve ser superior à data de início!", "ERRO!", "Data inválida!");
                } else {
                    Connection c2 = Util.criarConexao();

                    PreparedStatement pst2 = c2.prepareStatement("SELECT * FROM VINDIMA WHERE DATA_INICIO_VINDIMA = ?");
                    pst2.setDate(1, Date.valueOf(dataI));
                    ResultSet s1 = pst2.executeQuery();

                    if (s1.next()) {

                        PreparedStatement pst = c2.prepareStatement("UPDATE PLANTACAO_VINDIMA SET QTD_VINDIMADA = ?, DATA_FIM_VINDIMA= ?," +
                                "ID_VINDIMA = ?, ID_PLANTACAO = ?, ID_FUNCIONARIO = ?" +
                                " WHERE ID_PLANT_VINDIMA = ?");

                        pst.setInt(1, qtdVi);
                        if (dataF != null) {
                            pst.setDate(2, Date.valueOf(dataF));
                        } else {
                            pst.setDate(2, null);
                        }
                        pst.setInt(3, s1.getInt("ID_VINDIMA"));
                        pst.setInt(4, numPla);
                        pst.setInt(5, func);
                        pst.setInt(6, idEditar);

                        pst.executeQuery();

                        System.out.println("Vindima alterada com sucesso!");
                        msg.alertaInfo("Vindima alterada com sucesso!", "Info!", "Sucesso!");

                        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

                    } else {


                        PreparedStatement p9 = c2.prepareStatement("INSERT INTO VINDIMA(DATA_INICIO_VINDIMA) VALUES (?)");
                        p9.setDate(1, Date.valueOf(dataI));
                        p9.executeQuery();

                        PreparedStatement p5 = c2.prepareStatement("SELECT * FROM VINDIMA WHERE DATA_INICIO_VINDIMA = ?");
                        p5.setDate(1, Date.valueOf(dataI));

                        ResultSet s = p5.executeQuery();

                        if (s.next()) {

                            int a1 = s.getInt("ID_VINDIMA");

                            PreparedStatement pst = c2.prepareStatement("UPDATE PLANTACAO_VINDIMA SET QTD_VINDIMADA = ?, DATA_FIM_VINDIMA= ?," +
                                    "ID_VINDIMA = ?, ID_PLANTACAO = ?, ID_FUNCIONARIO = ?" +
                                    " WHERE ID_PLANT_VINDIMA = ?");

                            pst.setInt(1, qtdVi);
                            pst.setDate(2, Date.valueOf(dataF));
                            pst.setInt(3, a1);
                            pst.setInt(4, numPla);
                            pst.setInt(5, func);
                            pst.setInt(6, idEditar);

                            pst.executeQuery();

                            System.out.println("Vindima alterada com sucesso, com data inicio!");
                            msg.alertaInfo("Vindima alterada com sucesso, com nova data de inicio!", "Info!", "Sucesso!");

                            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

                        } else {
                            System.out.println("Erro a criar o novo ID da nova data de inicio de vindima. editVindimaCotnroller!");
                        }

                    }
                }
            }


        }


    }

    public void btnEditVindimaCancelarClic(ActionEvent actionEvent) {

        usernameFunc.setText("");
        numPlant.setText("");
        dtFimVindima.getEditor().clear();
        dtIniVindima.getEditor().clear();
        qtdVindimada.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
