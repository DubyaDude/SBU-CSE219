package javafxexamples;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ImageMosaicApp extends Application {
    private Stage primaryStage;
    private Scene scene;
    private Canvas canvas;
    private Image logo1Image;
    private Image logo2Image;
    private GraphicsContext gc;
    private ArrayList<Point2D> logo1Locations;
    private ArrayList<Point2D> logo2Locations;

    @Override
    public void start(Stage initPrimaryStage) {
        primaryStage = initPrimaryStage;
        initStage();
        initData();
        initGUI();
        initHandlers();
    }

    public void initStage() {
        primaryStage.setTitle("Image Mosaic App");
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
    }
    
    public void initData() {
        logo1Locations = new ArrayList();
        logo2Locations = new ArrayList();
        logo1Image = new Image("Logo1.png");
        logo2Image = new Image("Logo2.png");
    }
    
    public void initGUI() {
        canvas = new Canvas();
        gc = canvas.getGraphicsContext2D();
        Group root = new Group();
        root.getChildren().add(canvas);
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        canvas.setWidth(scene.getWidth());
        canvas.setHeight(scene.getHeight());
    }

    public void initHandlers() {
        canvas.setOnMouseClicked(mouseEvent -> {
            double x = mouseEvent.getX();
            double y = mouseEvent.getY();
            Point2D point = new Point2D(x, y);
            if (!logo1Locations.contains(point))
                logo1Locations.add(point);
            draw();
        });
        canvas.setOnMouseDragged(mouseEvent -> {
            double x = mouseEvent.getX();
            double y = mouseEvent.getY();
            Point2D point = new Point2D(x, y);
            if (!logo2Locations.contains(point))
                logo2Locations.add(point);
            draw();
        });
    }

    public void draw() {
        Iterator<Point2D> it = logo1Locations.iterator();
        while (it.hasNext()) {
            Point2D p = it.next();
            gc.drawImage(logo1Image, p.getX(), p.getY());
        }
        it = logo2Locations.iterator();
        while (it.hasNext()) {
            Point2D p = it.next();
            gc.drawImage(logo2Image, p.getX(), p.getY());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}