package app.controller;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class editFuncionarioController {
    public ComboBox dropdownEditFunc;
    public TextField usernameEditFunc;
    public PasswordField pwdEditFunc;
    public TextField nomeEditFunc;
    public TextField emailEditFunc;
    public TextField tlmEditFunc;
    public TextField ruaEditFunc;
    public TextField portaEditFunc;
    public TextField cpEditFunc;
    public Button btnConfirmEditFunc;
    public Button btnCancelEditFunc;
    public Pane funcionariosEditPane;

    public void iniciar() {
        System.out.println("Está na area de editar funcionários!");


    }
}
