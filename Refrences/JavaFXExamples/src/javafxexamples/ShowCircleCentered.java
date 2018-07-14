package javafxexamples;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
public class ShowCircleCentered extends Application {
    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();
        Circle circle = new Circle();
        circle.centerXProperty().bind(pane.widthProperty().divide(2));
        circle.centerYProperty().bind(pane.heightProperty().divide(2));
        pane.widthProperty().addListener(e->{
            circle.centerXProperty().set(pane.getWidth()/3);
        });
        pane.heightProperty().addListener(e->{
            circle.centerYProperty().set(pane.getHeight()/3);
        });
        circle.setRadius(50);
        circle.setStroke(Color.BLACK);
        circle.setFill(Color.WHITE);
        pane.getChildren().add(circle); 
        Scene scene = new Scene(pane, 200, 200);
        primaryStage.setTitle("ShowCircleCentered"); 
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }
    public static void main(String[] args) {
        launch(args);
    }
}