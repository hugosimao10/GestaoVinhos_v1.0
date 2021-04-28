package app.controller.embalamento;

import app.error.msg;
import app.util.Util;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.sql.*;
import java.time.LocalDate;

public class editEmbalamentoController {
    public Pane editEmbalamentoPane;
    public TextField idAvaliacao;
    public TextField qtdCaixas;
    public Button btnConfirmEditEmbalamento;
    public Button btnCancelEditEmbalamento;
    public TextField tipoVinho;
    public Text guardaIdEditEmbalamento;
    public DatePicker datePickerEmbalamento;

    public void iniciar(int idEdit, int qtdCaixasEdit, int idAva, String tipoVinhoEdit, LocalDate data1) throws SQLException {
        System.out.println("Está na area de editar embalamentos!");

        guardaIdEditEmbalamento.setVisible(false);
        guardaIdEditEmbalamento.setText(String.valueOf(idEdit));

        idAvaliacao.setText(String.valueOf(idAva));
        qtdCaixas.setText(String.valueOf(qtdCaixasEdit));
        tipoVinho.setText(tipoVinhoEdit);
        datePickerEmbalamento.setValue(data1);


    }

    public void btnEditEmbalamentoCancelarClic(ActionEvent actionEvent) {

        idAvaliacao.setText("");
        qtdCaixas.setText("");
        tipoVinho.setText("");
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void btnEditEmbalamentoAddClic(ActionEvent actionEvent) throws SQLException {

        String a = idAvaliacao.getText();
        String b = qtdCaixas.getText();
        String c = tipoVinho.getText();
        LocalDate d = datePickerEmbalamento.getValue();

        if(a.isEmpty() || b.isEmpty() || c.isEmpty()){

            System.out.println("Não podem ficar campos em branco");
            msg.alertaAviso("Não podem ficar campos vazios!", "Aviso!", "Campos vazios!");

        }
        else{

            int newAva = Integer.parseInt(a);
            int newQtdCai = Integer.parseInt(b);

            Connection c1 = Util.criarConexao();

                PreparedStatement p4 = c1.prepareStatement("SELECT a.*, e.* FROM AVALIACAO a, PRODUTOFINAL e WHERE e.ID_PRODUTO_FINAL = ?");
                p4.setInt(1, newAva);

                ResultSet sss = p4.executeQuery();
                String aa = guardaIdEditEmbalamento.getText();
                int idEditEmbala = Integer.parseInt(aa);

                if(sss.next()){

                    PreparedStatement p6 = c1.prepareStatement("UPDATE PRODUTOFINAL SET QTD_CAIXAS = ?, TIPO_VINHO = ?, DATA_EMB = ? WHERE ID_PRODUTO_FINAL = ?");
                    p6.setInt(1,newQtdCai);
                    p6.setString(2,c);
                    p6.setString(3, String.valueOf(d));
                    p6.setInt(4, idEditEmbala);
                    p6.executeQuery();

                    System.out.println("Embalamento alterado com sucesso!");
                    msg.alertaInfo("Embalamento alterado com sucesso", "Info!", "Embalamento alterado!");

                    idAvaliacao.setText("");
                    qtdCaixas.setText("");
                    tipoVinho.setText("");
                    datePickerEmbalamento.getEditor().clear();;

                    ((Node) (actionEvent.getSource())).getScene().getWindow().hide();



                }
                else{
                    System.out.println("Avaliação nao encontrada!");
                    msg.alertaAviso("Avaliação nao encontrada!", "Erro!", "ID de Avaliacao inválido!");

                }

        }







    }

}
