package app.controller;

import app.entities.userID;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

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
}
