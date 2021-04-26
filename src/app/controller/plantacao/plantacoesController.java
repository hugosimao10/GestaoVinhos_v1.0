package app.controller.plantacao;

import app.controller.controlo.addCastaController;
import app.controller.quinta.selectQuintaToEditController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class plantacoesController {
    public Pane plantacoesPane;
    public TableView tablePlantacoes;
    public Button btnAddPlantacao;
    public Button btnEditPlantacao;
    public Button btnConcluirPlantacao;
    public TableView tableCastas;
    public TableColumn tabelCastas;
    public Button btnAddCasta;
    public Button btnRemoveCasta;

    public void iniciar() {
        System.out.println("Está na area de listar plantações!");








    }

    public void btnAddPlantClic(ActionEvent actionEvent) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/plantacao/addPlantacaoPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Adicionar Plantacao");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        addPlantacaoController add = loader.getController();
        add.iniciar();
    }

    public void btnConcluirPlantClic(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/plantacao/concluirPlantacaoPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Concluir Plantação");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        concluirPlantacaoController c = loader.getController();
        c.iniciar();
    }

    public void btnEditPlantClic(ActionEvent actionEvent) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/plantacao/selectPlantacaoToEditPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Editar Plantação");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        selectPlantToEditController c = loader.getController();
        c.iniciar();
    }

    public void btnAddCastaClic(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/plantacao/addCastaPane.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Adicionar Casta");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
        addCastaController add = loader.getController();
        add.iniciar();
    }

}
