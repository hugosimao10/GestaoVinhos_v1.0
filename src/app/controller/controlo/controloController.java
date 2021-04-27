package app.controller.controlo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class controloController implements Initializable {
    @FXML
    public TableView table;
    public Pane controlosPane;
    @FXML
    public TableColumn colNum;
    @FXML
    public TableColumn colAcucar;
    @FXML
    public TableColumn colTemperatura;
    @FXML
    public TableColumn colData;
    @FXML
    public TableColumn colVindima;
    @FXML
    public TableColumn colFunc;
    public Button btnAddControlo;
    public Button btnEditControlo;

    public void iniciar() throws SQLException {
        System.out.println("Está na area de listar Controlos!");


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }

    public void btnAddControloClic(ActionEvent actionEvent) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/controlo/addControloPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Adicionar Controlos");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        addControloController add = loader.getController();
        add.iniciar();

    }

    public void btnEditControloClic(ActionEvent actionEvent) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/controlo/selectControloToEditPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Editar Controlos");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        selectControloToEditController add = loader.getController();
        add.iniciar();

    }


}
