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

public class addControloController {
    public Pane addControloPane;
    public TextField qtdAcucar;
    public TextField temperatura;
    public Button btnConfirmAddControlo;
    public Button btnCancelAddControlo;
    public TextField qualidadeAr;
    public DatePicker dtControlo;
    public TextField numVindima;
    public TextField funcionario;

    public void iniciar() throws SQLException {
        System.out.println("Está na area de adicionar Controlos!");

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
    }

    public void btnAddControloAddClic(ActionEvent actionEvent) throws SQLException {

        String a = qtdAcucar.getText();
        String b = temperatura.getText();
        String c = qualidadeAr.getText();
        String d = numVindima.getText();
        String e = funcionario.getText();
        LocalDate g = dtControlo.getValue();

        if (a.isEmpty() || b.isEmpty() || c.isEmpty() || d.isEmpty() || e.isEmpty() || g == null) {

            System.out.println("Não podem ficar campos em branco");
            msg.alertaAviso("Não podem ficar campos vazios!", "Aviso!", "Campos vazios!");
        } else {
            LocalDate dataIn = dtControlo.getValue();
            double qtdAcuc = Double.parseDouble(a);
            double temp = Double.parseDouble(b);
            double qualAr = Double.parseDouble(c);
            int numVin = Integer.parseInt(d);
            int func = Integer.parseInt(e);

            Connection c1 = Util.criarConexao();

            PreparedStatement ps = c1.prepareStatement("SELECT DATA_FIM_VINDIMA FROM PLANTACAO_VINDIMA WHERE ID_PLANT_VINDIMA = ?");
            ps.setInt(1, numVin);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Date dataFimBd = rs.getDate("DATA_FIM_VINDIMA");
                LocalDate dtFim = dataFimBd.toLocalDate();
                System.out.println(dtFim);
                if (dataIn.isBefore(dtFim)) {
                    System.out.println("Data controlo anterior à data fim de vindima!");
                    msg.alertaErro("Data de controlo anterior à data de fim da vindima!", "Erro!", "Data controlo inválida!");
                } else {
                    if (qtdAcuc > 2 && qtdAcuc < 8 && temp > 14 && temp < 26 && qualAr > 2 && qualAr <= 5) {
                        int resultado = 1;

                        preparedStatement(dataIn, qtdAcuc, temp, qualAr, numVin, func, c1, resultado);

                        System.out.println("Controlo adicionado com sucesso, com resultado positivo");
                        msg.alertaInfo("Controlo passado com sucesso!", "Info!", "Resultado positivo!");

                    } else {
                        int resultado = 0;
                        preparedStatement(dataIn, qtdAcuc, temp, qualAr, numVin, func, c1, resultado);

                        System.out.println("Controlo adicionado com sucesso, com resultado negativo");
                        msg.alertaInfo("As condições não permitem a produção de um bom vinho!", "Info!", "Resultado negativo!");


                    }
                    qtdAcucar.setText("");
                    temperatura.setText("");
                    qualidadeAr.setText("");
                    numVindima.setText("");
                    funcionario.setText("");
                    dtControlo.getEditor().clear();
                    ((Node) (actionEvent.getSource())).getScene().getWindow().hide();


                }
            }
        }


    }

    private void preparedStatement(LocalDate dataIn, double qtdAcuc, double temp, double qualAr, int numVin, int func, Connection c1, int resultado) throws SQLException {
        PreparedStatement p4 = c1.prepareStatement("INSERT INTO CONTROLO(QTD_ACUCAR, TEMPERATURA, QUALIDADE_AR, DATA_HORA," +
                "ID_PLANT_VINDIMA, ID_FUNCIONARIO, RESULTADO)" +
                "VALUES (?,?,?,?,?,?,?)");
        p4.setDouble(1, qtdAcuc);
        p4.setDouble(2, temp);
        p4.setDouble(3, qualAr);
        p4.setDate(4, Date.valueOf(dataIn));
        p4.setInt(5, numVin);
        p4.setInt(6, func);
        p4.setInt(7, resultado);

        p4.executeQuery();
    }

    public void btnAddControloCancelarClic(ActionEvent actionEvent) {
        qtdAcucar.setText("");
        temperatura.setText("");
        qualidadeAr.setText("");
        dtControlo.getEditor().clear();
        numVindima.setText("");
        funcionario.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
