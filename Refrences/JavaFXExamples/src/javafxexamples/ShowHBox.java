package javafxexamples;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
public class ShowHBox extends Application {
    @Override
    public void start(Stage primaryStage) {
        HBox hBox = new HBox(15);
        hBox.getChildren().add(new Label("Courses"));
        Label[] courses = {new Label("CSE114"), 
            new Label("CSE214"),new Label("CSE219"),new Label("CSE308")
        };
        for (Label course: courses) {
            hBox.getChildren().add(course);
        }
        Scene scene = new Scene(hBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args)
    {
        launch(args);
    }
}