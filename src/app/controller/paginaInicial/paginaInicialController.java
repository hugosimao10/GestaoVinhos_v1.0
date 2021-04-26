package app.controller.paginaInicial;

import app.entities.userID;
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

    public void iniciar() {
        System.out.println("Está na página Inicial!");

        bemVindoEmpresa.setText(userID.getNomeEmpresa());
        bemVindoUser.setText("Bem-vindo, " + userID.getUsername());




    }

    public void butLogout(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/login/login.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

    }
}
