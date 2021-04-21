package app;

import app.controller.DashboardController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.net.URL;

/**
 * The type Fxml loader.
 */
public class FxmlLoader {

    private Pane view;

    /**
     * Gets page.
     *
     * @param fileName the file name
     * @return the page
     */
    public Pane getPage(String fileName) {

        try {
            URL fileUrl = DashboardController.class.getResource("/app/ui/" + fileName + ".fxml");
            if (fileUrl == null) {
                throw new java.io.FileNotFoundException("Ficheiro FXML não encontrado!");
            }

            new FXMLLoader();
            view = FXMLLoader.load(fileUrl);

        } catch (Exception e) {
            System.out.println("Erro na página " + fileName + ":" + e.getMessage());
        }

        return view;
    }
}