package study.Assignment;


import java.awt.*;
import java.util.ArrayList;

public class HeavyTank extends TANK{
//    boolean Playing;
    HeavyWeapon weapon_M = new HeavyWeapon(this, gameEngine,0);
    Spitfire weapon_L = new Spitfire(this, gameEngine,-1,0);
    Spitfire weapon_R = new Spitfire(this, gameEngine,1,1);
    Spitfire weapon_L_L = new Spitfire(this, gameEngine,-1,2);
    Spitfire weapon_R_R = new Spitfire(this, gameEngine,1,3);
    TANK target = this;
    public HeavyTank(GameEngine gameEngine){
        super(gameEngine);
        size.setWidth(80);
        size.setHeight(80);
        FullHealth = 200;
        MovingAudio = gameEngine.loadAudio("Slow.wav");
        Health = 200;
        image = gameEngine.loadImage("HeavyTank0.png");
    }
    public void updateTank(double dt){
        Border border = new Border(this);
        if (Health <= 0){
            defeat = true;
        }
        if (!defeat) {
            if (UP) {
                velocity.setX(gameEngine.sin(Angle) * 60);
                velocity.setY(-gameEngine.cos(Angle) * 60);
            }
            if (LEFT) {
                Angle -= 100 * dt;
            }
            if (RIGHT) {
                Angle += 100 * dt;
            }
            if (DOWN) {
                velocity.setX(-gameEngine.sin(Angle) * 30);
                velocity.setY(gameEngine.cos(Angle) * 30);
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


            weapon_M.updateWeapon(dt);
            weapon_R.updateWeapon();
            weapon_L.updateWeapon();
            weapon_R_R.updateWeapon();
            weapon_L_L.updateWeapon();
        }
    }
    //Small turret
    public void FindTarget(ArrayList<TANK> tanks,double dt){
        if (!defeat) {
            weapon_L.targetChoosing(tanks, dt);
            weapon_L_L.targetChoosing(tanks, dt);
            weapon_R.targetChoosing(tanks, dt);
            weapon_R_R.targetChoosing(tanks, dt);
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
        weapon_R.drawWeapon();
        weapon_L.drawWeapon();
        weapon_R_R.drawWeapon();
        weapon_L_L.drawWeapon();
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
                    if (distance > 400) {
                        targetTracking(target, dt);
                    } else {
                        targetTracking(target);
                        velocity.setX(0);
                        velocity.setY(0);
                    }
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
        UP = true;
        if (angleDiff < -1) {
            Angle -= maxRotationSpeed * dt;
        } else if (angleDiff > 1) {
            Angle += maxRotationSpeed * dt;
        } else {
            Angle = targetAngle;
            weapon_M.Fire();
        }
    }
    public void UpdateDamage(ArrayList<TANK> Enemy){
        weapon_M.updateDamage(Enemy);
        weapon_R.updateDamage(Enemy);
        weapon_L.updateDamage(Enemy);
        weapon_R_R.updateDamage(Enemy);
        weapon_L_L.updateDamage(Enemy);
    }
}
