package dev;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Menu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Pane root = FXMLLoader.load(getClass().getResource("../fxml/menu.fxml"));
        Scene scene = new Scene(root);
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/image/asteroid.png"))); ikonka
        stage.setScene(scene);
        stage.setTitle("The Mad Otter");
        stage.show();
    }

}
