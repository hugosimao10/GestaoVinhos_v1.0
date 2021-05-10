package app.controller.avaliacao;

import app.entities.userID;
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

public class addAvaliacaoController {
    public Pane addAvaliacaoPane;
    public TextField numControlo;
    public TextField qtdProduzida;
    public Button btnConfirmAddAvaliacao;
    public Button btnCancelAddAvaliacao;
    public TextField qualidadeVinho;

    public void iniciar() {
        System.out.println("Está na area de adicionar avaliação!");

        Pattern pattern = Pattern.compile("^[0-9]*$");
        TextFormatter formatterControlo = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });
        TextFormatter formatterQualidade = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });


        numControlo.setTextFormatter(formatterControlo);
        qualidadeVinho.setTextFormatter(formatterQualidade);

        Pattern patternQtd = Pattern.compile("\\d*|\\d+\\.\\d*");
        TextFormatter formatterQtdProduzida = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return patternQtd.matcher(change.getControlNewText()).matches() ? change : null;
        });

        qtdProduzida.setTextFormatter(formatterQtdProduzida);

    }

    public void btnAddAvaliacaoAddClic(ActionEvent actionEvent) throws SQLException {

        String idContr = numControlo.getText();
        String qtdPr = qtdProduzida.getText();
        String qualVinho = qualidadeVinho.getText();
        int idEmpresa = userID.getId();


        if (idContr.isEmpty() || qtdPr.isEmpty() || qualVinho.isEmpty()) {

            System.out.println("Não podem ficar campos em branco");
            msg.alertaAviso("Não podem ficar campos vazios!", "Aviso!", "Campos vazios!");

        } else {

            int idContrInt = Integer.parseInt(idContr);
            double qtdProdInt = Double.parseDouble(qtdPr);
            int qualVinhoInt = Integer.parseInt(qualVinho);
            ;

            Connection c1 = Util.criarConexao();
            if (qualVinhoInt > 2 && qualVinhoInt <= 5) {


                PreparedStatement p4 = c1.prepareStatement("SELECT c.*, a.* FROM CONTROLO c, AVALIACAO a WHERE c.ID_CONTROLO = ? AND c.RESULTADO = 1");
                p4.setInt(1, idContrInt);

                ResultSet sss = p4.executeQuery();

                if (sss.next()) {
                    PreparedStatement p5 = c1.prepareStatement("INSERT INTO AVALIACAO(QTD_PRODUZIDA, QUALIDADE_VINHO) VALUES (?,?)");
                    p5.setDouble(1, qtdProdInt);
                    p5.setString(2, qualVinho);
                    p5.executeQuery();


                    PreparedStatement p8 = c1.prepareStatement("SELECT * FROM AVALIACAO WHERE QTD_PRODUZIDA = ? AND QUALIDADE_VINHO = ?");
                    p8.setDouble(1, qtdProdInt);
                    p8.setString(2, qualVinho);

                    ResultSet s1 = p8.executeQuery();

                    if (s1.next()) {

                        PreparedStatement p6 = c1.prepareStatement("UPDATE CONTROLO SET ID_AVALIACAO = ? WHERE ID_CONTROLO = ?");
                        p6.setInt(1, s1.getInt("ID_AVALIACAO"));
                        p6.setInt(2, idContrInt);
                        p6.executeQuery();

                        System.out.println("Avaliacao adicionada com sucesso, com resultado positivo");
                        msg.alertaInfo("Avaliacao adicionada com sucesso!", "Info!", "Avaliação bem sucedida!");

                        numControlo.setText("");
                        qtdProduzida.setText("");
                        qualidadeVinho.setText("");

                        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

                    } else {
                        System.out.println("ID AVALIACAO ASSOCIADO NAO ENCONTRADO (78, addAvaliacao)");
                    }


                } else {
                    System.out.println("O controlo inserido não existe OU o resultado é negativo!");
                    msg.alertaAviso("O controlo inserido não poder ser utilizado!", "Erro!", "Numero do controlo inexistente OU resutlado negativo!");

                }

            } else {
                System.out.println("O vinho não tem a qualidade minima desejada!");
                msg.alertaAviso("O vinho não tem a qualidade mínima desejada!", "Aviso!", "Qualidade fraca!");
            }


        }


    }

    public void btnAddAvaliacaoCancelarClic(ActionEvent actionEvent) {

        numControlo.setText("");
        qtdProduzida.setText("");
        qualidadeVinho.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
