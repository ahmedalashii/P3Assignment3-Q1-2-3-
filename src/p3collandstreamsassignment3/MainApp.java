package p3collandstreamsassignment3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane paneTextArea = FXMLLoader.load(getClass().getResource("TextAreaPane.fxml"));
        Scene scene = new Scene(paneTextArea);
        primaryStage.setTitle("Streams Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
