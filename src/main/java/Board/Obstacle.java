package Board;

public class Obstacle {
    private int width, posY, posX;

    public Obstacle(int width, int posY, int posX) {
        this.width = width;
        this.posY = posY;
        this.posX = posX;
    }

    public int getWidth() {
        return width;
    }

    public int getPosY() {
        return posY;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosY(int i) {
        this.posY = i;
    }
}
