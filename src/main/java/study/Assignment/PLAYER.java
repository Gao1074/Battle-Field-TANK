package study.Assignment;



public class PLAYER extends TANK implements TankWeapon{

    @Override
    public void Fire(GameEngine gameEngine) {
        Ammo tempAmmo;
        for (int i = 0; i <99 ;i++){
            if (!ammo.get(i).Active) {
                tempAmmo = new Ammo();
                tempAmmo.Active = true;
                tempAmmo.Angle = this.Angle;
                tempAmmo.position.setX(this.position.getX());
                tempAmmo.position.setY(this.position.getY());
                tempAmmo.velocity.setX(gameEngine.sin(tempAmmo.Angle)* 200);
                tempAmmo.velocity.setY(-gameEngine.cos(tempAmmo.Angle)* 200);
                ammo.set(i, tempAmmo);
                break;
            }
        }
    }
    public PLAYER(){
        Ammo initAmmo = new Ammo();
        initAmmo.position.setX(0);
        initAmmo.position.setY(0);
        initAmmo.velocity.setX(0);
        initAmmo.velocity.setY(0);
        initAmmo.Active = false;
        initAmmo.Angle = 0;
        for (int i = 0 ; i < 99 ;i++){
            ammo.add(i,initAmmo);
        }
    }
}
