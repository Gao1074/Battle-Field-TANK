package study.Assignment;


import java.awt.*;
import java.util.ArrayList;

public class HeavyTank extends TANK{

    ATWeapon weapon_M = new ATWeapon(this, gameEngine,0);
    Spitfire weapon_L = new Spitfire(this, gameEngine,-1,0);
    Spitfire weapon_R = new Spitfire(this, gameEngine,1,1);
    Spitfire weapon_L_L = new Spitfire(this, gameEngine,-1,2);
    Spitfire weapon_R_R = new Spitfire(this, gameEngine,1,3);
    TANK target = this;
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
        if(UP) {
            velocity.setX(gameEngine.sin(Angle) * 60);
            velocity.setY(-gameEngine.cos(Angle) * 60);
        }
        if(LEFT) {
            // Make the spaceship rotate anti-clockwise
            Angle -= 100 * dt;
        }
        if (RIGHT){
            Angle += 100 * dt;
        }
        if (DOWN){
            velocity.setX(-gameEngine.sin(Angle) * 30);
            velocity.setY(gameEngine.cos(Angle) * 30);
        }
        position.setX(position.getX() + velocity.getX() * dt);
        position.setY(position.getY() + velocity.getY() * dt);


        Border border = new Border(this);




        weapon_M.updateWeapon(dt);
        weapon_R.updateWeapon();
        weapon_L.updateWeapon();
        weapon_R_R.updateWeapon();
        weapon_L_L.updateWeapon();
    }
    public void FindTarget(ArrayList<TANK> tanks,double dt){
        weapon_L.targetChoosing(tanks,dt);
        weapon_L_L.targetChoosing(tanks,dt);
        weapon_R.targetChoosing(tanks,dt);
        weapon_R_R.targetChoosing(tanks,dt);
    }


    public HeavyTank(GameEngine gameEngine){
        super(gameEngine);
        image = gameEngine.loadImage("Player.png");
    }
    public void drawUI(){
        weapon_M.drawUI( 20 );
        weapon_L.drawUI( 220 );
        weapon_R.drawUI( 420 );
    }
    public void drawTank(){
        gameEngine.saveCurrentTransform();
        gameEngine.translate(position.getX(),position.getY());
        gameEngine.rotate(Angle);
        gameEngine.drawImage(Moving,- size.getWidth()/2,- size.getHeight()/2,size.getWidth(),size.getHeight()/2);
        gameEngine.drawImage(Moving,- size.getWidth()/2,0,size.getWidth(),size.getHeight()/2);
        gameEngine.drawImage(image, - size.getWidth()/2,  - size.getHeight()/2,size.getWidth(),size.getHeight());

        gameEngine.restoreLastTransform();


        weapon_M.drawWeapon();
        weapon_R.drawWeapon();
        weapon_L.drawWeapon();
        weapon_R_R.drawWeapon();
        weapon_L_L.drawWeapon();


        weapon_M.drawAmmo();
        weapon_L.drawAmmo();
        weapon_R.drawAmmo();
        weapon_L_L.drawAmmo();
        weapon_R_R.drawAmmo();
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
            if (distance > 200) {
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
        }
    }
}
