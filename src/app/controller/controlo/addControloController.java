package app.controller.controlo;

import app.error.msg;
import app.util.Util;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

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

    }

    public void btnAddControloAddClic(ActionEvent actionEvent) throws SQLException {

        String a = qtdAcucar.getText();
        String b = temperatura.getText();
        String c = qualidadeAr.getText();
        String d = numVindima.getText();
        String e = funcionario.getText();
        String g = String.valueOf(dtControlo.getValue());


        if (a.isEmpty() || b.isEmpty() || c.isEmpty() || d.isEmpty() || e.isEmpty() || g.isEmpty()) {

            System.out.println("Não podem ficar campos em branco");
            msg.alertaAviso("Não podem ficar campos vazios!", "Aviso!", "Campos vazios!");
        } else {
            LocalDate dataIn = dtControlo.getValue();
            int qtdAcuc = Integer.parseInt(a);
            int temp = Integer.parseInt(b);
            int qualAr = Integer.parseInt(c);
            int numVin = Integer.parseInt(d);
            int func = Integer.parseInt(e);

            if (qtdAcuc > 2 && qtdAcuc < 8 && temp > 14 && temp < 26 && qualAr > 2 && qualAr <= 5) {

                Connection c1 = Util.criarConexao();
                int resultado = 1;
                PreparedStatement p4 = c1.prepareStatement("INSERT INTO CONTROLO(QTD_ACUCAR, TEMPERATURA, QUALIDADE_AR, DATA_HORA," +
                        "ID_PLANT_VINDIMA, ID_FUNCIONARIO, RESULTADO)" +
                        "VALUES (?,?,?,?,?,?,?)");
                p4.setInt(1, qtdAcuc);
                p4.setInt(2, temp);
                p4.setInt(3, qualAr);
                p4.setDate(4, Date.valueOf(dataIn));
                p4.setInt(5, numVin);
                p4.setInt(6, func);
                p4.setInt(7, resultado);

                p4.executeQuery();

                System.out.println("Controlo adicionado com sucesso, com resultado positivo");
                msg.alertaInfo("Controlo passado com sucesso!", "Info!", "Resultado positivo!");

                qtdAcucar.setText("");
                temperatura.setText("");
                qualidadeAr.setText("");
                numVindima.setText("");
                funcionario.setText("");
                dtControlo.getEditor().clear();
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            } else {

                Connection c1 = Util.criarConexao();
                int resultado = 0;
                PreparedStatement p4 = c1.prepareStatement("INSERT INTO CONTROLO(QTD_ACUCAR, TEMPERATURA, QUALIDADE_AR, DATA_HORA," +
                        "ID_PLANT_VINDIMA, ID_FUNCIONARIO, RESULTADO)" +
                        "VALUES (?,?,?,?,?,?,?)");
                p4.setInt(1, qtdAcuc);
                p4.setInt(2, temp);
                p4.setInt(3, qualAr);
                p4.setDate(4, Date.valueOf(dataIn));
                p4.setInt(5, numVin);
                p4.setInt(6, func);
                p4.setInt(7, resultado);

                p4.executeQuery();

                System.out.println("Controlo adicionado com sucesso, com resultado negativo");
                msg.alertaInfo("As condições não permitem a produção de um bom vinho!", "Info!", "Resultado negativo!");

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
