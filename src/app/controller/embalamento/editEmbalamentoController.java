package app.controller.embalamento;

import app.error.msg;
import app.util.Util;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Pane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class editEmbalamentoController {
    public Pane editEmbalamentoPane;
    public TextField idAvaliacao;
    public TextField qtdCaixas;
    public Button btnConfirmEditEmbalamento;
    public Button btnCancelEditEmbalamento;
    public TextField tipoVinho;
    public int guardaIdEditEmbalamento;
    public DatePicker datePickerEmbalamento;

    public void iniciar(int idEdit, int qtdCaixasEdit, int idAva, String tipoVinhoEdit, LocalDate data1) throws SQLException {
        System.out.println("Está na area de editar embalamentos!");

        Pattern patternInt = Pattern.compile("^[0-9]*$");
        TextFormatter formaterNumAvaliacao = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return patternInt.matcher(change.getControlNewText()).matches() ? change : null;
        });
        TextFormatter formaterNumCaixas = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return patternInt.matcher(change.getControlNewText()).matches() ? change : null;
        });

        idAvaliacao.setTextFormatter(formaterNumAvaliacao);
        qtdCaixas.setTextFormatter(formaterNumCaixas);

        idAvaliacao.setText(String.valueOf(idAva));
        qtdCaixas.setText(String.valueOf(qtdCaixasEdit));
        tipoVinho.setText(tipoVinhoEdit);
        datePickerEmbalamento.setValue(data1);

        guardaIdEditEmbalamento = idEdit;
        idAvaliacao.setDisable(true);

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

        if (a.isEmpty() || b.isEmpty() || c.isEmpty() || d == null) {

            System.out.println("Não podem ficar campos em branco");
            msg.alertaAviso("Não podem ficar campos vazios!", "Aviso!", "Campos vazios!");

        } else {

            int newAva = Integer.parseInt(a);
            int newQtdCai = Integer.parseInt(b);

            Connection c1 = Util.criarConexao();

            int idEditEmbala = guardaIdEditEmbalamento;

            PreparedStatement p4 = c1.prepareStatement("SELECT a.*, e.*, c.* FROM AVALIACAO a, PRODUTOFINAL e, CONTROLO c WHERE e.ID_PRODUTO_FINAL = ? AND e.ID_PRODUTO_FINAL = a.ID_PRODUTO_FINAL AND a.ID_AVALIACAO = c.ID_AVALIACAO");
            p4.setInt(1, idEditEmbala);
            System.out.println(idEditEmbala);

            ResultSet sss = p4.executeQuery();

            if (sss.next()) {

                LocalDate dtControl = sss.getDate("DATA_HORA").toLocalDate();

                if (d.isBefore(dtControl)) {
                    System.out.println("Data inferior ao controlo!");
                    msg.alertaErro("Data embalamento inferior à data controlo!", "Erro!", "Data inválida!");
                } else {

                    PreparedStatement p6 = c1.prepareStatement("UPDATE PRODUTOFINAL SET QTD_CAIXAS = ?, TIPO_VINHO = ?, DATA_EMB = ? WHERE ID_PRODUTO_FINAL = ?");
                    p6.setInt(1, newQtdCai);
                    p6.setString(2, c);
                    p6.setString(3, String.valueOf(d));
                    p6.setInt(4, idEditEmbala);
                    p6.executeQuery();

                    System.out.println("Embalamento alterado com sucesso!");
                    msg.alertaInfo("Embalamento alterado com sucesso", "Info!", "Embalamento alterado!");

                    idAvaliacao.setText("");
                    qtdCaixas.setText("");
                    tipoVinho.setText("");
                    datePickerEmbalamento.getEditor().clear();

                    ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
                }

            } else {
                System.out.println("Produto final nao encontrada!");
                msg.alertaAviso("Produto final nao encontrado!", "Erro!", "ID de produto final inválido!");

            }

        }


    }

}
