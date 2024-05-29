package study.Assignment;

import java.awt.*;
import java.util.ArrayList;

public class MediumWeapon extends MainWeapon{
    double fullLoadingTime = 2;
    boolean BinaryFire = true;
    MediumWeapon(TANK tank, GameEngine gameEngine, double Angle) {
        super(tank, gameEngine, Angle);
        power = 100;
        initAmmo();
        image = gameEngine.loadImage("MediumWeapon.png");
    }
    public void Fire(){
        if (loadingTime <= 0) {
            creatFire();

            gameEngine.playAudio(fireSoundEffect);
            loadingTime = 2;
        }
    }
    private void creatFire(){
        for (Ammo ammo : ammos) {
            if (!ammo.Active) {

                ammo.Active = true;
                ammo.Angle = this.Angle;
                if (BinaryFire){
                    ammo.position.setX(this.position.getX()+gameEngine.sin(ammo.Angle - 90) * 6 + gameEngine.sin(ammo.Angle) * 60);
                    ammo.position.setY(this.position.getY()-gameEngine.cos(ammo.Angle - 90) * 6 - gameEngine.cos(ammo.Angle) * 60);
                    creatFireEffect(gameEngine.sin(ammo.Angle - 90) * 6,-gameEngine.cos(ammo.Angle - 90) * 6);
                }else {
                    ammo.position.setX(this.position.getX()+gameEngine.sin(ammo.Angle + 90) * 6 + gameEngine.sin(ammo.Angle) * 60);
                    ammo.position.setY(this.position.getY()-gameEngine.cos(ammo.Angle + 90) * 6 - gameEngine.cos(ammo.Angle) * 60);
                    creatFireEffect(gameEngine.sin(ammo.Angle + 90) * 6,-gameEngine.cos(ammo.Angle + 90) * 6);
                }

                ammo.velocity.setX(gameEngine.sin(ammo.Angle) * 1000);
                ammo.velocity.setY(-gameEngine.cos(ammo.Angle) * 1000);
                if (BinaryFire){
                    BinaryFire = false;
                }else {
                    BinaryFire = true;
                    break;
                }
            }
        }
    }
    public void drawWeapon(){
        if (!tank.defeat) {
            gameEngine.saveCurrentTransform();
            gameEngine.translate(position.getX(), position.getY());
            gameEngine.rotate(Angle);
            gameEngine.changeColor(Color.BLACK);
            gameEngine.drawImage(image, -tank.size.getWidth() / 4, -tank.size.getHeight() + 10, tank.size.getWidth() / 2, tank.size.getHeight());
            gameEngine.restoreLastTransform();

            gameEngine.changeColor(Color.BLUE);
            gameEngine.drawSolidRectangle(tank.position.getX() - tank.size.getWidth() / 2, tank.position.getY() - tank.size.getHeight() / 2 - 10, tank.size.getWidth() * (fullLoadingTime - loadingTime) / fullLoadingTime, 5);
            gameEngine.changeColor(Color.BLACK);
            gameEngine.drawRectangle(tank.position.getX() - tank.size.getWidth() / 2, tank.position.getY() - tank.size.getHeight() / 2 - 10, tank.size.getWidth(), 5);
            drawFireEffect();
        }
        drawAmmo();

    }
    public void updateWeapon(double dt){
        Angle = tank.Angle + ChangAngle;
        if (loadingTime > 0){
            loadingTime -=dt;
        }
        if (loadingTime < 0){
            loadingTime = 0;
        }
        position.setX(tank.position.getX());
        position.setY(tank.position.getY());
        updateFireEffect(dt);
    }
}
