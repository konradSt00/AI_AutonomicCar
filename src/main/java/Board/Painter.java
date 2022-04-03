package Board;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Painter extends Thread{
    private int X = 50;
    private GraphicsContext gc;
    private Canvas canvas;
    private WayGrid wayGrid;

    public Painter(GraphicsContext gc, Canvas canvas, WayGrid wayGrid) {
        this.gc = gc;
        this.canvas = canvas;
        this.wayGrid = wayGrid;
    }

    private void drawShapes(GraphicsContext gc, int X) {
        gc.setFill(Color.GREY);
        gc.fillRoundRect(0, 0, 650, 650, 10, 10);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(10);

        for(int i = 0; i < wayGrid.getObstaclesArray().size(); i++){
            drawObstacle(wayGrid.getObstaclesArray().get(i));
        }
        drawCar(wayGrid.getCar());
        wayGrid.refreshPositions();
    }

    private void drawObstacle(Obstacle obstacle){
        gc.strokeLine(0, obstacle.getPosY(), obstacle.getPosX(), obstacle.getPosY());
        gc.strokeLine(obstacle.getPosX() + obstacle.getWidth(),
                obstacle.getPosY(), canvas.getWidth(), obstacle.getPosY());
    }
    private void drawCar(Car car){
        gc.drawImage(car.getCarImage(), car.getPosX(), canvas.getHeight() -50);
        car.move(wayGrid);
    }
    @Override
    public void run() {
        while(true){
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            this.drawShapes(gc, X);
            X+=30;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
