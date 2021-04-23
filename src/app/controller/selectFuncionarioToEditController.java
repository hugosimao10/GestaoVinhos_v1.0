package app.controller;

import app.error.msg;
import app.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class selectFuncionarioToEditController {
    public Pane funcionariosSelectEditPane;
    public TextField usernameEditFunc;
    public Button btnConfirmEditFunc;
    public Button btnCancelEditFunc;
    public CheckBox checkEditFunc;

    public void iniciar() {
        System.out.println("Está na area de inserir nome de funcionario a editar!");

    }


    public void butConfirmFuncEditClic(ActionEvent actionEvent) throws SQLException, IOException {

        Connection conn = Util.criarConexao();

        String userProcurar = usernameEditFunc.getText();

        PreparedStatement pst = conn.prepareStatement("SELECT * FROM FUNCIONARIO WHERE USERNAME LIKE ?");

        pst.setString(1, userProcurar);

        ResultSet rs = pst.executeQuery();

        if(rs.next()){

            if(checkEditFunc.isSelected()){

                int idUserEdit = rs.getInt("ID_FUNCIONARIO");
                String cargo = rs.getString("TIPO_FUNCIONARIO");
                String nome = rs.getString("NOME");
                String email = rs.getString("EMAIL");
                String tlm = rs.getString("TLM");
                int nPorta = rs.getInt("NPORTA");
                String rua = rs.getString("RUA");
                int cod_postal = rs.getInt("COD_POSTAL");
                int empresa = rs.getInt("ID_EMPRESA");
                String pw = rs.getString("PW");
                String user = rs.getString("USERNAME");
                int estado = rs.getInt("ESTADO");



                FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/editFuncionarioPane.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Editar Funcionario");
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();
                editFuncionarioController edit = loader.getController();
                edit.iniciar(idUserEdit, cargo, nome, email, tlm, nPorta, rua, cod_postal, empresa, pw, user, estado);

                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            }
            else{
                System.out.println("Selecione a checbox para confirmar!");
                msg.alertaAviso("Selecione a checkbox para confirmar!", "Aviso!", "Selecione a checkbox!");

            }



        }
        else {
            System.out.println("O funcionário a procurar não foi encontrado!");
            msg.alertaErro("O funcionário a procurar não foi encontrado!", "Erro!", "O user a procurar não foi encontrado!");

        }


    }

    public void butVoltarEditClic(ActionEvent actionEvent) {


        usernameEditFunc.setText("");
        checkEditFunc.setSelected(false);

        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
