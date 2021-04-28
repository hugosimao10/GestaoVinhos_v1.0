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
    public Text percContrloSuccess;

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

        PreparedStatement pst2 = c.prepareStatement("SELECT p.*, f.* FROM PLANTACAO p, FUNCIONARIO f WHERE f.ID_EMPRESA = ? AND f.ID_FUNCIONARIO = p.ID_FUNCIONARIO AND p.ESTADO = 1");
        pst2.setInt(1, empresaLog);
        ResultSet S3 = pst2.executeQuery();

        int n = 0;

        while (S3.next()){

            ++n;

        }
        numPlantacoes.setText(String.valueOf(n));


        // TOTAL DE CASTAS

        PreparedStatement pst3 = c.prepareStatement("SELECT COUNT(*) AS totalcasta FROM CASTA");
        ResultSet S4 = pst3.executeQuery();

        while (S4.next()){

            numCastas.setText(S4.getString("totalcasta"));

        }


        // NUMERO VINDIMAS ATIVAS

        PreparedStatement pst4 = c.prepareStatement("SELECT pv.*, f.* FROM PLANTACAO_VINDIMA pv, FUNCIONARIO f WHERE f.ID_EMPRESA = ? AND f.ID_FUNCIONARIO = pv.ID_FUNCIONARIO");
        pst4.setInt(1, empresaLog);
        ResultSet ss = pst4.executeQuery();

        int n1 = 0;

        while (ss.next()){

            ++n1;

        }
        numVindimasAtivas.setText(String.valueOf(n));


        // CONTROLOS BEM SUCEDIDOS

        PreparedStatement pst5 = c.prepareStatement("SELECT c.*, f.* FROM CONTROLO c, FUNCIONARIO f WHERE f.ID_EMPRESA = ? AND c.ID_FUNCIONARIO = f.ID_FUNCIONARIO AND c.RESULTADO = 1");
        pst5.setInt(1, empresaLog);
        ResultSet as = pst5.executeQuery();

        int n44 = 0;

        while (as.next()){

            ++n44;

        }
        numControlos.setText(String.valueOf(n));


        // PERCENTAGEM DE CONTROLOS BEM SUCEDIDOS

        PreparedStatement pst10 = c.prepareStatement("SELECT c.*, f.* FROM CONTROLO c, FUNCIONARIO f WHERE f.ID_EMPRESA = ? AND c.ID_FUNCIONARIO = f.ID_FUNCIONARIO AND c.RESULTADO = 1");
        pst10.setInt(1, empresaLog);
        ResultSet as1 = pst10.executeQuery();

        int n30 = 0;

        while (as1.next()){

            ++n30;

        }

        PreparedStatement pst11 = c.prepareStatement("SELECT c.*, f.* FROM CONTROLO c, FUNCIONARIO f WHERE f.ID_EMPRESA = ? AND c.ID_FUNCIONARIO = f.ID_FUNCIONARIO");
        pst11.setInt(1, empresaLog);
        ResultSet as12 = pst11.executeQuery();

        int num = 0;

        while(as12.next()){

            ++num;
        }

        if(num ==0){
            percContrloSuccess.setText("0");

        }
        else {
            float perc = (n30 * 100) / num;


            percContrloSuccess.setText(String.valueOf(perc));
        }

        // CAIXAS PRODUZIDAS NESTE ANO

        PreparedStatement pst44 = c.prepareStatement("SELECT f.*, c.*, a.*, e.* FROM FUNCIONARIO f, CONTROLO c, AVALIACAO a, PRODUTOFINAL e " +
                "WHERE e.ID_PRODUTO_FINAL = a.ID_PRODUTO_FINAL AND a.ID_AVALIACAO = c.ID_AVALIACAO AND c.ID_FUNCIONARIO = f.ID_FUNCIONARIO AND " +
                "f.ID_EMPRESA = ?");
        pst44.setInt(1, empresaLog);

        ResultSet caixas = pst44.executeQuery();

        int totalcaixa = 0;

        while(caixas.next()){
            int recebe = caixas.getInt("QTD_CAIXAS");
            totalcaixa+=recebe;
        }


        numCaixas.setText(String.valueOf(totalcaixa));
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
