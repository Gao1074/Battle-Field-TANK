package study.Assignment;

import java.awt.*;

public class Ammo extends Entity {
    Image AmmoImage = gameEngine.loadImage("Ammo.png");
    boolean Active = false;
    double power;
    Ammo(GameEngine gameEngine){
        super(gameEngine);
        size.setRadius(8);
    }

    public void update(double dt,Wall wall){
        if (Active) {
            position.setX(position.getX() + velocity.getX() * dt);
            position.setY(position.getY() + velocity.getY() * dt);
            wall.setCollidesAmmo(this);
        }
    }
    public void draw(){
        if (Active) {
            gameEngine.saveCurrentTransform();
            gameEngine.translate(position.getX(), position.getY());
            gameEngine.rotate(Angle);
            gameEngine.drawImage(image,-size.getRadius(), -size.getRadius(),size.getRadius()*2,size.getRadius()*4);
            gameEngine.restoreLastTransform();
        }
    }
}

