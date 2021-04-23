package app.controller;

import app.userLogado.userID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class DashboardController {
    public Button btnQuintas;
    public Button btnFuncionarios;
    public Button btnPlantacoes;
    public Button btnVindimas;
    public Button btnControlos;
    public Button btnAvaliacoes;
    public Button btnEmbalamentos;
    public Button btnPaginaInicial;
    public BorderPane paneToChange;

    // FUNCAO QUE ATIVA O BOTAO DA PAGINA INICIAL

    public void iniciar(int idEmpresa, int idLog1,String username, String nomeEmpresa) throws IOException {

    // PASSAR O ID DA EMPRESA PARA UMA VARIAVEL GLOBAL

        userID.setId(idEmpresa);
        userID.setIdUser(idLog1);
        userID.setUsername(username);
        userID.setNomeEmpresa(nomeEmpresa);

        btnPaginaInicial.fire();

    }

    // BOTAO QUE APRESENTA A PAGINA INICIAL AO UTILIZADOR

    @FXML
    private void openPaginaInicial() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/paginaInicialPane.fxml"));
        Parent root = loader.load();
        paginaInicialController controller = loader.getController();
        paneToChange.setCenter(root);
        controller.iniciar();

    }

    // BOTAO QUE LEVA O UTILIZADOR PARA A AREA DE QUINTAS

    @FXML
    public void btnQuintasClic(ActionEvent actionEvent) throws IOException{




    }

    //BOTAO QUE LEVA O UTILIZADOR PARA A AREA DE FUNCIONARIOS

    @FXML
    public void btnFuncionarioClic(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ui/funcionariosPane.fxml"));
        Parent root = loader.load();
        funcionarioController controller = loader.getController();
        paneToChange.setCenter(root);
        controller.iniciar();

    }

    // BOTAO QUE LEVA O UTILIZADOR PARA A AREA DE PLANTACOES

    @FXML
    public void btnPlantacaoClic(ActionEvent actionEvent) throws IOException{



    }

    // BOTAO QUE LEVA O UTILIZADOR PARA A AREA DE VINDIMAS

    @FXML
    public void btnVindimaClic(ActionEvent actionEvent) throws IOException{



    }

    // BOTAO QUE LEVA O UTILZIADOR PARA A AREA DE CONTROLOS

    @FXML
    public void btnControloClic(ActionEvent actionEvent) throws IOException{



    }

    // BOTAO QUE LEVA O UTILIZADOR PARA A AREA DE AVALIACOES

    @FXML
    public void btnAvaliacoesClic(ActionEvent actionEvent) throws IOException{



    }

    // BOTAO QUE LEVA O UTILZIADOR PARA A AREA DE EMBALAMENTOS

    @FXML
    public void btnEmbalamentosClic(ActionEvent actionEvent) throws IOException{



    }
}
