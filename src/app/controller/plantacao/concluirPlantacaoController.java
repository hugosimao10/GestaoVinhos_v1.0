package app.controller.plantacao;

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

public class concluirPlantacaoController {
    public TextField numPlantacao;
    public Button btnConfirmConcluirPlant;
    public Pane conclurPlantacoesPane;
    public Button btnCancelConcluirPlant;
    public CheckBox checkConcluirPlant;

    public void iniciar() {
        System.out.println("Está na area de concluir plantações!");

        Pattern patternInt = Pattern.compile("^[0-9]*$");
        TextFormatter formatterPlant = new TextFormatter((UnaryOperator<TextFormatter.Change>) change -> {
            return patternInt.matcher(change.getControlNewText()).matches() ? change : null;
        });
        numPlantacao.setTextFormatter(formatterPlant);
    }

    public void btnConcluirPlantClic(ActionEvent actionEvent) throws SQLException {


        String pla = numPlantacao.getText();
        int conf1 = userID.getId();

        Connection conn = Util.criarConexao();

        // VER SE O USERNAME ESTA VAZIO

        if (pla.isEmpty()) {
            System.out.println("Por favor, preencha a plantacao!");
            msg.alertaAviso("Por favor insira a plantacao que pretende remover!", "Aviso!", "Campo não pode estar vazio!");
        } else {

            int plantRem = Integer.parseInt(pla);

            PreparedStatement pst = conn.prepareStatement("SELECT p.*, q.ID_EMPRESA FROM PLANTACAO p, QUINTA q WHERE p.ID_PLANTACAO = ? AND q.ID_EMPRESA = ?");

            pst.setInt(1, plantRem);
            pst.setInt(2, conf1);

            ResultSet rs = pst.executeQuery();

            // VER SE A CHECKBOX ESTÁ SELECIONADA

            if (checkConcluirPlant.isSelected()) {

                // VER SE FOI ENCONTRADO O USERNAME, E SE FOI ENCONTRADO, ENTAO PASSA O ESTADO A 0 (NÃO TRABALHA MAIS NA EMPRESA)

                if (rs.next()) {


                    PreparedStatement pst1 = conn.prepareStatement("UPDATE PLANTACAO SET ESTADO = 0 WHERE ID_PLANTACAO = ?");
                    pst1.setInt(1, plantRem);
                    pst1.executeQuery();
                    System.out.println("Plantação concluída com sucesso!");
                    msg.alertaInfo("Plantação concluída com sucesso!", "Sucesso!", "Sucesso!");
                    numPlantacao.setText("");

                    ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

                } else {
                    System.out.println("A plantacao nao foi encontrada!");
                    msg.alertaErro("A plantacao nao foi encontrada!", "Erro!", "Plantacao não existe!");
                }

            } else {
                System.out.println("Por favor, selecione a checkbox para confirmar que pretende concluir!");
                msg.alertaAviso("Por favor selecione a checkbox!", "Aviso!", "Confirme que pretende concluir!");
            }


        }

    }

    public void btnConcluirPlantCancelarClic(ActionEvent actionEvent) {

        numPlantacao.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
