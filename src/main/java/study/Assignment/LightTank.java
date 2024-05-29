package study.Assignment;

import java.awt.*;
import java.util.ArrayList;

public class LightTank extends TANK{
//    boolean Playing;
    LightWeapon weapon_M = new LightWeapon(this,gameEngine,0);
    LightTank(GameEngine gameEngine) {
        super(gameEngine);
        size.setWidth(40);
        size.setHeight(40);
        FullHealth = 40;
        Health = 40;
        speed = 300;
        MovingAudio = gameEngine.loadAudio("Fast.wav");
        image = gameEngine.loadImage("LightTank0.png");
    }
    LightTank(GameEngine gameEngine,double difficult) {
        super(gameEngine);
        size.setWidth(40);
        size.setHeight(40);
        FullHealth = 40 * difficult;
        Health = 40 * difficult;
        speed = 300 + difficult * 0.01 * 300;
        MovingAudio = gameEngine.loadAudio("Fast.wav");
        image = gameEngine.loadImage("LightTank0.png");
    }
    public void updateTank(double dt){
        if (Health <= 0){
            defeat = true;
        }
        if (!defeat) {
            if (UP) {
                velocity.setX(gameEngine.sin(Angle) * speed);
                velocity.setY(-gameEngine.cos(Angle) * speed);

            }
            if (LEFT) {
                Angle -= 100 * dt;

            }
            if (RIGHT) {
                Angle += 100 * dt;

            }
            if (DOWN) {
                velocity.setX(-gameEngine.sin(Angle) * speed/2);
                velocity.setY(gameEngine.cos(Angle) * speed/2);

            }
//            if (!Playing && (UP || LEFT || RIGHT || DOWN)) {
//                gameEngine.startAudioLoop(MovingAudio);
//                Playing = true;
//            }
//            if (Playing && !(UP || LEFT || RIGHT || DOWN)) {
//                gameEngine.stopAudioLoop(MovingAudio);
//                Playing = false;
//            }
            position.setX(position.getX() + velocity.getX() * dt);
            position.setY(position.getY() + velocity.getY() * dt);
            Border border = new Border(this);

            weapon_M.updateWeapon(dt);
        }
    }
    public void AI(ArrayList<TANK> tanks,double dt){
        if (!defeat) {
            double distance = 9999999;
            for (TANK tank : tanks) {
                if (gameEngine.distance(position.getX(), position.getY(), tank.position.getX(), tank.position.getY()) <= distance) {
                    target = tank;
                }
            }
            if (target.defeat){
                UP = false;
                velocity.setX(0);
                velocity.setY(0);
            }else {
                distance = gameEngine.distance(position.getX(), position.getY(), target.position.getX(), target.position.getY());
                if (distance < 600) {
                    targetTracking(target, dt);
                } else {
                    UP = false;
                    velocity.setX(0);
                    velocity.setY(0);
                }
            }

        }
    }
    public void targetTracking(Entity enemy){
        Angle = -gameEngine.atan2(position.getX() - enemy.position.getX(),position.getY() - enemy.position.getY());
        UP = false;
        weapon_M.Fire();
    }
    public void targetTracking(Entity enemy, double dt){
        double targetAngle = -gameEngine.atan2(position.getX() - enemy.position.getX(),position.getY() - enemy.position.getY());
        double angleDiff = (targetAngle - Angle + 180 + 360) % 360 - 180;
        double maxRotationSpeed = 90;
        if (gameEngine.distance(enemy.position.getX(),enemy.position.getY(),position.getX(),position.getY())<400){
            UP = false;
            velocity.setX(0);
            velocity.setY(0);
        }else {
            UP = true;
        }
        if (angleDiff < -1) {
            Angle -= maxRotationSpeed * dt;
        } else if (angleDiff > 1) {
            Angle += maxRotationSpeed * dt;
        } else {
            Angle = targetAngle;
            weapon_M.Fire();
        }
    }
    public void drawTank(){
        if (!defeat) {
            gameEngine.saveCurrentTransform();
            gameEngine.translate(position.getX(), position.getY());
            gameEngine.rotate(Angle);

            gameEngine.drawImage(image, -size.getWidth() / 2, -size.getHeight() / 2, size.getWidth(), size.getHeight());
            gameEngine.restoreLastTransform();
            gameEngine.drawSolidRectangle(position.getX() - size.getWidth() / 2, position.getY() - size.getHeight() / 2 - 20, size.getWidth() * (Health / FullHealth), 10);
            gameEngine.changeColor(Color.BLACK);
            gameEngine.drawRectangle(position.getX() - size.getWidth() / 2, position.getY() - size.getHeight() / 2 - 20, size.getWidth(), 10);
        }
        weapon_M.drawWeapon();
    }
    public void UpdateDamage(ArrayList<TANK> Enemy){
        weapon_M.updateDamage(Enemy);
    }
}
