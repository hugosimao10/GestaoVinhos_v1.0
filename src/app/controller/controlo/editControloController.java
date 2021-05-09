package app.controller.controlo;

import app.error.msg;
import app.util.Util;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

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
    public Text guardaIdEditControlo;

    public void iniciar(int idEdit, int qtdAc, int temp, int ar, LocalDate data, int numVin, int numFunc) throws SQLException {
        System.out.println("Está na area de editar Controlos!");

        guardaIdEditControlo.setVisible(false);
        guardaIdEditControlo.setText(String.valueOf(idEdit));

        qtdAcucar.setText(String.valueOf(qtdAc));
        temperatura.setText(String.valueOf(temp));
        qualidadeAr.setText(String.valueOf(ar));
        dtControlo.setValue(data);
        numVindima.setText(String.valueOf(numVin));
        funcionario.setText(String.valueOf(numFunc));

    }

    public void btnEditControloAddClic(ActionEvent actionEvent) throws SQLException {

        String acucarNovo = qtdAcucar.getText();
        String tempNova = temperatura.getText();
        String arNovo = qualidadeAr.getText();
        LocalDate dataNova = dtControlo.getValue();
        String numVinNova = numVindima.getText();
        String funNovo = funcionario.getText();


        if (acucarNovo.isEmpty() || tempNova.isEmpty() || arNovo.isEmpty() || numVinNova.isEmpty() || funNovo.isEmpty()) {

            System.out.println("Não pode haver campos vazios!");
            msg.alertaAviso("Não podem ficar campos vazios!", "Aviso!", "Campos vazios!");

        } else {

            Connection c1 = Util.criarConexao();
            String b = guardaIdEditControlo.getText();
            int q = Integer.parseInt(b);

            int a = Integer.parseInt(acucarNovo);
            int b1 = Integer.parseInt(tempNova);
            int c = Integer.parseInt(arNovo);
            int e = Integer.parseInt(numVinNova);
            int f = Integer.parseInt(funNovo);

            if (a > 2 && a < 8 && b1 > 14 && b1 < 26 && c > 2 && c <= 5) {

                int resultado = 1;

                PreparedStatement pst = c1.prepareStatement("UPDATE CONTROLO SET QTD_ACUCAR = ?, TEMPERATURA= ?," +
                        " QUALIDADE_AR = ?, DATA_HORA = ?, ID_PLANT_VINDIMA = ?, ID_FUNCIONARIO = ?, RESULTADO = ? WHERE ID_CONTROLO = ?");
                pst.setInt(1, a);
                pst.setInt(2, b1);
                pst.setInt(3, c);
                pst.setDate(4, Date.valueOf(dataNova));
                pst.setInt(5, e);
                pst.setInt(6, f);
                pst.setInt(7, resultado);
                pst.setInt(8, q);

                pst.executeQuery();

                System.out.println("Controlo alterado com sucesso!");
                msg.alertaInfo("Controlo alterada com sucesso, resultado positivo!", "Info!", "Sucesso!");


            } else {

                int resultado = 0;

                PreparedStatement pst = c1.prepareStatement("UPDATE CONTROLO SET QTD_ACUCAR = ?, TEMPERATURA= ?," +
                        " QUALIDADE_AR = ?, DATA_HORA = ?, ID_PLANT_VINDIMA = ?, ID_FUNCIONARIO = ?, RESULTADO = ? WHERE ID_CONTROLO = ?");
                pst.setInt(1, a);
                pst.setInt(2, b1);
                pst.setInt(3, c);
                pst.setDate(4, Date.valueOf(dataNova));
                pst.setInt(5, e);
                pst.setInt(6, f);
                pst.setInt(7, resultado);
                pst.setInt(8, q);

                pst.executeQuery();

                System.out.println("Controlo alterado com sucesso!");
                msg.alertaInfo("Controlo alterada com sucesso, resultado negativo!", "Info!", "Sucesso!");


            }

        }


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
