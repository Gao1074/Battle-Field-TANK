package study.Assignment;

import java.awt.*;

public class ATWeapon extends Weapon {
    double loadingTime;
    boolean BinaryFire = true;
    ATWeapon(TANK tank, GameEngine gameEngine, double Angle) {
        super(tank, gameEngine, Angle);
        power = 200;
        initAmmo();
        image = gameEngine.loadImage("ATWeapon.png");
    }
    public void Fire(){
        if (loadingTime <= 0) {
            creatFire();
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
                if (BinaryFire){
                    ammo.position.setX(this.position.getX()+gameEngine.sin(ammo.Angle - 90) * 6 + gameEngine.sin(ammo.Angle) * 60);
                    ammo.position.setY(this.position.getY()-gameEngine.cos(ammo.Angle - 90) * 6 - gameEngine.cos(ammo.Angle) * 60);
                }else {
                    ammo.position.setX(this.position.getX()+gameEngine.sin(ammo.Angle + 90) * 6 + gameEngine.sin(ammo.Angle) * 60);
                    ammo.position.setY(this.position.getY()-gameEngine.cos(ammo.Angle + 90) * 6 - gameEngine.cos(ammo.Angle) * 60);
                }

                ammo.velocity.setX(gameEngine.sin(ammo.Angle) * 200);
                ammo.velocity.setY(-gameEngine.cos(ammo.Angle) * 200);
                if (BinaryFire){
                    BinaryFire = false;
                }else {
                    BinaryFire = true;
                    break;
                }
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
