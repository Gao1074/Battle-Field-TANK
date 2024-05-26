package study.Assignment;

import java.awt.*;

public class LightWeapon extends MainWeapon{
    double loadingTime = 2;
    LightWeapon(TANK tank, GameEngine gameEngine, double Angle) {
        super(tank, gameEngine, Angle);
        power = 100;
        initAmmo();
        image = gameEngine.loadImage("LightWeapon.png");
    }
    public void Fire(){
        if (loadingTime <= 0) {
            creatFire();
            loadingTime = 2;
        }
    }
    private void creatFire(){
        for (Ammo ammo : ammos) {
            if (!ammo.Active) {
                ammo.Active = true;
                ammo.Angle = this.Angle;
                ammo.position.setX(this.position.getX()+ gameEngine.sin(ammo.Angle) * 60);
                ammo.position.setY(this.position.getY()- gameEngine.cos(ammo.Angle) * 60);
                ammo.velocity.setX(gameEngine.sin(ammo.Angle) * 1000);
                ammo.velocity.setY(-gameEngine.cos(ammo.Angle) * 1000);
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
        position.setX(tank.position.getX());
        position.setY(tank.position.getY());
    }
    public void drawWeapon(){
        gameEngine.saveCurrentTransform();
        gameEngine.translate(position.getX(),position.getY());
        gameEngine.rotate(Angle);
        gameEngine.changeColor(Color.BLACK);
        gameEngine.drawImage(image,-tank.size.getWidth() / 4,-tank.size.getHeight() + 10,tank.size.getWidth() / 2 ,tank.size.getHeight());
        gameEngine.restoreLastTransform();
    }
}
