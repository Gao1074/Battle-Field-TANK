package study.Assignment;

import java.util.ArrayList;

public class MainWeapon extends Weapon{
    double loadingTime;
    ArrayList<ATAmmo> ammos = new ArrayList<>(99);
   final GameEngine.AudioClip fireSoundEffect = gameEngine.loadAudio("src/main/resources/Fire.WAV");
    MainWeapon(TANK tank, GameEngine gameEngine, double Angle) {
        super(tank, gameEngine, Angle);
    }
    void initAmmo(){
        for (int i = 0 ; i<99;i++){
            ATAmmo ammo = new ATAmmo(gameEngine);
            ammo.position.setX(this.position.getX());
            ammo.position.setY(this.position.getY());
            ammo.velocity.setX(this.velocity.getX());
            ammo.velocity.setY(this.velocity.getY());
            ammo.Angle = this.Angle;
            ammo.Active = false;
            ammos.add(i,ammo);
        }
    }
    public void updateAmmo(double dt ,Wall wall){
        for (ATAmmo ammo : ammos) {
            ammo.update(dt,wall);
        }
    }
    public void drawAmmo(){
        for (ATAmmo ammo : ammos) {
            ammo.draw();
        }
    }
    public void updateDamage(ArrayList<TANK> tank){
        for (ATAmmo ammo : ammos){
            ammo.Damage(tank);
        }
    }

}
