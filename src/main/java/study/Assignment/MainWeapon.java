package study.Assignment;

public class MainWeapon extends Weapon{
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
            ammo.power = this.power;
            ammo.Active = false;
            ammos.add(i,ammo);
        }
    }
}
