package app.controller.quinta;

import app.entities.userID;
import app.error.msg;
import app.util.Util;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class removeQuintasController {
    public Pane quintasRemovePane;
    public TextField numRemoveQuinta;
    public Button btnConfirmRemoveFunc;
    public Button btnCancelRemoveQuinta;
    public CheckBox checkRemoveQuinta;

    public void iniciar() throws SQLException {
        System.out.println("Está na area de remover quintas!");

    }

    public void btnRemoverFuncRemoveClic(ActionEvent actionEvent) throws SQLException {

        String idQui =  numRemoveQuinta.getText();


        // VER SE O CAMPO ESTA VAZIO

        if(idQui.isEmpty()){
            System.out.println("Por favor, preencha o número da Quinta!");
                msg.alertaAviso("Por favor insira o número da quinta que pretende remover!", "Aviso!", "Campo não pode ficar vazio!");
        }
        else{

            // VER SE A CHECKBOX ESTÁ SELECIONADA

            if(checkRemoveQuinta.isSelected()){

                int i = Integer.parseInt(idQui);
                int a = userID.getId();

                Connection conn = Util.criarConexao();

                PreparedStatement pst = conn.prepareStatement("SELECT * FROM QUINTA WHERE ID_QUINTA = ? AND ID_EMPRESA = ?");

                pst.setInt(1, i);
                pst.setInt(2, a);

                ResultSet rs = pst.executeQuery();

                // VER SE FOI ENCONTRADO O ID, E SE FOI ENCONTRADO, ENTAO PASSA O CAMPO ATIVO A 0 (QUINTA NÃO FAZ MAIS PARTE DA EMPRESA)

                if(rs.next()){

                    PreparedStatement pst1 = conn.prepareStatement("UPDATE QUINTA SET ATIVA = 0 WHERE ID_QUINTA = ?");
                    pst1.setInt(1,i);
                    pst1.executeQuery();
                    System.out.println("A quinta foi desativada com sucesso!");
                    msg.alertaInfo("A quinta foi desativada com sucesso!", "Sucesso!", "Quinta desativada!");
                    numRemoveQuinta.setText("");

                }
                else{
                    System.out.println("A quinta nao foi encontrada!");
                    msg.alertaErro("A quinta não foi encontrado!", "Erro!", "Quinta não existe!");
                }

            }
            else{
                System.out.println("Por favor, selecione a checkbox para confirmar a remoção da quinta!");
                msg.alertaAviso("Por favor, selecione a checkbox para confirmar a remoção da quinta!)", "Aviso!", "Confirme a checkbox!");
            }



        }



    }

    public void btnRemoveQuintaCancelarClic(ActionEvent actionEvent) {

        numRemoveQuinta.setText("");

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

    }
}
