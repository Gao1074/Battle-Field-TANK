package study.Assignment;

import java.awt.*;

public class ATWeapon extends Weapon {
    double loadingTime;
    double waitTime = 0.5;
    ATWeapon(TANK tank, GameEngine gameEngine, double Angle) {
        super(tank, gameEngine, Angle);
        power = 200;
        initAmmo();
        image = gameEngine.loadImage("ATWeapon.png");
    }
    public void Fire(){
        if (loadingTime <= 0) {
            creatFire();
            waitTime = 0.2;
            loadingTime = 1;
        }
    }
    private void creatFire(){
        for (Ammo ammo : ammos) {
            if (!ammo.Active) {
                ammo.Active = true;
                ammo.Angle = this.Angle;
                ammo.position.setX(this.position.getX());
                ammo.position.setY(this.position.getY());
                ammo.velocity.setX(gameEngine.sin(ammo.Angle) * 200);
                ammo.velocity.setY(-gameEngine.cos(ammo.Angle) * 200);
                break;
            }
        }
    }
    public void updateWeapon(double dt){
        this.Angle = tank.Angle + ChangAngle;
        if (loadingTime > 0){
            loadingTime -=dt;
        }
        if (loadingTime < 0){
            loadingTime = 0;
        }
        if (waitTime > 0 && waitTime < 1){
            waitTime -= dt;
        }
        if (waitTime <= 0){
            creatFire();
            waitTime = 1;
        }
    }
    private void initAmmo(){
        for (int i = 0 ; i<99;i++){
            ATAmmo ammo = new ATAmmo(gameEngine);
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
    public void drawUI(double X){
        gameEngine.changeColor(Color.BLACK);
        gameEngine.drawSolidRectangle(X,20,(1 - loadingTime)* 100,20);
        gameEngine.drawRectangle(X,20,100,20);
    }
    public void drawWeapon(){
        gameEngine.saveCurrentTransform();
        gameEngine.translate(position.getX(),position.getY());
        gameEngine.rotate(Angle);
        gameEngine.changeColor(Color.BLACK);
        gameEngine.drawImage(image,-tank.size.getWidth() / 4,-tank.size.getHeight() / 2 - 20,tank.size.getWidth() / 2 ,tank.size.getHeight());
        gameEngine.restoreLastTransform();
    }
}
