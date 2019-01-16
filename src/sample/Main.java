package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

/**
 * Created by misiek on 2017-04-08.
 */

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Batman 'n stuff");
        primaryStage.setScene(new Scene(root, 1004, 593));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}