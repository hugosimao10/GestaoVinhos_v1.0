package app.controller.funcionario;

import app.entities.userID;
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
import javafx.scene.image.Image;
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
        int conf = userID.getId();

        PreparedStatement pst = conn.prepareStatement("SELECT * FROM FUNCIONARIO WHERE USERNAME LIKE ? AND ID_EMPRESA = ?");

        pst.setString(1, userProcurar);
        pst.setInt(2, conf);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            int cod_postal = rs.getInt("COD_POSTAL");

            PreparedStatement psCp = conn.prepareStatement("SELECT * FROM COD_POSTAL WHERE ID_CODPOSTAL LIKE ?");
            psCp.setInt(1, cod_postal);
            ResultSet rsCP = psCp.executeQuery();

            if (rsCP.next()) {

                if (checkEditFunc.isSelected()) {

                    int idUserEdit = rs.getInt("ID_FUNCIONARIO");
                    String cargo = rs.getString("TIPO_FUNCIONARIO");
                    String nome = rs.getString("NOME");
                    String email = rs.getString("EMAIL");
                    String tlm = rs.getString("TLM");
                    int nPorta = rs.getInt("NPORTA");
                    String rua = rs.getString("RUA");
                    String codPostal = rsCP.getString("COD_POSTAL");
                    int empresa = rs.getInt("ID_EMPRESA");
                    String pw = rs.getString("PW");
                    String user = rs.getString("USERNAME");
                    int estado = rs.getInt("ESTADO");


                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/funcionario/editFuncionarioPane.fxml"));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Editar Funcionario");
                    stage.setScene(new Scene(root));
                    stage.setResizable(false);
                    stage.show();
                    stage.getIcons().add(new Image("/img/logo.png"));
                    editFuncionarioController edit = loader.getController();
                    edit.iniciar(idUserEdit, cargo, nome, email, tlm, nPorta, rua, codPostal, empresa, pw, user, estado);

                    ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
                } else {
                    System.out.println("Selecione a checbox para confirmar!");
                    msg.alertaAviso("Selecione a checkbox para confirmar!", "Aviso!", "Selecione a checkbox!");

                }
            }
        } else {
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
