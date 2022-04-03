package Board;

import FuzzyLogic.FuzzyLogic;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Car {
    private int posX, speedX, width, posY;
    private double speed;
    private Obstacle obstacle;
    private FuzzyLogic fuzzyLogic;
    private Image carImage;
    private int i = 0;
    public Car(int posX, int posY, int speedX, int width) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.speedX = speedX;
        this.speed = speedX;
        fuzzyLogic = new FuzzyLogic();
        fuzzyLogic.setFis();
        fuzzyLogic.fuzzyMethod();
        FileInputStream inputstream = null;
        try {
            inputstream = new FileInputStream("src/main/resources/car.png");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.carImage = new Image(inputstream, 30, 30, true, true);
    }
    public void move(WayGrid wayGrid){
        obstacle = wayGrid.getObstaclesArray().get(0);
        if(obstacle.getPosY() > posY +width) {
            obstacle = wayGrid.getObstaclesArray().get(1);
        }
        posX += speedX;
        updateSpeed();
    }
    public void updateSpeed(){
        int middleOfCar = posX + width/2;
        int middleOfGate = obstacle.getPosX() + width;
        int distX = 130 - (middleOfGate - middleOfCar);
        //getting deltaVX from fuzzyLogic
        fuzzyLogic.setVars(distX, speed, posY - obstacle.getPosY() + width); //update variables
        fuzzyLogic.fis.evaluate();

        speed *= fuzzyLogic.fis.getVariable("deltaVX").getLatestDefuzzifiedValue()/5;

        if(speed > 32) //max speed
            speed = 32;
        else if(speed < 1) // min speed
            speed = 1;

        // to avoid negative values of speed:
        speedX = (int) speed - 16; // speed = 16 = > speedX = 0
                                   // speed < 16 => speedX != 0 (left)
                                   // speed > 16 => speedX != 0 (right)


    }
    public int getPosX() {
        return posX;
    }

    public int getSpeedX() {
        return speedX;
    }


    public int getWidth() {
        return width;
    }

    public Image getCarImage() {
        return carImage;
    }

    public void setCarImage(Image carImage) {
        this.carImage = carImage;
    }
}
