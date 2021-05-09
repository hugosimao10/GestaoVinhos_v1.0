package app.controller.dashboard;

import app.controller.avaliacao.avaliacoesController;
import app.controller.controlo.controloController;
import app.controller.embalamento.embalamentoController;
import app.controller.funcionario.funcionarioController;
import app.controller.paginaInicial.paginaInicialController;
import app.controller.plantacao.plantacoesController;
import app.controller.quinta.quintaController;
import app.controller.vindima.vindimaController;
import app.entities.userID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.sql.SQLException;

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

    public void iniciar(int idEmpresa, int idLog1, String username, String nomeEmpresa, int cargoPerm) throws IOException, SQLException {

        // PASSAR O ID DA EMPRESA PARA UMA VARIAVEL GLOBAL

        userID.setId(idEmpresa);
        userID.setIdUser(idLog1);
        userID.setUsername(username);
        userID.setNomeEmpresa(nomeEmpresa);
        userID.setCargoPermissao(cargoPerm);

        System.out.println(cargoPerm);

        if (cargoPerm == 21) {
            btnQuintas.setDisable(true);
            btnFuncionarios.setDisable(true);
            btnControlos.setDisable(true);
            btnAvaliacoes.setDisable(true);
            btnEmbalamentos.setDisable(true);

        } else if (cargoPerm == 22) {

            btnQuintas.setDisable(true);
            btnFuncionarios.setDisable(true);
            btnPlantacoes.setDisable(true);
            btnVindimas.setDisable(true);
            btnEmbalamentos.setDisable(true);

        } else if (cargoPerm == 23) {

            btnQuintas.setDisable(true);
            btnFuncionarios.setDisable(true);
            btnPlantacoes.setDisable(true);
            btnVindimas.setDisable(true);
            btnControlos.setDisable(true);
            btnEmbalamentos.setDisable(true);
        }


        btnPaginaInicial.fire();

    }

    // BOTAO QUE APRESENTA A PAGINA INICIAL AO UTILIZADOR

    @FXML
    private void openPaginaInicial() throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/paginaInicial/paginaInicialPane.fxml"));
        Parent root = loader.load();
        paginaInicialController controller = loader.getController();
        paneToChange.setCenter(root);
        controller.iniciar();

    }

    // BOTAO QUE LEVA O UTILIZADOR PARA A AREA DE QUINTAS

    @FXML
    public void btnQuintasClic(ActionEvent actionEvent) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/quinta/quintasPane.fxml"));
        Parent root = loader.load();
        quintaController controller = loader.getController();
        paneToChange.setCenter(root);
        controller.iniciar();

    }

    //BOTAO QUE LEVA O UTILIZADOR PARA A AREA DE FUNCIONARIOS

    @FXML
    public void btnFuncionarioClic(ActionEvent actionEvent) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/funcionario/funcionariosPane.fxml"));
        Parent root = loader.load();
        funcionarioController controller = loader.getController();
        paneToChange.setCenter(root);
        controller.iniciar();

    }

    // BOTAO QUE LEVA O UTILIZADOR PARA A AREA DE PLANTACOES

    @FXML
    public void btnPlantacaoClic(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/plantacao/plantacoesPane.fxml"));
        Parent root = loader.load();
        plantacoesController controller = loader.getController();
        paneToChange.setCenter(root);
        controller.iniciar();

    }

    // BOTAO QUE LEVA O UTILIZADOR PARA A AREA DE VINDIMAS

    @FXML
    public void btnVindimaClic(ActionEvent actionEvent) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/vindima/vindimasPane.fxml"));
        Parent root = loader.load();
        vindimaController controller = loader.getController();
        paneToChange.setCenter(root);
        controller.iniciar();


    }

    // BOTAO QUE LEVA O UTILZIADOR PARA A AREA DE CONTROLOS

    @FXML
    public void btnControloClic(ActionEvent actionEvent) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/controlo/controlosPane.fxml"));
        Parent root = loader.load();
        controloController controller = loader.getController();
        paneToChange.setCenter(root);
        controller.iniciar();

    }

    // BOTAO QUE LEVA O UTILIZADOR PARA A AREA DE AVALIACOES

    @FXML
    public void btnAvaliacoesClic(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/avaliacao/avaliacoesPane.fxml"));
        Parent root = loader.load();
        avaliacoesController controller = loader.getController();
        paneToChange.setCenter(root);
        controller.iniciar();

    }

    // BOTAO QUE LEVA O UTILZIADOR PARA A AREA DE EMBALAMENTOS

    @FXML
    public void btnEmbalamentosClic(ActionEvent actionEvent) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/ui/embalamento/embalamentoPane.fxml"));
        Parent root = loader.load();
        embalamentoController controller = loader.getController();
        paneToChange.setCenter(root);
        controller.iniciar();

    }
}
