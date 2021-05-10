package app.controller.vindima;

import app.entities.userID;
import app.error.msg;
import app.util.Util;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.Pane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class concluirVindimaController {
    public Pane concluirVindimaPane;
    public TextField numVindima;
    public Button btnConfirmConcluirVindima;
    public Button btnCancelConcluirVindima;
    public CheckBox checkConcluirVindima;
    public TextField qtdVindimada;

    public void iniciar() throws SQLException {
        System.out.println("Está na area de concluir Vindimas!");

        Pattern pattern = Pattern.compile("^[0-9]*$");
        TextFormatter formatter = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return pattern.matcher(change.getControlNewText()).matches() ? change : null;
        });

        numVindima.setTextFormatter(formatter);

        Pattern patternQtd = Pattern.compile("\\d*|\\d+\\.\\d*");
        TextFormatter formatterQtdVindimada = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return patternQtd.matcher(change.getControlNewText()).matches() ? change : null;
        });

        qtdVindimada.setTextFormatter(formatterQtdVindimada);
    }

    public void btnConcluirVindimaClic(ActionEvent actionEvent) throws SQLException {

        String numVind = numVindima.getText();
        String QtdVindimada = qtdVindimada.getText();


        // VER SE O CAMPO ESTA VAZIO

        if (numVind.isEmpty() || QtdVindimada.isEmpty()) {
            System.out.println("Não pode haver campos vazios!");
            msg.alertaAviso("Não pode haver campos vazios!", "Aviso!", "Campo não pode ficar vazio!");
        } else {

            int numVindInt = Integer.parseInt(numVind);
            int qtdVinInt = Integer.parseInt(QtdVindimada);
            if (qtdVinInt <= 0) {
                msg.alertaAviso("A quantidade vindimada deve ser superior a 0!", "Aviso!", "Quantidade vindimada inválida!");
            } else {


                // VER SE A CHECKBOX ESTÁ SELECIONADA

                if (checkConcluirVindima.isSelected()) {

                    int a = userID.getId();

                    Connection conn = Util.criarConexao();

                    PreparedStatement pst = conn.prepareStatement("SELECT p.*, f.* FROM PLANTACAO_VINDIMA p, FUNCIONARIO f WHERE p.ID_PLANT_VINDIMA = ? AND f.ID_FUNCIONARIO = p.ID_FUNCIONARIO AND f.ID_EMPRESA = ?");

                    pst.setInt(1, numVindInt);
                    pst.setInt(2, a);

                    ResultSet rs = pst.executeQuery();

                    // VER SE FOI ENCONTRADO O ID, E SE FOI ENCONTRADO, ENTAO PASSA O CAMPO ATIVO A 0 (QUINTA NÃO FAZ MAIS PARTE DA EMPRESA)

                    if (rs.next()) {

                        PreparedStatement pst1 = conn.prepareStatement("UPDATE PLANTACAO_VINDIMA SET QTD_VINDIMADA = ? WHERE ID_PLANT_VINDIMA = ?");
                        pst1.setInt(1, qtdVinInt);
                        pst1.setInt(2, numVindInt);
                        pst1.executeQuery();
                        System.out.println("A vindima foi finalizada com sucesso!");
                        msg.alertaInfo("A vindima foi finalizada com sucesso!", "Sucesso!", "Vindima finalizada!");
                        numVindima.setText("");
                        qtdVindimada.setText("");

                        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

                    } else {
                        System.out.println("A vindima nao foi encontrada!");
                        msg.alertaErro("A vindima não foi encontrado!", "Erro!", "Vindima não existe!");

                    }

                } else {
                    System.out.println("Por favor, selecione a checkbox para confirmar a remoção da vindima!");
                    msg.alertaAviso("Por favor, selecione a checkbox para confirmar a remoção da vindima!)", "Aviso!", "Confirme a checkbox!");
                }
            }
        }
    }

    public void btnConcluirVindimaCancelarClic(ActionEvent actionEvent) {

        numVindima.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
