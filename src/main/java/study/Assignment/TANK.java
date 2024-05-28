package study.Assignment;

import java.awt.*;
import java.util.ArrayList;

public abstract class TANK extends Entity{
    //direction
    protected double Health;
    protected boolean defeat = false;
    protected double FullHealth;
    protected boolean UP;
    protected boolean DOWN;
    protected boolean LEFT;
    protected boolean RIGHT;
    public boolean explode;
    public boolean isrepair;

    protected GameEngine.AudioClip MovingAudio;
    TANK target = this;

    TANK(GameEngine gameEngine){
        super(gameEngine);

    }
    public void initTank(){
        Angle = 90;
        DOWN = false;
        UP = false;
        LEFT = false;
        RIGHT = false;
        position.setX(size.getHeight());
        position.setY(Toolkit.getDefaultToolkit().getScreenSize().height / 2.0);
        velocity.setX(0);
        velocity.setY(0);
    }
    public void initTank(double x,double y){
        Angle = 90;
        DOWN = false;
        UP = false;
        LEFT = false;
        RIGHT = false;
        position.setX(x);
        position.setY(y);
        velocity.setX(0);
        velocity.setY(0);
    }
    public void updateTank(double dt){

    }
    public void AI(ArrayList<TANK> tanks,double dt){
        double distance = 9999999;
        for (TANK tank : tanks){
            if (gameEngine.distance(position.getX() ,position.getY() ,tank.position.getX(),tank.position.getY()) <= distance){
                target = tank;
            }
        }
        distance = gameEngine.distance(position.getX() ,position.getY() ,target.position.getX(),target.position.getY());
        if (distance < 600){
            if (distance > 400) {
                targetTracking(target, dt);
            }
            else {
                targetTracking(target);
                velocity.setX(0);
                velocity.setY(0);
            }
        }else {
            UP = false;
            velocity.setX(0);
            velocity.setY(0);
        }
    }
    public void targetTracking(Entity enemy){
        Angle = -gameEngine.atan2(position.getX() - enemy.position.getX(),position.getY() - enemy.position.getY());
        UP = false;
        //weapon_M.Fire();
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
            //weapon_M.Fire();
        }
    }
}
