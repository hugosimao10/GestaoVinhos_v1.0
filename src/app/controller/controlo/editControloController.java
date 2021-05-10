package app.controller.controlo;

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

public class editControloController {
    public TextField qtdAcucar;
    public TextField temperatura;
    public Button btnConfirmEditControlo;
    public Button btnCancelEditControlo;
    public TextField qualidadeAr;
    public DatePicker dtControlo;
    public TextField numVindima;
    public TextField funcionario;
    public Pane editControloPane;
    public int guardaIdEditControlo;

    public void iniciar(int idEdit, int qtdAc, int temp, int ar, LocalDate data, int numVin, int numFunc) throws SQLException {
        System.out.println("Está na area de editar Controlos!");

        Pattern patternQtd = Pattern.compile("\\d*|\\d+\\.\\d*");
        TextFormatter formatterQtdAcucar = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return patternQtd.matcher(change.getControlNewText()).matches() ? change : null;
        });
        TextFormatter formatterTemperatura = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return patternQtd.matcher(change.getControlNewText()).matches() ? change : null;
        });
        TextFormatter formatterAr = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return patternQtd.matcher(change.getControlNewText()).matches() ? change : null;
        });

        Pattern patternInt = Pattern.compile("^[0-9]*$");
        TextFormatter formaterNumVindima = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return patternInt.matcher(change.getControlNewText()).matches() ? change : null;
        });
        TextFormatter formaterNumFuncionario = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return patternInt.matcher(change.getControlNewText()).matches() ? change : null;
        });
        numVindima.setTextFormatter(formaterNumVindima);
        funcionario.setTextFormatter(formaterNumFuncionario);

        qtdAcucar.setTextFormatter(formatterQtdAcucar);
        temperatura.setTextFormatter(formatterTemperatura);
        qualidadeAr.setTextFormatter(formatterAr);

        dtControlo.getEditor().setEditable(false);

        qtdAcucar.setText(String.valueOf(qtdAc));
        temperatura.setText(String.valueOf(temp));
        qualidadeAr.setText(String.valueOf(ar));
        dtControlo.setValue(data);
        numVindima.setText(String.valueOf(numVin));
        funcionario.setText(String.valueOf(numFunc));

        guardaIdEditControlo = idEdit;

    }

    public void btnEditControloAddClic(ActionEvent actionEvent) throws SQLException {

        String acucarNovo = qtdAcucar.getText();
        String tempNova = temperatura.getText();
        String arNovo = qualidadeAr.getText();
        LocalDate dataNova = dtControlo.getValue();
        String numVinNova = numVindima.getText();
        String funNovo = funcionario.getText();


        if (acucarNovo.isEmpty() || tempNova.isEmpty() || arNovo.isEmpty() || numVinNova.isEmpty() || funNovo.isEmpty() || dataNova == null) {

            System.out.println("Não pode haver campos vazios!");
            msg.alertaAviso("Não podem ficar campos vazios!", "Aviso!", "Campos vazios!");

        } else {

            Connection c1 = Util.criarConexao();
            int q = guardaIdEditControlo;

            LocalDate dataIn = dtControlo.getValue();
            double a = Double.parseDouble(acucarNovo);
            double b1 = Double.parseDouble(tempNova);
            double c = Double.parseDouble(arNovo);
            int e = Integer.parseInt(numVinNova);
            int f = Integer.parseInt(funNovo);

            PreparedStatement ps = c1.prepareStatement("SELECT DATA_FIM_VINDIMA FROM PLANTACAO_VINDIMA WHERE ID_PLANT_VINDIMA = ?");
            ps.setInt(1, e);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Date dataFimBd = rs.getDate("DATA_FIM_VINDIMA");
                LocalDate dtFim = dataFimBd.toLocalDate();
                System.out.println(dtFim);
                if (dataIn.isBefore(dtFim)) {
                    System.out.println("Data controlo anterior à data fim de vindima!");
                    msg.alertaErro("Data de controlo anterior à data de fim da vindima!", "Erro!", "Data controlo inválida!");
                } else {

                    if (a > 2 && a < 8 && b1 > 14 && b1 < 26 && c > 2 && c <= 5) {

                        int resultado = 1;

                        preparedStatement(dataNova, c1, q, a, b1, c, e, f, resultado);
                        msg.alertaInfo("Controlo alterada com sucesso, resultado positivo!", "Info!", "Sucesso!");

                        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

                    } else {

                        int resultado = 0;

                        preparedStatement(dataNova, c1, q, a, b1, c, e, f, resultado);
                        msg.alertaInfo("Controlo alterada com sucesso, resultado negativo!", "Info!", "Sucesso!");

                        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

                    }
                }
            }

        }


    }

    private void preparedStatement(LocalDate dataNova, Connection c1, int q, double a, double b1, double c, int e, int f, int resultado) throws SQLException {
        PreparedStatement pst = c1.prepareStatement("UPDATE CONTROLO SET QTD_ACUCAR = ?, TEMPERATURA= ?," +
                " QUALIDADE_AR = ?, DATA_HORA = ?, ID_PLANT_VINDIMA = ?, ID_FUNCIONARIO = ?, RESULTADO = ? WHERE ID_CONTROLO = ?");
        pst.setDouble(1, a);
        pst.setDouble(2, b1);
        pst.setDouble(3, c);
        pst.setDate(4, Date.valueOf(dataNova));
        pst.setInt(5, e);
        pst.setInt(6, f);
        pst.setInt(7, resultado);
        pst.setInt(8, q);

        pst.executeQuery();

        System.out.println("Controlo alterado com sucesso!");


    }

    public void btnEditControloCancelarClic(ActionEvent actionEvent) {

        qtdAcucar.setText("");
        temperatura.setText("");
        qualidadeAr.setText("");
        dtControlo.getEditor().clear();
        numVindima.setText("");
        funcionario.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
