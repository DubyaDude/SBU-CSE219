package javafxexamples;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class PentaApp extends Application {
    private Stage primaryStage;
    private Scene scene;
    private Canvas canvas;
    private GraphicsContext gc;
    private ArrayList<double[]> xPoints;
    private ArrayList<double[]> yPoints;
    private ArrayList<Color> colors;
    
    @Override
    public void start(Stage initPrimaryStage) {
        primaryStage = initPrimaryStage;
        initStage();
        initData();
        initGUI();
        initHandlers();
    }
    
    public void initStage() {
        primaryStage.setTitle("Penta App");
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        primaryStage.setX(bounds.getMinX());
        primaryStage.setY(bounds.getMinY());
        primaryStage.setWidth(bounds.getWidth());
        primaryStage.setHeight(bounds.getHeight());
    }
    
    public void initData() {
        xPoints = new ArrayList();
        yPoints = new ArrayList();
        colors = new ArrayList();
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
            if (mouseEvent.getClickCount() == 2) {
                xPoints.clear();
                yPoints.clear();
                colors.clear();
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            }            
        });
        
        canvas.setOnMouseDragged(mouseEvent -> {
            double x = mouseEvent.getX();
            double y = mouseEvent.getY();
            double[] xs = new double[5];
            double[] ys = new double[5];
            
            // CENTER
            xs[0] = x;
            ys[0] = y - (int)(Math.random() * 20) - 1;
			
            // TOP-RIGHT POINT
            xs[1] = x + (int)(Math.random() * 15) + 1;
            ys[1] = y - (int)(Math.random() * 10) - 1;
            
            // BOTTOM-RIGHT POINT
            xs[2] = x + (int)(Math.random() * 10) + 1;
            ys[2] = y + (int)(Math.random() * 15) + 1;

            // BOTTOM-LEFT POINT
            xs[3] = x - (int)(Math.random() * 10) - 1;
            ys[3] = y + (int)(Math.random() * 15) + 1;

            // TOP-LEFT POINT
            xs[4] = x - (int)(Math.random() * 15) - 1;
            ys[4] = y - (int)(Math.random() * 10) - 1;

            xPoints.add(xs);
            yPoints.add(ys);
            int r = (int)(Math.random() * 256);
            int g = (int)(Math.random() * 256);
            int b = (int)(Math.random() * 256);
            colors.add(Color.rgb(r,g,b));
            PentaApp.this.draw();
        });
    }
    
    public void draw() {
        for (int i = 0; i < xPoints.size(); i++) {
            double[] xVertices = xPoints.get(i);
            double[] yVertices = yPoints.get(i);
            for (int j = 0; j < 5; j++) {
                xVertices[j] += (int)(Math.random()*9) - 4;
                yVertices[j] += (int)(Math.random()*9) - 4;
            }
            Color color = colors.get(i);
            gc.setFill(color);
            gc.fillPolygon(xVertices, yVertices, 5);
            gc.setStroke(Color.BLACK);
            gc.strokePolygon(xVertices, yVertices, 5);
        }        
    }
	
    public static void main(String[] args) {
        launch();
    }
}