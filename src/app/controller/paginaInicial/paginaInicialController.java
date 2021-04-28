package app.controller.paginaInicial;

import app.entities.userID;
import app.util.Util;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class paginaInicialController {
    public ImageView iconQuintas;
    public ImageView iconFuncionarios;
    public ImageView iconVindimas;
    public ImageView iconControlos;
    public Text numQuintas;
    public Text numFuncionarios;
    public Text numVindimasAtivas;
    public Text numControlos;
    public ImageView iconPlatacoes;
    public ImageView iconCastas;
    public ImageView iconPercControlos;
    public ImageView iconCaixas;
    public Text numPlantacoes;
    public Text numCastas;
    public Text percControlos;
    public Text numCaixas;
    public Button btnLogout;
    public Text bemVindoEmpresa;
    public Text bemVindoUser;

    public void iniciar() throws SQLException {
        System.out.println("Está na página Inicial!");

        bemVindoEmpresa.setText(userID.getNomeEmpresa());
        bemVindoUser.setText("Bem-vindo, " + userID.getUsername());
        int empresaLog = userID.getId();

        Connection c = Util.criarConexao();

        // NUM TOTAL QUINTAS

        PreparedStatement pst = c.prepareStatement("SELECT COUNT(*) AS TOTALQUINTA FROM QUINTA WHERE ID_EMPRESA = ?");
        pst.setInt(1, empresaLog);
        ResultSet s = pst.executeQuery();

        if(s.next()){
            numQuintas.setText(s.getString("TOTALQUINTA"));
        }
        else{
            System.out.println("Não foram encontradas quintas nesta empresa");
            numQuintas.setText("0");
        }

        // NUM TOTAL DE FUNCIONARIOS

        PreparedStatement pst1 = c.prepareStatement("SELECT COUNT(*) AS TOTALFUNC FROM FUNCIONARIO WHERE ID_EMPRESA = ?");
        pst1.setInt(1, empresaLog);
        ResultSet s2 = pst1.executeQuery();

        if(s2.next()){
            numFuncionarios.setText(s2.getString("TOTALFUNC"));
        }
        else{
            System.out.println("Não foram encontrados funcionários nesta empresa");
            numFuncionarios.setText("0");
        }

        // TOTAL PLANTAÇOES


        // TOTAL DE CASTAS


        // NUMERO VINDIMAS ATIVAS


        // CONTROLOS BEM SUCEDIDOS


        // PERCENTAGEM DE CONTROLOS BEM SUCEDIDOS


        // CAIXAS PRODUZIDAS NESTE ANO





    }

    public void butLogout(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/login/login.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

    }
}
