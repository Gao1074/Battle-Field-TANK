package study.Assignment;

import java.awt.*;
import java.util.ArrayList;
public class Spitfire extends Weapon{
    double LimitTime = 0.05;
    double FireTime = 0;
    double fuel = 100;
    ArrayList<FireAmmo> ammos = new ArrayList<>();
    Image image = gameEngine.loadImage("SideFire.png");
    double sideFireMode;
    public Spitfire(TANK tank , GameEngine gameEngine ,double Angle,double D) {
        super(tank, gameEngine, Angle);
        if (sideFireMode == 0){
            position.setX(tank.position.getX() + gameEngine.sin(tank.Angle - 90) * 20 + gameEngine.sin(tank.Angle) * 20);
            position.setY(tank.position.getY() - gameEngine.cos(tank.Angle - 90) * 20 + gameEngine.sin(tank.Angle) * 20);
        }
        if (sideFireMode == 1){
            position.setX(tank.position.getX() + gameEngine.sin(tank.Angle + 90) * 20 + gameEngine.sin(tank.Angle) * 20);
            position.setY(tank.position.getY() - gameEngine.cos(tank.Angle + 90) * 20 + gameEngine.sin(tank.Angle) * 20);
        }
        if (sideFireMode == 2){
            position.setX(tank.position.getX() + gameEngine.sin(tank.Angle - 90) * 20 + gameEngine.sin(tank.Angle + 180) * 20);
            position.setY(tank.position.getY() - gameEngine.cos(tank.Angle - 90) * 20 + gameEngine.sin(tank.Angle + 180) * 20);
        }
        if (sideFireMode == 3){
            position.setX(tank.position.getX() + gameEngine.sin(tank.Angle + 90) * 20 + gameEngine.sin(tank.Angle + 180) * 20);
            position.setY(tank.position.getY() - gameEngine.cos(tank.Angle + 90) * 20 + gameEngine.sin(tank.Angle + 180) * 20);
        }
        power = 0.2;
        initAmmo();
        sideFireMode = D;
    }
    public void updateWeapon(){
        Angle = tank.Angle + ChangAngle;
        if (sideFireMode == 0){
            position.setX(tank.position.getX() + gameEngine.sin(tank.Angle - 90) * 25 + gameEngine.sin(tank.Angle) * 25);
            position.setY(tank.position.getY() - gameEngine.cos(tank.Angle - 90) * 25 - gameEngine.cos(tank.Angle) * 25);
        }
        if (sideFireMode == 1){
            position.setX(tank.position.getX() + gameEngine.sin(tank.Angle + 90) * 25 + gameEngine.sin(tank.Angle) * 25);
            position.setY(tank.position.getY() - gameEngine.cos(tank.Angle + 90) * 25 - gameEngine.cos(tank.Angle) * 25);
        }
        if (sideFireMode == 2){
            position.setX(tank.position.getX() + gameEngine.sin(tank.Angle - 90) * 25 + gameEngine.sin(tank.Angle + 180) * 25);
            position.setY(tank.position.getY() - gameEngine.cos(tank.Angle - 90) * 25 - gameEngine.cos(tank.Angle + 180) * 25);
        }
        if (sideFireMode == 3){
            position.setX(tank.position.getX() + gameEngine.sin(tank.Angle + 90) * 25 + gameEngine.sin(tank.Angle + 180) * 25);
            position.setY(tank.position.getY() - gameEngine.cos(tank.Angle + 90) * 25 - gameEngine.cos(tank.Angle + 180) * 25);
        }
    }
    private void initAmmo(){
        for (int i = 0 ; i<99;i++){
            FireAmmo ammo = new FireAmmo(gameEngine);
            ammo.position.setX(this.position.getX());
            ammo.position.setY(this.position.getY());
            ammo.velocity.setX(this.velocity.getX());
            ammo.velocity.setY(this.velocity.getY());
            ammo.Angle = this.Angle;
            ammo.power = this.power;
            ammo.Active = false;
            ammos.add(i,ammo);
        }
    }
    public void FireMode(double dt){
        FireTime += dt;
        if (FireTime >= LimitTime){
            FireTime -= LimitTime;
            Fire();
        }
    }
    public void Fire(){

        for (FireAmmo fire : ammos) {
            if (!fire.Active) {
                fire.Active = true;
                fire.Angle = this.Angle;
                Position temp = new Position() {
                    @Override
                    public double getX() {
                        return super.getX();
                    }
                };
                temp.setX(this.position.getX());
                temp.setY(this.position.getY());
                fire.setInitialPosition(temp);


                fire.position.setX(position.getX() + gameEngine.sin(fire.Angle)* 30);
                fire.position.setY(position.getY() - gameEngine.cos(fire.Angle)* 30);
                fire.velocity.setX(gameEngine.sin(fire.Angle - 10 + gameEngine.rand(20))* 200 );
                fire.velocity.setY(-gameEngine.cos(fire.Angle - 10 + gameEngine.rand(20))* 200 );
                fuel -= 0.1;
                break;
            }
        }
    }
    public void targetChoosing(ArrayList<TANK> Tanks ,double dt){
        TANK target = null;
        double distance = 9999999;
        for (TANK tank : Tanks){
            if (gameEngine.distance(position.getX() ,position.getY() ,tank.position.getX(),tank.position.getY()) <=distance){
                target = tank;
                distance = gameEngine.distance(position.getX() ,position.getY() ,tank.position.getX(),tank.position.getY());
            }
        }
        if (target != null && distance <= 400) {
            targetTracking(target,dt);
        }else if (sideFireMode == 0 || sideFireMode == 1){
            targetTracking(0,dt);
        }else if (sideFireMode == 2 || sideFireMode == 3){
            targetTracking(180,dt);
        }

    }
    public void targetTracking(double initAngle,double dt){
        double angleDiff = (initAngle - ChangAngle + 180 + 360) % 360 - 180;
        double maxRotationSpeed = 90;

        if (angleDiff < -1) {
            ChangAngle -= maxRotationSpeed * dt;
        } else if (angleDiff > 1) {
            ChangAngle += maxRotationSpeed * dt;
        } else {
            ChangAngle = initAngle;
        }

    }
    public void targetTracking(Entity enemy, double dt){
        double targetAngle = -gameEngine.atan2(position.getX() - enemy.position.getX(),position.getY() - enemy.position.getY());
        double angleDiff = (targetAngle - Angle + 180 + 360) % 360 - 180;
        double maxRotationSpeed = 90;

        if (angleDiff < -1) {
            ChangAngle -= maxRotationSpeed * dt;
        } else if (angleDiff > 1) {
            ChangAngle += maxRotationSpeed * dt;
        } else {
            ChangAngle = targetAngle - tank.Angle;

        }
        if (gameEngine.distance(position.getX() ,position.getY() ,enemy.position.getX(),enemy.position.getY()) <=200){
            FireMode(dt);
        }

    }
    public void updateAmmo(double dt ,Wall wall){
        for (Ammo ammo : ammos) {
            ammo.update(dt, wall);
        }
    }
    public void drawAmmo(){
        for (Ammo ammo : ammos) {
            ammo.draw();
        }
    }
    public void drawUI(double X){
        gameEngine.changeColor(Color.BLACK);
        gameEngine.drawSolidRectangle(X,20,fuel,20);
        gameEngine.drawRectangle(X,20,100,20);
    }
    public void drawWeapon(){
        gameEngine.saveCurrentTransform();
        gameEngine.translate(position.getX(),position.getY());
        gameEngine.rotate(Angle);
        gameEngine.drawImage(image,-20,-20,40,40);
        gameEngine.restoreLastTransform();
    }
}
