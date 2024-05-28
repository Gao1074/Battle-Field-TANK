package study.Assignment;

import java.awt.*;

public class Floor {

    GameEngine gameEngine;

    Image image;
    double positionX;

    double positionY;

    Floor(Image image, double positionX, double positionY, GameEngine gameEngine){
        this.image = image;
        this.positionX = positionX;
        this.positionY = positionY;
        this.gameEngine = gameEngine;
    }
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public void draw(){
        gameEngine.saveCurrentTransform();
        gameEngine.translate(positionX,positionY);
        gameEngine.drawImage(image,0,0,100,100);
        gameEngine.restoreLastTransform();
    }
}
