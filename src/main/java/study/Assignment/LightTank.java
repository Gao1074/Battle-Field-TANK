package study.Assignment;

import java.awt.*;

public class LightTank extends TANK{
    LightWeapon M_Weapon = new LightWeapon(this,gameEngine,0);
    LightTank(GameEngine gameEngine) {
        super(gameEngine);
        size.setWidth(40);
        size.setHeight(40);
        image = gameEngine.loadImage("LightTank0.png");
    }
    public void updateTank(double dt){
        if(UP) {
            velocity.setX(gameEngine.sin(Angle) * 200);
            velocity.setY(-gameEngine.cos(Angle) * 200);
        }
        if(LEFT) {
            // Make the spaceship rotate anti-clockwise
            Angle -= 100 * dt;
        }
        if (RIGHT){
            Angle += 100 * dt;
        }
        if (DOWN){
            velocity.setX(-gameEngine.sin(Angle) * 100);
            velocity.setY(gameEngine.cos(Angle) * 100);
        }
        position.setX(position.getX() + velocity.getX() * dt);
        position.setY(position.getY() + velocity.getY() * dt);
        Border border = new Border(this);

        M_Weapon.updateWeapon();
    }
    public void drawTank(){
        gameEngine.saveCurrentTransform();
        gameEngine.translate(position.getX(),position.getY());
        gameEngine.rotate(Angle);
        gameEngine.drawImage(image, - size.getWidth()/2,  - size.getHeight()/2,size.getWidth(),size.getHeight());
        gameEngine.restoreLastTransform();
        M_Weapon.drawWeapon();
    }
}
