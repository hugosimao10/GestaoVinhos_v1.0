package app.controller.embalamento;

import javafx.event.ActionEvent;
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

public class embalamentoController implements Initializable {
    public Pane embalamentosPane;
    public TableView table;
    public TableColumn colNum;
    public TableColumn colQtdCaixas;
    public TableColumn colTipoVinho;
    public TableColumn colData;
    public Button btnAddEmbalamento;
    public Button btnEditEmbalamento;
    public TableColumn numAvaEmbalamento;

    public void iniciar() throws SQLException {
        System.out.println("Está na area de listar embalamentos!");


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }

    public void btnAddEmbalamentoClic(ActionEvent actionEvent) throws SQLException, IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/embalamento/addEmbalamentoPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Adicionar Embalamentos");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        addEmbalamentoController add = loader.getController();
        add.iniciar();
    }

    public void btnEditEmbalamentoClic(ActionEvent actionEvent) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/embalamento/selectEmbalamentoToEditPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Editar Embalamentos");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        selectEmbalamentoToEditController add = loader.getController();
        add.iniciar();
    }


}
