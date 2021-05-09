package app.controller.embalamento;

import app.error.msg;
import app.util.Util;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.sql.*;
import java.time.LocalDate;

public class addEmbalamentoController {
    public Pane addEmbalamentoPane;
    public TextField idAvaliacao;
    public TextField qtdCaixas;
    public Button btnConfirmAddEmbalamento;
    public Button btnCancelAddEmbalamento;
    public TextField tipoVinho;
    public DatePicker datePickerEmbalamento;

    public void iniciar() throws SQLException {
        System.out.println("Está na area de adicionar embalamentos!");


    }

    public void btnAddEmbalamentoAddClic(ActionEvent actionEvent) throws SQLException {

        String idAva = idAvaliacao.getText();
        String qtdCai = qtdCaixas.getText();
        String tpVinho = tipoVinho.getText();
        LocalDate dataVinho = datePickerEmbalamento.getValue();


        if (idAva.isEmpty() || qtdCai.isEmpty() || tpVinho.isEmpty()) {

            System.out.println("Não podem ficar campos em branco");
            msg.alertaAviso("Não podem ficar campos vazios!", "Aviso!", "Campos vazios!");

        } else {

            int idAvaInt = Integer.parseInt(idAva);
            int qtdCaiInt = Integer.parseInt(qtdCai);


            Connection c1 = Util.criarConexao();

            PreparedStatement p4 = c1.prepareStatement("SELECT a.*, e.* FROM AVALIACAO a, PRODUTOFINAL e WHERE a.ID_AVALIACAO = ?");
            p4.setInt(1, idAvaInt);

            ResultSet sss = p4.executeQuery();

            if (sss.next()) {
                PreparedStatement p5 = c1.prepareStatement("INSERT INTO PRODUTOFINAL(QTD_CAIXAS, TIPO_VINHO, DATA_EMB) VALUES (?,?,?)");
                p5.setInt(1, qtdCaiInt);
                p5.setString(2, tpVinho);
                p5.setDate(3, Date.valueOf(dataVinho));
                p5.executeQuery();


                PreparedStatement p8 = c1.prepareStatement("SELECT * FROM PRODUTOFINAL WHERE QTD_CAIXAS = ? AND TIPO_VINHO = ?");
                p8.setInt(1, qtdCaiInt);
                p8.setString(2, tpVinho);

                ResultSet s1 = p8.executeQuery();

                if (s1.next()) {

                    PreparedStatement p6 = c1.prepareStatement("UPDATE AVALIACAO SET ID_PRODUTO_FINAL = ? WHERE ID_AVALIACAO = ?");
                    p6.setInt(1, s1.getInt("ID_PRODUTO_FINAL"));
                    p6.setInt(2, idAvaInt);
                    p6.executeQuery();

                    System.out.println("Embalamento finalizado!");
                    msg.alertaInfo("Embalamento finalizado com sucesso!", "Info!", "Embalamento bem sucedido!");

                    idAvaliacao.setText("");
                    qtdCaixas.setText("");
                    tipoVinho.setText("");

                    ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

                } else {
                    System.out.println("ID AVALIACAO ASSOCIADO NAO ENCONTRADO (78, addAvaliacao)");
                }


            } else {
                System.out.println("A avaliação inserida não existe!");
                msg.alertaAviso("A avaliação inserida não existe!", "Erro!", "Avaliação inexistente");

            }


        }


    }

    public void btnAddEmbalamentoCancelarClic(ActionEvent actionEvent) {
        idAvaliacao.setText("");
        qtdCaixas.setText("");
        tipoVinho.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
