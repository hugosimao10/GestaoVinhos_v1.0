package app.controller.avaliacao;

import app.error.msg;
import app.util.Util;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Pane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class editAvaliacaoController {
    public Pane editAvaliacaoPane;
    public TextField numControlo;
    public TextField qtdProduzida;
    public Button btnConfirmEditAvaliacao;
    public Button btnCancelEditAvaliacao;
    public TextField qualidadeVinho;
    public int guardaIdEditAvaliacao;

    public void iniciar(int idEdit, int idContr, int qtdProd, String qualVinho) {
        System.out.println("Está na area de editar avaliação!");

        numControlo.setText(String.valueOf(idContr));
        qtdProduzida.setText(String.valueOf(qtdProd));
        qualidadeVinho.setText(qualVinho);
        guardaIdEditAvaliacao = idEdit;

        Pattern patternQtd = Pattern.compile("\\d*|\\d+\\.\\d*");
        TextFormatter formatterQtdProduzida = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return patternQtd.matcher(change.getControlNewText()).matches() ? change : null;
        });

        qtdProduzida.setTextFormatter(formatterQtdProduzida);
    }

    public void btnEditAvaliacaoAddClic(ActionEvent actionEvent) throws SQLException {

        String a = numControlo.getText();
        String b = qtdProduzida.getText();
        String c = qualidadeVinho.getText();

        if (a.isEmpty() || b.isEmpty() || c.isEmpty()) {
            System.out.println("Não podem ficar campos em branco");
            msg.alertaAviso("Não podem ficar campos vazios!", "Aviso!", "Campos vazios!");

        } else {

            int newContr = Integer.parseInt(a);
            double newQtdPr = Double.parseDouble(b);
            int newQualVinho = Integer.parseInt(c);

            Connection c1 = Util.criarConexao();
            if (newQualVinho > 2 && newQualVinho <= 5) {


                PreparedStatement p4 = c1.prepareStatement("SELECT c.*, a.* FROM CONTROLO c, AVALIACAO a WHERE c.ID_CONTROLO = ? AND c.RESULTADO = 1");
                p4.setInt(1, newContr);

                ResultSet sss = p4.executeQuery();
                int bb = guardaIdEditAvaliacao;

                if (sss.next()) {

                    PreparedStatement p6 = c1.prepareStatement("UPDATE AVALIACAO SET QTD_PRODUZIDA = ?, QUALIDADE_VINHO = ? WHERE ID_AVALIACAO = ?");
                    p6.setDouble(1, newQtdPr);
                    p6.setString(2, c);
                    p6.setInt(3, bb);
                    p6.executeQuery();

                    System.out.println("Avaliacao alterada com sucesso");
                    msg.alertaInfo("Avaliacao alterada com sucesso!", "Info!", "Avaliação alterada!");

                    numControlo.setText("");
                    qtdProduzida.setText("");
                    qualidadeVinho.setText("");

                    ((Node) (actionEvent.getSource())).getScene().getWindow().hide();


                } else {
                    System.out.println("O controlo inserido não existe OU o resultado é negativo!");
                    msg.alertaAviso("O controlo inserido não poder ser utilizado!", "Erro!", "Numero do controlo inexistente OU resutlado negativo!");

                }


            } else {
                System.out.println("O vinho não tem a qualidade minima desejada!");
                msg.alertaAviso("O vinho não tem a qualidade mínima desejada!", "Aviso!", "Qualidade fraca!");
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            }


        }


    }

    public void btnEditAvaliacaoCancelarClic(ActionEvent actionEvent) {

        numControlo.setText("");
        qtdProduzida.setText("");
        qualidadeVinho.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
