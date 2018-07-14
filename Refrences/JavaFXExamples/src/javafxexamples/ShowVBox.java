package javafxexamples;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class ShowVBox extends Application {
    @Override
    public void start(Stage primaryStage) {
        VBox vBox = new VBox(15);
        vBox.getChildren().add(new Label("Courses"));
        Label[] courses = {new Label("CSE114"), 
            new Label("CSE214"),new Label("CSE219"),new Label("CSE308")
        };
        for (Label course: courses) {
            vBox.getChildren().add(course);
        }
        Scene scene = new Scene(vBox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args)
    {
        launch(args);
    }
}