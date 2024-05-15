package study.Assignment;

import java.awt.*;
import java.util.ArrayList;
public class Spitfire extends Weapon{
    double LimitTime = 0.05;
    double FireTime = 0;
    double fuel = 100;
    ArrayList<FireAmmo> ammos = new ArrayList<>();
    public Spitfire(TANK tank , GameEngine gameEngine ,double Angle) {
        super(tank, gameEngine, Angle);
        power = 0.2;
        initAmmo();
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


                fire.position.setX(this.position.getX());
                fire.position.setY(this.position.getY());
                fire.velocity.setX(gameEngine.sin(fire.Angle - 10 + gameEngine.rand(20))* 200 );
                fire.velocity.setY(-gameEngine.cos(fire.Angle - 10 + gameEngine.rand(20))* 200 );
                fuel -= 0.1;
                break;
            }
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
}
