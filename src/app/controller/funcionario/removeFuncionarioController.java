package app.controller.funcionario;

import app.entities.userID;
import app.error.msg;
import app.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class removeFuncionarioController {
    public Pane funcionariosRemovePane;
    public TextField usernameRemoveFunc;
    public Button btnConfirmRemoveFunc;
    public Button btnCancelRemoveFunc;
    public CheckBox checkRemoveFunc;

    public void iniciar() {
        System.out.println("Está na area de remover funcionários!");


    }

    // BOTAO DE REMOVER FUNCIONARIO NA PANE DE REMOVER FUNCIONARIO
    @FXML
    public void btnRemoverFuncRemoveClic(ActionEvent actionEvent) throws IOException, SQLException {

        String user = usernameRemoveFunc.getText();
        int conf1 = userID.getId();

        Connection conn = Util.criarConexao();

        PreparedStatement pst = conn.prepareStatement("SELECT * FROM FUNCIONARIO WHERE USERNAME LIKE ? AND ID_EMPRESA = ?");

        pst.setString(1, user);
        pst.setInt(2, conf1);

        ResultSet rs = pst.executeQuery();

        // VER SE O USERNAME ESTA VAZIO

        if (user.isEmpty()) {
            System.out.println("Por favor, preencha o funcionário!");
            msg.alertaAviso("Por favor insira o funcionário que pretende remover!", "Aviso!", "Funcionário não pode estar vazio!");
        } else {

            // VER SE A CHECKBOX ESTÁ SELECIONADA

            if (checkRemoveFunc.isSelected()) {

                // VER SE FOI ENCONTRADO O USERNAME, E SE FOI ENCONTRADO, ENTAO PASSA O ESTADO A 0 (NÃO TRABALHA MAIS NA EMPRESA)

                if (rs.next()) {


                    PreparedStatement pst1 = conn.prepareStatement("UPDATE FUNCIONARIO SET ESTADO = 0 WHERE USERNAME = ?");
                    pst1.setString(1, user);
                    pst1.executeQuery();
                    System.out.println("O user foi removido com sucesso!");
                    msg.alertaInfo("O funcionário foi removido com sucesso!", "Sucesso!", "Funcionário encontrado!");
                    usernameRemoveFunc.setText("");

                    ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

                } else {
                    System.out.println("O funcionário nao foi encontrado!");
                    msg.alertaErro("O funcionário não foi encontrado!", "Erro!", "Funcionário não existe!");
                }

            } else {
                System.out.println("Por favor, selecione a checkbox para confirmar que pretende remover!");
                msg.alertaAviso("Por favor selecione a checkbox!", "Aviso!", "Confirme que pretende remover!");
            }


        }


    }

    // BOTAO DE CANCELAR NA PANE DE REMOVER FUNCIONARIO
    @FXML
    public void btnRemoveFuncCancelarClic(ActionEvent actionEvent) throws IOException {

        usernameRemoveFunc.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
