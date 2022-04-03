package Board;

import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class WayGrid {
    private int NUM_OF_OBSTACLES = 20;
    private int WIDTH = 150;
    private int SPEED = 5;  // Y speed
    private ArrayList<Obstacle> obstaclesArray;
    private int GAP = 200;
    private Car car;
    private Canvas canvas;
    public WayGrid(Canvas canvas) {
        this.canvas = canvas;
        car = new Car(300, (int)canvas.getHeight()-50,16, 30);
        this.obstaclesArray = new ArrayList(NUM_OF_OBSTACLES);
        for(int i = 0; i < NUM_OF_OBSTACLES; i++){
            obstaclesArray.add(new Obstacle(WIDTH, 10 + (int)canvas.getHeight() - GAP*i, (new Random()).nextInt(400)+ 50));
        }
    }

    public ArrayList<Obstacle> getObstaclesArray() {
        return obstaclesArray;
    }


    public void refreshPositions(){
        obstaclesArray.forEach(obstacle -> obstacle.setPosY(obstacle.getPosY() + SPEED));
        obstaclesArray = (ArrayList<Obstacle>) obstaclesArray.stream().
                filter(obstacle -> obstacle.getPosY() < canvas.getHeight() + 50).
                collect(Collectors.toList());
        refillArray();
    }

    private void refillArray(){
        int numOfObstaclesToAdd = NUM_OF_OBSTACLES - obstaclesArray.size();
        for(int i = 0; i < numOfObstaclesToAdd; i ++){
            obstaclesArray.add(new Obstacle(WIDTH,
                    obstaclesArray.stream().mapToInt(obstacle -> obstacle.getPosY()).min().getAsInt() - GAP,
                    (new Random()).nextInt(150)+ 200));
        }
    }

    public Car getCar() {
        return car;
    }

}
