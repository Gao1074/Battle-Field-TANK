package study.Assignment;

import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.floor;

public class MainWeapon extends Weapon{
    double loadingTime;
    ArrayList<ATAmmo> ammos = new ArrayList<>(99);
    ArrayList<FireEffect> fireEffects = new ArrayList<>();
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
    public void creatFireEffect(double x,double y){
        boolean FireActive = false;
        for (FireEffect fireEffect : fireEffects) {
            if (!fireEffect.Active) {
                fireEffect.position.setX(position.getX() + gameEngine.sin(Angle) * 60+x);
                fireEffect.position.setY(position.getY() - gameEngine.cos(Angle) * 60+y);
                fireEffect.size.setWidth(size.getWidth());
                fireEffect.size.setHeight(size.getHeight());
                fireEffect.time = 0;
                fireEffect.Angle = Angle;
                fireEffect.duration = 0.5;
                fireEffect.Active = true;
                FireActive = true;
                break;
            }
        }
        if (!FireActive){
            FireEffect fireEffect = new FireEffect(gameEngine);
            fireEffect.position.setX(position.getX()+ gameEngine.sin(Angle) * 60+x);
            fireEffect.position.setY(position.getY()- gameEngine.cos(Angle) * 60+y);
            fireEffect.size.setWidth(size.getWidth());
            fireEffect.size.setHeight(size.getHeight());
            fireEffect.time = 0;
            fireEffect.Angle = Angle;
            fireEffect.duration = 0.1;
            fireEffect.Active = true;
            fireEffects.add(fireEffect);
        }
    }
    public void updateFireEffect(double dt) {
        for (FireEffect fireEffect : fireEffects) {
            if (fireEffect.Active) {
                fireEffect.time += dt;
                if (fireEffect.time >= fireEffect.duration) {
                    fireEffect.Active = false;
                }
            }
        }
    }
    public void drawFireEffect() {
        for (FireEffect fireEffect : fireEffects) {
            if (fireEffect.Active) {
                gameEngine.saveCurrentTransform();

                gameEngine.translate(fireEffect.position.getX(), fireEffect.position.getY());

                int i = getAnimationFrame(fireEffect.time, fireEffect.duration, 3);
                gameEngine.rotate(fireEffect.Angle);
                gameEngine.drawImage(fireEffect.fireEffectImage[i], -fireEffect.size.getWidth()/2-8, -fireEffect.size.getHeight()/2-8, 16, 16);
                gameEngine.restoreLastTransform();
            }
        }
    }
    public int getAnimationFrame(double timer, double duration, int numFrames) {

        int i = (int)floor(((timer % duration) / duration) * numFrames);

        if(i >= numFrames) {
            i = numFrames-1;
        }
        return i;
    }
}
