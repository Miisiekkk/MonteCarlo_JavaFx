package sample;

import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;


/**
 * Created by misiek on 2017-04-08.
 */

public class Controller {

    @FXML
    private Canvas canvas;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private TextField textField;

    @FXML
    private Label resultLabel;


    private DrawerTask task;

    @FXML
    private void handleButtonStop() {
        if (task != null) {
            task.cancel();
        }
    }

    @FXML
    private void handleButtonStart() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight()); //robi sie czorny

        double input;

        if (textField.getText().length() > 0) {
            try {
                input = Double.parseDouble(textField.getText()); //sciaga doubla
                if (input > 0) {
                    task = new DrawerTask(gc, input);                   //rysowanko
                    progressBar.progressProperty().bind(task.progressProperty());   //podpiety progressbar
                    task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {      //przekazanie danych do watku
                        @Override
                        public void handle(WorkerStateEvent event) {
                            resultLabel.setText(Double.toString((Double) task.getValue())); //wynik hitów
                        }
                    });
                    new Thread(task).start();                       //podpiecie watku
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("ERROR");
                    alert.setHeaderText("Zmień Wartość!");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Zmień Wartość!");
                alert.showAndWait();
            }
        }
    }
}