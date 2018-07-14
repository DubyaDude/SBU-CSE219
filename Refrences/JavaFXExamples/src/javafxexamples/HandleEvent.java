package javafxexamples;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author McKillaGorilla
 */
public class HandleEvent extends Application
{
    @Override
    public void start(Stage primaryStage)
    {
        HBox pane = new HBox(10);
        Button btOK = new Button("OK");
        Button btCancel = new Button("Cancel");
        OKHandler handler1 = new OKHandler();
        btOK.setOnAction(handler1);
        btOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e)
            {
                System.out.println("OK button clicked");
            } 
        });
        CancelHandler handler2
                = new CancelHandler();
        btCancel.setOnAction(handler2);
        pane.getChildren().addAll(btOK, btCancel);
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}

class OKHandler implements EventHandler<ActionEvent>
{

    @Override
    public void handle(ActionEvent e)
    {
        System.out.println("OK button clicked");
    }
}

class CancelHandler implements
        EventHandler<ActionEvent>
{

    @Override
    public void handle(ActionEvent e)
    {
        System.out.println("Cancel button clicked");
    }
}
