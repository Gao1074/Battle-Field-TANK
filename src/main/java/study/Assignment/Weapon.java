package study.Assignment;

import java.awt.*;
import java.util.ArrayList;

public abstract class Weapon extends Entity implements TankWeapon{
    double power;
    double Angle;
    TANK tank;
    double ChangAngle = 90;
    protected ArrayList<Ammo> ammos = new ArrayList<>(99);
    public void FireMode(double dt){

    }
    public void Fire() {
        for (Ammo ammo : ammos) {
            if (!ammo.Active) {
                ammo.Active = true;
                ammo.Angle = this.Angle;
                ammo.position.setX(this.position.getX());
                ammo.position.setY(this.position.getY());
                ammo.velocity.setX(gameEngine.sin(ammo.Angle)* 200 );
                ammo.velocity.setY(-gameEngine.cos(ammo.Angle)* 200 );
                break;
            }
        }
    }
    public void updateWeapon(){
        this.Angle = tank.Angle + ChangAngle;
        position.setX(tank.position.getX());
        position.setY(tank.position.getY());
    }
    //public void updateW() {
      //  this.Angle =
    //}
    Weapon(TANK tank, GameEngine gameEngine , double Angle){
        super(gameEngine);
        this.tank = tank;
        position.setX(tank.position.getX());
        position.setY(tank.position.getY());
        ChangAngle = Angle * ChangAngle;
        this.Angle = tank.Angle + ChangAngle;
    }
    private void initAmmo(){
        for (int i = 0 ; i<99;i++){
            Ammo ammo = new Ammo(gameEngine);
            ammo.position.setX(this.position.getX());
            ammo.position.setY(this.position.getY());
            ammo.velocity.setX(this.velocity.getX());
            ammo.velocity.setY(this.velocity.getY());
            ammo.Angle = this.Angle;
            ammo.Active = false;
            ammos.add(i,ammo);
        }
    }

    public void drawWeapon(){
        gameEngine.saveCurrentTransform();
        gameEngine.translate(position.getX(),position.getY());
        gameEngine.rotate(Angle);
        gameEngine.changeColor(Color.BLACK);
        gameEngine.drawSolidRectangle(-tank.size.getWidth()/10,-tank.size.getHeight()*3/4,tank.size.getWidth()/5,tank.size.getHeight()/4);
        gameEngine.restoreLastTransform();
    }
    public void updateAmmo(double dt ,Wall wall){
        for (Ammo ammo : ammos) {
            ammo.update(dt,wall);
        }
    }
    public void drawAmmo(){
        for (Ammo ammo : ammos) {
            ammo.draw();
        }
    }
}
