package app;

import app.entities.Funcionario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    //começar a app com o login

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ui/login.fxml"));
        stage.setTitle("Login");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        //setTitleIcon.setIcon(stage);
        stage.show();
    }


/*
    // codigo de escolher data, meter dentro do botão

    DatePicker datepicker = new DatePicker(this);

      recebe_data_ini.setText(datepicker.setPickedDate());
*/
}
