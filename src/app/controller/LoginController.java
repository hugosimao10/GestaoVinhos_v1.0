package app.controller;

import app.error.msg;
import app.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController {
    public TextField userInserido;
    public PasswordField passInserida;


    //  login Funcionario

    public void butEntrarClic(ActionEvent actionEvent) throws SQLException, IOException {

        Connection conn = Util.criarConexao();

        String pass = this.passInserida.getText();
        String user = this.userInserido.getText();

        PreparedStatement pst = conn.prepareStatement("SELECT ID_FUNCIONARIO, TIPO_FUNCIONARIO, USERNAME, PW" +
                " FROM FUNCIONARIO WHERE USERNAME LIKE ? AND PW LIKE ?");

        pst.setString(1, user);
        pst.setString(2, pass);

        ResultSet rs = pst.executeQuery();

        if(rs.next()){

            System.out.println("Login com sucesso!");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/dashboard.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("EmpresaVinhos | Dashboard");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
            DashboardController dashboard = loader.getController();
            dashboard.iniciar();
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();



        }
        else {
            if (user.isEmpty()) {
                msg.alertaAviso("Por favor insira o username!", "Aviso!", "Username não pode estar vazio!");
            } else if (pass.isEmpty()) {
                msg.alertaAviso("Por favor insira a password!", "Aviso!", "Password não pode estar vazia!");
            }

            else {
                msg.alertaErro("Corrija o utilizador e/ou a password!", "Erro!", "Username e password não correspondem!");
            }
        }

    }



}
